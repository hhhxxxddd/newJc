package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.SwmsMatSupMapper;
import com.jc.api.mapper.SwmsStockInJournalAccountMapper;
import com.jc.api.mapper.SwmsStockInLedgersMapper;
import com.jc.api.service.restservice.*;
import com.jc.api.utils.materialcode.DecoderV1;
import com.jc.api.utils.materialcode.MaterialCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XudongHu
 * @apiNote 入库台账实现类
 * @className SwmsStockInLedgersService
 * @modifier
 * @since 20.1.12日16:43
 */
@Service
@Slf4j
public class SwmsStockInLedgersService implements ISwmsStockInLedgersService {
    //物料编码解码器
    private static DecoderV1 DECODER = new DecoderV1();
    @Autowired
    private SwmsStockInLedgersMapper swmsStockInLedgersMapper;
    @Autowired
    private SwmsStockInJournalAccountMapper swmsStockInJournalAccountMapper;
    @Autowired
    private ISwmsStockInventoryReallyReportsService iSwmsStockInventoryReallyReportsService;
    @Autowired
    private ISwmsBasicSupplierInfoService iSwmsBasicSupplierInfoService;
    @Autowired
    private ISwmsBasicMaterialTypeService iSwmsBasicMaterialTypeService;
    @Autowired
    private ISwmsBasicMaterialSubTypeService iSwmsBasicMaterialSubTypeService;
    @Autowired
    private ISwmsBasicMaterialInfoService iSwmsBasicMaterialInfoService;
    @Autowired
    private ISwmsBasicPlantInfoService iSwmsBasicPlantInfoService;
    @Autowired
    private ISwmsBasicMeasureUnitService iSwmsBasicMeasureUnitService;
    @Autowired
    private SwmsMatSupMapper matSupMapper;

    /**
     * 解析编码并存入台账
     * @param SwmsStockInJournalAccountId 入库台账id
     */
    @Override
    @Async("PoolTaskExecutor")
    @Transactional
    public void parsingAndInsert(String SwmsStockInJournalAccountId) {
        SwmsStockInJournalAccount journalAccount = swmsStockInJournalAccountMapper.selectById(SwmsStockInJournalAccountId);
        if (journalAccount == null) throw new DataNotFindException("未找到此入库记录:" + SwmsStockInJournalAccountId);
        swmsStockInJournalAccountMapper.updateById(journalAccount.setFlag(true));

        MaterialCode.DecodedMaterialCode codeContent = DECODER.decode(journalAccount.getMaterialCode());
        log.info("入库台账解析开始=======================>:{}", SwmsStockInJournalAccountId);
        log.info(journalAccount.toString());
        //供应商代号查询,自动新增
        String materialSupplierNameCode = codeContent.getMaterialSupplierNameCode();
        SwmsBasicSupplierInfo supplierInfo = iSwmsBasicSupplierInfoService.autoAdd(new SwmsBasicSupplierInfo().setMaterialSupplierCode(materialSupplierNameCode));
        //物料类型查询,自动新增
        String materialTypeStr = codeContent.getMaterialTypeStr();
        SwmsBasicMaterialType materialType = iSwmsBasicMaterialTypeService.autoAdd(new SwmsBasicMaterialType().setTypeCode(materialTypeStr));
        SwmsBasicMaterialSubType subType = iSwmsBasicMaterialSubTypeService.autoAdd(new SwmsBasicMaterialSubType().setSubTypeCode(codeContent.getMaterialNameCode()).setTypeId(Integer.valueOf(materialType.getId())));
        //物料计量单位 自动新增
        String weightWithUnit = codeContent.getWeight();
        String wordRegex = "[a-zA-z]+";
        Matcher matcher = Pattern.compile(wordRegex).matcher(weightWithUnit);
        String unit = "";
        while (matcher.find()) unit = matcher.group();
        //=======>单位
        unit = unit.length() > 0 ? unit.toLowerCase() : "无单位";
        SwmsBasicMeasureUnit measureUnit = iSwmsBasicMeasureUnitService.autoAdd(new SwmsBasicMeasureUnit().setMeasureUnit(unit).setAutoFlag(true));
        //=======>重量
        String weightStr = weightWithUnit.replaceAll(wordRegex, "");
        Float weightFloat = Float.valueOf(weightStr);
        //物料名称查询,自动新增
        String materialNameCode = codeContent.getMaterialNameCode();
        SwmsBasicMaterialInfo materialInfo = new SwmsBasicMaterialInfo();
        materialInfo
                .setMaterialNameCode(materialNameCode)
                .setMaterialTypeId(Integer.valueOf(materialType.getId()))
                .setSubTypeId(Integer.valueOf(subType.getId()))
                //.setSupplierId(Integer.valueOf(supplierInfo.getId()))
                .setMeasureUnit(unit)
                .setStreamFlag(false)
                .setAlkaliFlag(false)
                .setNiFlag(false)
                .setNhFlag(false)
                .setMnFlag(false)
                .setCoFlag(false)
                .setAutoFlag(true);
        SwmsBasicMaterialInfo newMaterialInfo = iSwmsBasicMaterialInfoService.autoAdd(materialInfo);
        //建立物料供应商映射
        SwmsBasicMatSup matSup = new SwmsBasicMatSup();
        matSup.setMatId(Integer.valueOf(newMaterialInfo.getId()));
        matSup.setSupId(Integer.valueOf(supplierInfo.getId()));

        //物料车间代号 自动新增
        String materialWorkShopCode = codeContent.getMaterialWorkShopCode();
        SwmsBasicPlantInfo plantInfo = iSwmsBasicPlantInfoService.autoAdd(new SwmsBasicPlantInfo().setPlantCode(materialWorkShopCode));


        //开始新增台账
        SwmsStockInLedgers stockInLedgers = new SwmsStockInLedgers();
        stockInLedgers
                .setStockInRecordId(Long.valueOf(journalAccount.getId()))
                .setMaterialCode(journalAccount.getMaterialCode())
                .setMaterialBatch(codeContent.getBatch())
                .setMaterialTypeId(Integer.valueOf(materialType.getId()))
                .setMaterialSubTypeId(Integer.valueOf(subType.getId()))
                .setMaterialWorkshopId(Integer.valueOf(plantInfo.getId()))
                .setMaterialNameCode(Integer.valueOf(materialInfo.getId()))
                .setMaterialSupplierCode(Integer.valueOf(supplierInfo.getId()))
                .setMaterialName(materialInfo.getMaterialName())
                .setBagNum(Integer.valueOf(codeContent.getBagNo()))
                .setWeight(weightFloat)
                .setMeasureUnit(measureUnit.getMeasureUnit())
                .setCreatedTime(journalAccount.getCreatedTime())
                .setCreatedPerson(journalAccount.getCreatedPerson())
                .setMaterialStatus(0);

        QueryWrapper<SwmsStockInLedgers> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.eq("material_code", journalAccount.getMaterialCode()).last("limit 1");
        if (swmsStockInLedgersMapper.selectOne(byMaterialCode) != null) {
            log.error("入库台账保存失败:物料编码重复,{}", journalAccount.getMaterialCode());
            return;
        }
        Boolean isInsert = swmsStockInLedgersMapper.insert(stockInLedgers) > 0;
        log.info("入库台账保存完成:{}", isInsert);
        //增加库存
        SwmsStockInventoryReallyReports newValue = new SwmsStockInventoryReallyReports();
        newValue.setMaterialTypeId(Integer.valueOf(materialType.getId()))
                .setMaterialSubTypeId(Integer.valueOf(subType.getId()))
                .setMaterialNameCode(Integer.valueOf(materialInfo.getId()))
                .setMaterialSupplierCode(Integer.valueOf(supplierInfo.getId()))
                .setMaterialName(materialInfo.getMaterialName())
                .setMaterialBatch(stockInLedgers.getMaterialBatch())
                .setMeasureUnit(stockInLedgers.getMeasureUnit())
                .setCheckStatus(0)
                .setRealWeight(weightFloat)
                .setUsefulWeight(weightFloat)
                .setStockDate(stockInLedgers.getCreatedTime());
        iSwmsStockInventoryReallyReportsService.autoAdd(newValue);
    }

    /**
     * 查询所有
     * @param materialCode
     * @return
     */
    @Override
    public List<SwmsStockInLedgers> getAll(String materialCode) {
        return swmsStockInLedgersMapper.selectEntity(materialCode);
    }

    /**
     * 查询所有-分页
     * @param materialCode
     * @return
     */
    @Override
    public IPage<SwmsStockInLedgers> getAllByPage(Page page, String materialCode) {
        return swmsStockInLedgersMapper.selectEntityByPage(page,materialCode);
    }
}
