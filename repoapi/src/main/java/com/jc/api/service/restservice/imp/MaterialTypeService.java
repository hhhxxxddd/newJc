package com.jc.api.service.restservice.imp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.po.MaterialType;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.DataNotFindException;
import com.jc.api.mapper.MaterialInfoMapper;
import com.jc.api.mapper.MaterialTypeMapper;
import com.jc.api.service.restservice.IMaterialInfoService;
import com.jc.api.service.restservice.IMaterialTypeService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author XudongHu
 * @className MaterialTypeServiceImp
 * @apiNote 物料类型服务
 * @modifer
 * @since 2019/10/30日16:19
 */
@Service
@Slf4j
public class MaterialTypeService implements IMaterialTypeService {
    @Autowired
    private MaterialTypeMapper materialTypeMapper;
    @Autowired
    private MaterialInfoMapper materialInfoMapper;
    @Autowired
    private IMaterialInfoService iMaterialInfoService;

    /**
     * 查询父类型链
     * 父类型=>子类型 顺序排列
     *
     * @param id 子类型主键
     * @return list 父类型=>子类型
     */
    @Override
    public List<MaterialType> getCompleteType(Integer id) {
        //返回查询结果
        MaterialType materialType = materialTypeMapper.selectById(id);
        List<MaterialType> values = new ArrayList<>();
        //限制查询20次,深度20层不太可能
        for (int i = 1; materialType != null && i <= 20; i++) {
            values.add(materialType);
            Integer parentId = materialType.getParentId();
            //没有父parentId 跳出循环
            if (parentId == null) break;
            MaterialType parentMaterial = materialTypeMapper.selectById(parentId);
            if (parentMaterial == null)
                throw new DataNotFindException("id:" + materialType.getId() + ":父物料类型id不存在!,parentId:" + parentId);
            //子类型用父类型替换
            materialType = parentMaterial;
            //如果parentId等于自己的id 跳出循环
            if (parentId.equals(materialType.getParentId())) break;

        }
        //父类->子类排序
        Collections.reverse(values);
        return values;
    }

    /**
     * 查询所有类型:树形
     *
     * @return jsonArray
     */
    @Override
    public JSONArray getAllTypeTree() {
        QueryWrapper<MaterialType> byLevel = new QueryWrapper<>();
        byLevel.eq("level", 1);
        List<MaterialType> materialTypes = materialTypeMapper.selectList(byLevel);
        JSONArray childTree = new JSONArray();
        return queryRecursive(childTree, materialTypes);
    }

    /**
     * 查询子类型树
     *
     * @param id 主键
     * @return map
     */
    @Override
    public JSONArray getTypeTree(Integer id) {
        JSONArray childTree = new JSONArray();
        //查看是否存在此类型
        List<MaterialType> childList = getNodeById(id);
        if (childList == null) return childTree;
        return queryRecursive(childTree, childList);

    }


    /**
     * 查询所有子类
     *
     * @param id 主键
     * @return list
     */
    @Override
    public Set<String> getAllChildren(Integer id) {
        Set<String> childList = new HashSet<>();
        List<MaterialType> children = getNodeById(id);
        if (children == null) return childList;
        children.forEach(e -> {
            childList.add(e.getId());
            Set<String> kids = getAllChildren(Integer.valueOf(e.getId()));
            if (!kids.isEmpty()) childList.addAll(kids);
        });

        return childList;
    }

    /**
     * 条件查询所有
     *
     * @return Page
     */
    @Override
    public IPage<MaterialType> getAllByPage(Page page, MaterialType materialType) {
        QueryWrapper<MaterialType> byEntity = new QueryWrapper<>();
        byEntity.setEntity(materialType);
        return materialTypeMapper.selectPage(page, byEntity);
    }

    /**
     * 条件查询所有
     *
     * @return list
     */
    @Override
    public List<MaterialType> getAll(MaterialType materialType) {
        QueryWrapper<MaterialType> byEntity = new QueryWrapper<>();
        if (materialType.getLevel() != null) byEntity.eq("level", materialType.getLevel());
        if (materialType.getTypeName() != null)
            byEntity.likeRight("type_name", materialType.getTypeName());
        if (materialType.getParentId() != null)
            byEntity.ge("parent_id", materialType.getParentId());
        return materialTypeMapper.selectList(byEntity);
    }

    /**
     * 新增物料类型
     *
     * @param materialType 物料类型po
     * @return 物料类型po
     */
    @Override
    @Transactional
    public Boolean add(MaterialType materialType) {
        //查询条件,根据名称
        QueryWrapper<MaterialType> byTypeCode = new QueryWrapper<>();
        byTypeCode.eq("type_code", materialType.getTypeCode()).last("limit 1");
        //如果没有父id,证明是1级类型
        if (materialType.getParentId() == null) materialType.setLevel(1);
        else {
            MaterialType parentType = materialTypeMapper.selectById(materialType.getParentId());
            if (parentType == null) throw new DataNotFindException("新增失败,不存在的父类型id:" + materialType.getParentId());
            materialType.setLevel(parentType.getLevel() + 1);
        }
        if (materialTypeMapper.selectOne(byTypeCode) != null)
            throw new DataDuplicateException("物料类型代号重复!:" + materialType.getTypeCode());
        return materialTypeMapper.insert(materialType) > 0;
    }

    /**
     * 更新
     *
     * @param materialType 物料类型po
     * @return boolean
     */
    @Override
    public Boolean update(MaterialType materialType) {
        String typeId = materialType.getId();
        MaterialType oldValue = materialTypeMapper.selectById(typeId);
        if (oldValue == null) throw new DataNotFindException("更新失败,物料类型不存在,id:" + typeId);
        String typeCode = materialType.getTypeCode();
        String typeName = materialType.getTypeName();

        if (!StringUtil.isNullOrEmpty(typeCode) && !oldValue.getTypeCode().equals(typeCode)) {
            QueryWrapper<MaterialType> byTypeCode = new QueryWrapper<>();
            byTypeCode.eq("type_code", typeCode).last("limit 1");
            if (null != materialTypeMapper.selectOne(byTypeCode))
                throw new DataDuplicateException("更新失败,物料类型代号重复:" + typeCode);
            oldValue.setTypeCode(typeCode);
        }
        if (!StringUtil.isNullOrEmpty(typeName)) oldValue.setTypeName(typeName);

        return materialTypeMapper.updateById(oldValue) > 0;
    }

    /**
     * 自动新增物料类型 ,解码类型名称
     *
     * @param typeCodeListStr 类型全称 顺序  父类->子类
     * @return list 顺序 父类->子类
     */
    @Override
    @Transactional
    public List<MaterialType> autoAdd(String typeCodeListStr) {
        List<String> typeCodeList = decodeTypeCode(typeCodeListStr);
        log.info("物料类型解析为:{}", typeCodeList.toString());
        //默认子类存在
        int hasValuePosition = typeCodeList.size() - 1;
        //已经保存的类型
        MaterialType hasValueMaterialType = new MaterialType();
        //逆序查
        for (int i = typeCodeList.size() - 1; i >= 0; i--) {
            QueryWrapper<MaterialType> byTypeCode = new QueryWrapper<>();
            byTypeCode.eq("type_code", typeCodeList.get(i)).last("limit 1");
            MaterialType oldValue = materialTypeMapper.selectOne(byTypeCode);
            if (oldValue == null)
                hasValuePosition--;
            else {
                hasValuePosition = i;
                hasValueMaterialType = oldValue;
                break;
            }
        }
        //如果表中都存在,那就不用新增了
        List<MaterialType> newMaterialTypeList = new ArrayList<>();
        newMaterialTypeList.add(hasValueMaterialType);
        for (int i = hasValuePosition + 1; i < typeCodeList.size(); i++) {
            String newTypeCode = typeCodeList.get(i);
            //开始新增
            MaterialType newValue = MaterialType.builder()
                    .typeCode(newTypeCode)
                    .typeName("未知类型名称")
                    .autoFlag(1)
                    .level(hasValueMaterialType.getLevel() == null ? 1 : hasValueMaterialType.getLevel() + 1)
                    .parentId(hasValueMaterialType.getId() == null ? null : Integer.valueOf(hasValueMaterialType.getId())).build();
            materialTypeMapper.insert(newValue);
            newMaterialTypeList.add(newValue);
            log.info("发现新类型,新增:{}", newTypeCode);
            //新增后交给下一位
            hasValueMaterialType = newValue;
        }
        log.info("完整物料类型:{}",newMaterialTypeList.toString());
        log.info("策略保存类型链结束****************************");
        return newMaterialTypeList;
    }

    /**
     * 根据主键删除
     *
     * @param id     主键
     * @return boolean
     */
    @Override
    public Boolean delete(Integer id) {
        try{
            return materialTypeMapper.deleteById(id) > 0;
        }catch (Exception e){
            throw new DataAssociationException("删除失败,数据");
        }
    }

    //递归查询
    private JSONArray queryRecursive(JSONArray childTree, List<MaterialType> childList) {
        childList.forEach(e -> {
            JSONObject o = new JSONObject();
            o.put("id", e.getId());
            o.put("level", e.getLevel());
            o.put("typeCode", e.getTypeCode());
            o.put("typeName", e.getTypeName());
            o.put("autoFlag", e.getAutoFlag());
            JSONArray children = getTypeTree(Integer.valueOf(e.getId()));  //递归调用此方法
            if (!children.isEmpty()) {
                o.put("children", children);
            }
            childTree.fluentAdd(o);
        });
        return childTree;
    }

    //查询所有子节点
    private List<MaterialType> getNodeById(Integer id) {
        MaterialType materialType = materialTypeMapper.selectById(id);
        if (materialType == null) return null;
        QueryWrapper<MaterialType> byParentId = new QueryWrapper<>();
        byParentId.eq("parent_id", materialType.getId());
        //当前层级当前点下的所有子节点（实战中不要慢慢去查,一次加载到集合然后慢慢处理）
        return materialTypeMapper.selectList(byParentId);
    }

    //解码物料类型
    private static List<String> decodeTypeCode(String typeStr) {
        Queue<Character> typeQueue = new LinkedList<>();
        List<String> typeList = new ArrayList<>();
        for (Character c : typeStr.toCharArray()) {
            if (!(c.equals('(') || c.equals(')'))) typeQueue.offer(c);
            else {
                StringBuilder type = new StringBuilder();
                while (typeQueue.peek() != null) {
                    type.append(typeQueue.poll());
                }
                if (type.toString().length() > 0) typeList.add(type.toString());
            }
        }
        StringBuilder type = new StringBuilder();
        while (typeQueue.peek() != null) {
            type.append(typeQueue.poll());
        }
        if (type.toString().length() > 0) typeList.add(type.toString());

        return typeList;
    }

}
