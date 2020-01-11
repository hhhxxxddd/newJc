package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.param.MaterialInfoQueryParam;
import com.jc.api.entity.po.MaterialInfo;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.po.MaterialType;
import com.jc.api.entity.vo.MaterialInfoJointVo;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.MaterialInfoMapper;
import com.jc.api.mapper.MaterialInfoSupplierMapper;
import com.jc.api.mapper.MaterialStockMapper;
import com.jc.api.mapper.MaterialTypeMapper;
import com.jc.api.service.restservice.IMaterialInfoService;
import com.jc.api.service.restservice.IMaterialTypeService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author XudongHu
 * @apiNote 物料信息实现类
 * @className MaterialInfoService
 * @modifier
 * @since 2019/11/1日23:59
 */
@Deprecated
@Service
@Slf4j
public class MaterialInfoService implements IMaterialInfoService {
    @Autowired
    private MaterialInfoMapper materialInfoMapper;
    @Autowired
    private MaterialStockMapper materialStockMapper;
    @Autowired
    private IMaterialTypeService iMaterialTypeService;
    @Autowired
    private MaterialTypeMapper materialTypeMapper;
    @Autowired
    private MaterialInfoSupplierMapper materialInfoSupplierMapper;


    @Override
    public MaterialInfoJointVo getById(String materialInfoId) {
        MaterialInfo materialInfo = materialInfoMapper.selectById(materialInfoId);
        return toVo(materialInfo);
    }

    /**
     * 查询所有 -条件
     *
     * @param materialInfoQueryParam 物料信息查询参数
     * @return list
     */
    @Override
    public List<MaterialInfoJointVo> getAllVo(MaterialInfoQueryParam materialInfoQueryParam) {
        QueryWrapper<MaterialInfo> byCondition = materialInfoQueryParam.build();
        List<MaterialInfo> materialInfos = materialInfoMapper.selectList(byCondition);
        //类型是外键 所以额外搜索一下
        //是否深度搜索 => 如果深度搜索,搜索结果将包含给定类型的子类型
        Boolean depthQuery = materialInfoQueryParam.getDepthQuery();
        if (materialInfoQueryParam.getMaterialTypeId() != null) {
            MaterialType materialType = materialTypeMapper.selectById(materialInfoQueryParam.getMaterialTypeId());
            if (materialType == null) return null;
            if (depthQuery) {
                //深度查询
                Set<String> allChildren = iMaterialTypeService.getAllChildren(Integer.valueOf(materialType.getId()));
                materialInfos.removeIf(e -> notInTypeList(e, allChildren));
            } else {
                Integer typeId = Integer.valueOf(materialType.getId());
                materialInfos.removeIf(m -> !m.getMaterialTypeId().equals(typeId));
            }
        }
        //转vo
        List<MaterialInfoJointVo> infoVos = new ArrayList<>();
        materialInfos.forEach(e -> infoVos.add(toVo(e)));
        return infoVos;
    }

    /**
     * 查询所有 条件/分页
     *
     * @param page                   分页参数
     * @param materialInfoQueryParam 物料信息查询参数
     * @return
     */
    @Override
    public IPage<MaterialInfoJointVo> getAllVoByPage(Page page, MaterialInfoQueryParam materialInfoQueryParam) {
        QueryWrapper<MaterialInfo> byCondition = materialInfoQueryParam.build();
        Boolean depthQuery = materialInfoQueryParam.getDepthQuery();

        //是否存在类型
        Set<String> children = new HashSet<>();
        if (materialInfoQueryParam.getMaterialTypeId() != null) {
            MaterialType materialType = materialTypeMapper.selectById(materialInfoQueryParam.getMaterialTypeId());
            if (materialType == null) return null;
            if (depthQuery) children.addAll(iMaterialTypeService.getAllChildren(Integer.valueOf(materialType.getId())));
            else children.add(materialType.getId());
        }
        if (!children.isEmpty()) byCondition.in("material_type_id", children);
        IPage materialInfoIPage = materialInfoMapper.selectPage(page, byCondition);

        List<MaterialInfo> records = materialInfoIPage.getRecords();
        List<MaterialInfoJointVo> voList = new ArrayList<>();
        records.forEach(e -> voList.add(toVo(e)));

        return materialInfoIPage.setRecords(voList);
    }

    /**
     * 自动新增物料信息
     *
     * @param materialInfo 物料信息po
     * @return 是否存在相同名称代号?返回旧数据:新增并返回新数据
     */
    @Override
    @Transactional
    public MaterialInfo autoAdd(MaterialInfo materialInfo) {
        String materialNameCode = materialInfo.getMaterialNameCode();
        QueryWrapper<MaterialInfo> byNameCode = new QueryWrapper<>();
        byNameCode.eq("material_name_code", materialNameCode).last("limit 1");
        MaterialInfo oldValue = materialInfoMapper.selectOne(byNameCode);
        if (oldValue != null) return oldValue;
        materialInfo.setAutoFlag(1);
        materialInfo.setMaterialName("未知物料名称");
        materialInfoMapper.insert(materialInfo);
        //新增库存
        int stockInsertSuccess = materialStockMapper.insert(MaterialStock.builder().bagsSum(0).materialInfoId(Long.valueOf(materialInfo.getId())).weightSum(0.0).build());
        log.info("新增库存:{}", stockInsertSuccess > 0);
        return materialInfo;
    }

    /**
     * 新增物料信息
     *
     * @param materialInfo 物料信息po
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean add(MaterialInfo materialInfo) {
        String materialNameCode = materialInfo.getMaterialNameCode();
        QueryWrapper<MaterialInfo> byNameCode = new QueryWrapper<>();
        byNameCode.eq("material_name_code", materialNameCode).last("limit 1");
        // 代号是唯一的,类型也必须存在,供应商必须存在
        if (null != materialInfoMapper.selectOne(byNameCode))
            throw new DataDuplicateException(String.format("新增失败,名称代号重复:%s", materialNameCode));
        if (null == materialTypeMapper.selectById(materialInfo.getMaterialTypeId()))
            throw new DataNotFindException(String.format("新增失败,类型未找到:%d", materialInfo.getMaterialTypeId()));
        if (null == materialInfoSupplierMapper.selectById(materialInfo.getMaterialSupplierId()))
            throw new DataNotFindException(String.format("新增失败,物料供货商id未找到:%d", materialInfo.getMaterialSupplierId()));
        //新增物料信息,需要新增库存信息
        log.info("新增物料:{}", materialInfo.toString());
        boolean success = materialInfoMapper.insert(materialInfo) > 0;
        MaterialStock build = MaterialStock.builder().bagsSum(0).materialInfoId(Long.valueOf(materialInfo.getId())).weightSum(0.0).build();
        int stockInsertSuccess = materialStockMapper.insert(build);
        //新增库存
        log.info("新增库存:{}", stockInsertSuccess > 0);


        return success;
    }

    /**
     * 更新 物料信息
     *
     * @param materialInfo 物料信息
     * @return
     */
    @Override
    @Transactional
    public Boolean update(MaterialInfo materialInfo) {

        String id = materialInfo.getId();
        String materialNameCode = materialInfo.getMaterialNameCode();
        Integer materialTypeId = materialInfo.getMaterialTypeId();
        Integer materialSupplierId = materialInfo.getMaterialSupplierId();

        MaterialInfo oldValue = materialInfoMapper.selectById(id);
        if (null == oldValue) throw new DataNotFindException(String.format("更新失败,物料信息未找到:%s", id));
        if (!StringUtil.isNullOrEmpty(materialNameCode) && !materialNameCode.equals(oldValue.getMaterialNameCode())) {
            QueryWrapper<MaterialInfo> byNameCode = new QueryWrapper<>();
            byNameCode.eq("material_name_code", materialNameCode).last("limit 1");
            if (materialInfoMapper.selectOne(byNameCode) != null)
                throw new DataDuplicateException(String.format("更新失败名称代号重复:%s", materialNameCode));
        }
        if (null != materialTypeId && null == materialTypeMapper.selectById(materialTypeId))
            throw new DataNotFindException("更新失败类型未找到:" + materialTypeId);
        if (null != materialSupplierId && null == materialInfoSupplierMapper.selectById(materialSupplierId))
            throw new DataNotFindException("更新失败,供应商未找到:" + materialSupplierId);
        return materialInfoMapper.updateById(materialInfo) > 0;
    }

    /**
     * 删除一个物料信息
     *
     * @param id    主键
     * @return boolean
     */
    @Override
    @Transactional
    public Boolean delete(Integer id) {
        try {
            return materialInfoMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,存在关联的数据");
        }
    }

    //物料是否属于此类型?
    private boolean notInTypeList(MaterialInfo m, Set<String> typeIds) {
        String materialTypeId = String.valueOf(m.getMaterialTypeId());
        boolean isExist = false;
        for (String typeId : typeIds) {
            if (materialTypeId.equals(typeId)) {
                isExist = true;
                break;
            }
        }
        return !isExist;
    }

    //转vo
    private MaterialInfoJointVo toVo(MaterialInfo materialInfo) {
        MaterialInfoJointVo materialInfoJointVo = new MaterialInfoJointVo(materialInfo);
        List<MaterialType> completeType = iMaterialTypeService.getCompleteType(materialInfo.getMaterialTypeId());
        materialInfoJointVo.setCompleteType(completeType);
        return materialInfoJointVo;
    }
}

