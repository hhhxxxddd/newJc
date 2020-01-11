package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoRawmaterial;
import com.jinchi.common.domain.BasicInfoRawmaterialExample;
import com.jinchi.common.domain.BasicInfoRawmaterialLineWeight;
import com.jinchi.common.domain.BasicInfoRawmaterialLineWeightExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.RawmaterialLineWeightDTO;
import com.jinchi.common.dto.WeightDTO;
import com.jinchi.common.mapper.BasicInfoRawmaterialLineWeightMapper;
import com.jinchi.common.mapper.BasicInfoRawmaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-31 19:21
 * @description:
 **/
@Service
public class PrecursorRawmaterialLineWeightServiceImp implements PrecursorRawmaterialLineWeightService {

    @Autowired
    BasicInfoRawmaterialLineWeightMapper weightMapper;

    @Autowired
    BasicInfoRawmaterialMapper rawmaterialMapper;

    @Autowired
    PrecursorMaterialTypeService typeService;

    @Autowired
    PrecursorProductionLineService precursorProductionLineService;

    @Override
    public RawmaterialLineWeightDTO add(RawmaterialLineWeightDTO dto) {
        int materialCode = dto.getMaterialCode();

        BasicInfoRawmaterialLineWeightExample example = new BasicInfoRawmaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(materialCode);
        int count = weightMapper.selectByExample(example).size();
        if (count > 0) {
            return null;
        }

        List<WeightDTO> weightDTOS = dto.getWeightDTOS();
        for (WeightDTO weightDTO : weightDTOS) {
            BasicInfoRawmaterialLineWeight weight = new BasicInfoRawmaterialLineWeight();
            weight.setMaterialCode(materialCode);
            weight.setLineCode(weightDTO.getLineCode());
            weight.setWeightValue(weightDTO.getWeightValue());
            weightMapper.insertSelective(weight);
        }
        return dto;
    }

    @Override
    public void delete(Integer id) {
        BasicInfoRawmaterialLineWeightExample example = new BasicInfoRawmaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(id);
        weightMapper.deleteByExample(example);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            delete(id);
        }
    }

    @Override
    public List getAll(String condition) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andMaterialNameLike(condition + "%");
        List<BasicInfoRawmaterial> list = rawmaterialMapper.selectByExample(example);

        List<RawmaterialLineWeightDTO> lineWeightDTOS = new ArrayList<>();

        BasicInfoRawmaterialLineWeightExample example1 = new BasicInfoRawmaterialLineWeightExample();
        example.createCriteria();
        List<BasicInfoRawmaterialLineWeight> lineWeights = weightMapper.selectByExample(example1);

        List<Integer> ids = new ArrayList<>();
        for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
            if (!ids.contains(lineWeight.getMaterialCode())) {
                ids.add(lineWeight.getMaterialCode());
            }
        }

        for (BasicInfoRawmaterial details : list) {
            if (ids.contains(details.getCode())) {
                lineWeightDTOS.add(getRecordById(details.getCode()));
            }
        }

        return lineWeightDTOS;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(condition), page, size);
        return pageInfo;
    }

    @Override
    public RawmaterialLineWeightDTO getRecordById(Integer id) {
        RawmaterialLineWeightDTO rawmaterialLineWeightDTO = new RawmaterialLineWeightDTO();

        BasicInfoRawmaterial record = rawmaterialMapper.selectByPrimaryKey(id);
        rawmaterialLineWeightDTO.setMaterialCode(record.getCode());
        rawmaterialLineWeightDTO.setMaterialName(record.getMaterialName());
        rawmaterialLineWeightDTO.setDataType(record.getDataType());
        rawmaterialLineWeightDTO.setMaterialTypeCode(record.getTypesCode());
        rawmaterialLineWeightDTO.setMaterialTypeName(typeService.getByCode(record.getTypesCode()).getMaterialTypeName());

        List<WeightDTO> weightDTOS = new ArrayList<>();
        BasicInfoRawmaterialLineWeightExample example = new BasicInfoRawmaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(id);
        List<BasicInfoRawmaterialLineWeight> lineWeights = weightMapper.selectByExample(example);

        for (BasicInfoRawmaterialLineWeight weight : lineWeights) {
            WeightDTO weightDTO = new WeightDTO();
            weightDTO.setLineCode(weight.getLineCode());
            weightDTO.setWeightValue(weight.getWeightValue());
            weightDTO.setLineName(precursorProductionLineService.getNameById(weight.getLineCode()));
            weightDTOS.add(weightDTO);
        }
        rawmaterialLineWeightDTO.setWeightDTOS(weightDTOS);

        return rawmaterialLineWeightDTO;
    }

    @Override
    public RawmaterialLineWeightDTO update(RawmaterialLineWeightDTO rawmaterialLineWeightDTO) {
        int materialCode = rawmaterialLineWeightDTO.getMaterialCode();
        BasicInfoRawmaterialLineWeightExample example = new BasicInfoRawmaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(materialCode);
        weightMapper.deleteByExample(example);
        add(rawmaterialLineWeightDTO);
        return rawmaterialLineWeightDTO;
    }
}
