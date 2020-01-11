package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoMaterialType;
import com.jinchi.common.domain.BasicInfoMaterialTypeExample;
import com.jinchi.common.domain.BasicInfoRawmaterial;
import com.jinchi.common.domain.BasicInfoRawmaterialExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.RawmaterialsDTO;
import com.jinchi.common.mapper.BasicInfoMaterialTypeMapper;
import com.jinchi.common.mapper.BasicInfoRawmaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-28 22:15
 * @description:
 **/
@Service
public class PrecursorRawmaterialServiceImp implements PrecursorRawmaterialService {

    @Autowired
    BasicInfoRawmaterialMapper rawmaterialMapper;
    @Autowired
    BasicInfoMaterialTypeMapper typeMapper;

    @Override
    public BasicInfoRawmaterial add(BasicInfoRawmaterial basicInfoRawmaterial) {
        rawmaterialMapper.insertSelective(basicInfoRawmaterial);
        return basicInfoRawmaterial;
    }

    @Override
    public List getAll(String condition) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andMaterialNameLike(condition + "%");
        List<BasicInfoRawmaterial> basicInfoRawmaterials = rawmaterialMapper.selectByExample(example);

        BasicInfoMaterialTypeExample example1 = new BasicInfoMaterialTypeExample();
        example1.createCriteria();
        List<BasicInfoMaterialType> basicInfoMaterialTypes = typeMapper.selectByExample(example1);

        Map<Integer, String> map = new HashMap<>();
        basicInfoMaterialTypes.forEach(type -> map.put(type.getCode(), type.getMaterialTypeName()));

        List<RawmaterialsDTO> ans = new ArrayList<>();
        for (BasicInfoRawmaterial item : basicInfoRawmaterials) {
            RawmaterialsDTO dto = new RawmaterialsDTO();
            settings(map, dto, item);
            ans.add(dto);
        }
        return ans;
    }

    private void settings(Map<Integer, String> map, RawmaterialsDTO dto, BasicInfoRawmaterial item) {
        dto.setCode(item.getCode());
        dto.setCoFlag(item.getCoFlag());
        dto.setDataType(item.getDataType());
        dto.setMaterialName(item.getMaterialName());
        dto.setMnFlag(item.getMnFlag());
        dto.setNiFlag(item.getNiFlag());
        dto.setPhaseType(item.getPhaseType());
        dto.setPickingType(item.getPickingType());
        dto.setTypesCode(item.getTypesCode());
        dto.setTypeName(map.get(item.getTypesCode()));
    }

    @Override
    public void deleteById(Integer id) {
        rawmaterialMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(condition), page, size);
        return pageInfo;
    }

    @Override
    public BasicInfoRawmaterial update(BasicInfoRawmaterial basicInfoRawmaterial) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andCodeEqualTo(basicInfoRawmaterial.getCode());
        rawmaterialMapper.updateByExampleSelective(basicInfoRawmaterial, example);
        return basicInfoRawmaterial;
    }

    @Override
    public RawmaterialsDTO getById(Integer id) {
        BasicInfoMaterialTypeExample example1 = new BasicInfoMaterialTypeExample();
        example1.createCriteria();
        List<BasicInfoMaterialType> basicInfoMaterialTypes = typeMapper.selectByExample(example1);

        Map<Integer, String> map = new HashMap<>();
        basicInfoMaterialTypes.forEach(type -> map.put(type.getCode(), type.getMaterialTypeName()));

        BasicInfoRawmaterial item = rawmaterialMapper.selectByPrimaryKey(id);
        RawmaterialsDTO dto = new RawmaterialsDTO();

        settings(map, dto, item);

        return dto;
    }

    @Override
    public List getByTypeCode(Integer type) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andTypesCodeEqualTo(type);
        return rawmaterialMapper.selectByExample(example);
    }

    @Override
    public List getByDatatype(Integer flag) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andDataTypeEqualTo(flag.byteValue());
        return rawmaterialMapper.selectByExample(example);
    }
}
