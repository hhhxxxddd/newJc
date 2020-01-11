package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.constant.MaterialStatusEnum;
import com.jc.api.entity.param.StockInRecordAccountQueryParam;
import com.jc.api.entity.po.*;
import com.jc.api.entity.vo.StockAccountJointVo;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.*;
import com.jc.api.utils.WeightUnitConvertUtil;
import com.jc.api.utils.materialcode.DecoderV1;
import com.jc.api.utils.materialcode.MaterialCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XudongHu
 * @apiNote 入库记录台账接口
 * @className StockInRecordAccountService
 * @modifier
 * @since 19.12.8日0:06
 */
@Service
@Slf4j
public class StockInRecordAccountService implements IStockInRecordAccountService {
    //物料编码解码器
    private static DecoderV1 DECODER = new DecoderV1();
    @Autowired
    private StockInRecordMapper stockInRecordMapper;
    @Autowired
    private StockInRecordAccountMapper stockInRecordAccountMapper;
    @Autowired
    private MaterialStockMapper materialStockMapper;
    @Autowired
    private IMaterialInfoWorkshopService iMaterialInfoWorkshopService;
    @Autowired
    private IMaterialInfoSupplierService iMaterialInfoSupplierService;
    @Autowired
    private IMaterialTypeService iMaterialTypeService;
    @Autowired
    private IMaterialInfoService iMaterialInfoService;
    @Autowired
    private MaterialInfoMapper materialInfoMapper;
    @Autowired
    private MaterialTypeMapper materialTypeMapper;
    @Autowired
    private MaterialInfoWorkshopMapper materialInfoWorkshopMapper;
    @Autowired
    private MaterialInfoSupplierMapper materialInfoSupplierMapper;

    /**
     * 新增入库台账
     * 解析并新增
     *
     * @param stockInRecordId 物料入库id
     * @return boolean
     */
    @Override
    @Async("PoolTaskExecutor")
    @Transactional
    public synchronized void parsingAndInsert(String stockInRecordId) {
        StockInRecord stockInRecord = stockInRecordMapper.selectById(stockInRecordId);
        if (stockInRecord == null) throw new DataNotFindException("未找到此入库记录:" + stockInRecordId);
        //物料编码解析工具
        MaterialCode.DecodedMaterialCode codeContent = DECODER.decode(stockInRecord.getMaterialCode());
        log.info("入库台账解析开始=======================>:{}", stockInRecordId);
        log.info(stockInRecord.toString());
        //供应商代号查询,未查到新增
        String materialSupplierNameCode = codeContent.getMaterialSupplierNameCode();
        MaterialInfoSupplier materialInfoSupplier = iMaterialInfoSupplierService.autoAdd(MaterialInfoSupplier.builder().materialSupplierCode(materialSupplierNameCode).build());
        //物料类型查询,未查到新增
        String materialTypeStr = codeContent.getMaterialTypeStr();
        List<MaterialType> materialTypes = iMaterialTypeService.autoAdd(materialTypeStr);
        MaterialType leafType = materialTypes.get(materialTypes.size() - 1);
        List<MaterialType> completeType = iMaterialTypeService.getCompleteType(Integer.valueOf(leafType.getId()));
        MaterialType rootType = completeType.get(0);
        //物料名称查询,未查到新增
        String materialNameCode = codeContent.getMaterialNameCode();
        MaterialInfo build = MaterialInfo.builder()
                .materialNameCode(materialNameCode)
                .materialSupplierId(Integer.valueOf(materialInfoSupplier.getId()))
                .materialTypeId(Integer.valueOf(leafType.getId()))
                .niFlag(0)
                .coFlag(0)
                .mnFlag(0)
                .nhFlag(0)
                .alkaliFlag(0)
                .autoFlag(1)
                .build();
        MaterialInfo materialInfo = iMaterialInfoService.autoAdd(build);
        //物料车间代号查询,未查到新增
        String materialWorkShopCode = codeContent.getMaterialWorkShopCode();
        MaterialInfoWorkshop materialInfoWorkshop = iMaterialInfoWorkshopService.autoAdd(MaterialInfoWorkshop.builder().materialWorkshopCode(materialWorkShopCode).build());
        //单位转换
        String weightWithUnit = codeContent.getWeight();
        Double weight = WeightUnitConvertUtil.convertWithUnit(weightWithUnit, WeightUnitConvertUtil.WeightUnitEnum.kg);
        //新增入库台账
        StockInRecordAccount stockInRecordAccount = StockInRecordAccount
                .builder()
                .stockInRecordId(Long.valueOf(stockInRecordId))
                .materialInfoId(Long.valueOf(materialInfo.getId()))
                .materialCode(stockInRecord.getMaterialCode())
                .materialBatch(codeContent.getBatch())
                .materialNameCode(codeContent.getMaterialNameCode())
                .materialLeafTypeId(Integer.valueOf(leafType.getId()))
                .materialRootTypeId(Integer.valueOf(rootType.getId()))
                .materialSupplierId(Integer.valueOf(materialInfoSupplier.getId()))
                .materialWorkshopId(Integer.valueOf(materialInfoWorkshop.getId()))
                .bagNo(codeContent.getBagNo())
                .weight(weight)
                .materialStatus(MaterialStatusEnum.IN_REPO.getCode())
                .createdTime(stockInRecord.getCreatedTime())
                .createdPerson(stockInRecord.getCreatedPerson())
                .build();
        QueryWrapper<StockInRecordAccount> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", stockInRecord.getMaterialCode()).last("limit 1");
        if (stockInRecordAccountMapper.selectOne(byMaterialCode) != null) {
            log.error("入库台账保存失败:物料编码重复,{}",stockInRecord.getMaterialCode());
            return;
        }
        Boolean isInsert = stockInRecordAccountMapper.insert(stockInRecordAccount) > 0;
        log.info("入库台账保存完成:{}", isInsert);
        //增加库存
        String materialInfoId = materialInfo.getId();
        QueryWrapper<MaterialStock> byInfoId = new QueryWrapper<>();
        byInfoId.eq("material_info_id", materialInfoId).last("limit 1");
        MaterialStock materialStock = materialStockMapper.selectOne(byInfoId);
        materialStock.setBagsSum(materialStock.getBagsSum() + 1);
        materialStock.setWeightSum(materialStock.getWeightSum() + weight);
        boolean updateStock = materialStockMapper.updateById(materialStock) > 0;
        log.info("库存更新:{}-{}", materialStock,updateStock);
    }

    /**
     * 查询台账
     * @param id 主键
     * @return vo
     */
    @Override
    public StockAccountJointVo getById(String id) {
        StockInRecordAccount stockInRecordAccount = stockInRecordAccountMapper.selectById(id);
        return toVo(stockInRecordAccount);
    }

    /**
     * 查询所有-条件
     * @param queryParam
     * @return
     */
    @Override
    public List<StockAccountJointVo> getAll(StockInRecordAccountQueryParam queryParam) {
        QueryWrapper<StockInRecordAccount> build = queryParam.build();
        List<StockInRecordAccount> stockInRecordAccounts = stockInRecordAccountMapper.selectList(build);
        ArrayList<StockAccountJointVo> stockAccountJointVos = new ArrayList<>();
        stockInRecordAccounts.forEach(e -> stockAccountJointVos.add(toVo(e)));
        return stockAccountJointVos;
    }

    /**
     * 查询所有-条件/分页
     * @param page
     * @param queryParam
     * @return
     */
    @Override
    public IPage<StockAccountJointVo> getAllByPage(Page page, StockInRecordAccountQueryParam queryParam) {
        QueryWrapper<StockInRecordAccount> build = queryParam.build();
        IPage iPage = stockInRecordAccountMapper.selectPage(page, build);
        List<StockInRecordAccount> records = iPage.getRecords();
        ArrayList<StockAccountJointVo> stockAccountJointVos = new ArrayList<>();
        records.forEach(e -> stockAccountJointVos.add(toVo(e)));
        return iPage.setRecords(stockAccountJointVos);
    }

    /**
     * 出库-(物料状态设置)
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean out(String id, Integer status) {
        StockInRecordAccount oldValue = stockInRecordAccountMapper.selectById(id);
        if (oldValue == null) throw new DataNotFindException("出库失败,未找到台账id:" + id);
        oldValue.setMaterialStatus(status);
        return stockInRecordAccountMapper.updateById(oldValue) > 0;
    }

    private StockAccountJointVo toVo(StockInRecordAccount stockInRecordAccount) {
        StockAccountJointVo jointVo = new StockAccountJointVo(stockInRecordAccount);
        //名称
        MaterialInfo materialInfo = materialInfoMapper.selectById(stockInRecordAccount.getMaterialInfoId());
        //子类型
        Integer materialLeafTypeId = stockInRecordAccount.getMaterialLeafTypeId();
        MaterialType leafType = materialTypeMapper.selectById(materialLeafTypeId);
        //父类型
        Integer materialRootTypeId = stockInRecordAccount.getMaterialRootTypeId();
        MaterialType rootType = materialTypeMapper.selectById(materialRootTypeId);
        //车间名称
        Integer materialWorkshopId = stockInRecordAccount.getMaterialWorkshopId();
        MaterialInfoWorkshop workshop = materialInfoWorkshopMapper.selectById(materialWorkshopId);
        //供应商名称
        Integer materialSupplierId = stockInRecordAccount.getMaterialSupplierId();
        MaterialInfoSupplier supplier = materialInfoSupplierMapper.selectById(materialSupplierId);
        //
        jointVo.setMaterialName(materialInfo==null?"未知物料名称":materialInfo.getMaterialName());
        jointVo.setLeafType(leafType);
        jointVo.setRootType(rootType);
        jointVo.setWorkshop(workshop);
        jointVo.setSupplier(supplier);
        return jointVo;

    }
}
