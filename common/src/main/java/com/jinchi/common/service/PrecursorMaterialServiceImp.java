package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetailsExample;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialLineWeight;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialLineWeightExample;
import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.WeightDTO;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialDetailsMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialLineWeightMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrecursorMaterialServiceImp implements PrecursorMaterialService {

    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper detailsMapper;

    @Autowired
    BasicInfoPrecursorMaterialLineWeightMapper weightMapper;

    @Autowired
    PrecursorProcessTypeService precursorProcessTypeService;

    @Autowired
    PrecursorProductionLineService precursorProductionLineService;

    @Override
    public BasicInfoPrecursorMaterialDetails getById(Integer id) {
        return detailsMapper.selectByPrimaryKey(id);
    }

//    @Override
//    public BasicInfoPrecursorMaterialLineWeight add(BasicInfoPrecursorMaterialLineWeight basicInfoPrecursorMaterialLineWeight) {
//        weightMapper.insertSelective(basicInfoPrecursorMaterialLineWeight);
//        return basicInfoPrecursorMaterialLineWeight;
//    }


    @Override
    public LineWeightDTO getRecordById(Integer id) {
        LineWeightDTO lineWeightDTO = new LineWeightDTO();
        BasicInfoPrecursorMaterialDetails materialDetails = detailsMapper.selectByPrimaryKey(id);
        lineWeightDTO.setMaterialCode(materialDetails.getCode());
        lineWeightDTO.setMaterialName(materialDetails.getMaterialName());
        lineWeightDTO.setTypes(materialDetails.getTypes());
        lineWeightDTO.setProcessCode(materialDetails.getProcessCode());
        lineWeightDTO.setProcessName(precursorProcessTypeService.getProcessNameById(materialDetails.getProcessCode()));
        List<WeightDTO> weightDTOS = new ArrayList<>();
        BasicInfoPrecursorMaterialLineWeightExample example = new BasicInfoPrecursorMaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(id);
        List<BasicInfoPrecursorMaterialLineWeight> lineWeights = weightMapper.selectByExample(example);
        for(BasicInfoPrecursorMaterialLineWeight lineWeight : lineWeights){
            WeightDTO weightDTO = new WeightDTO();
            weightDTO.setLineCode(lineWeight.getLineCode());
            weightDTO.setWeightValue(lineWeight.getWeightValue());
            weightDTO.setLineName(precursorProductionLineService.getNameById(lineWeight.getLineCode()));
            weightDTOS.add(weightDTO);
        }
        lineWeightDTO.setWeightDTOS(weightDTOS);
        return lineWeightDTO;
    }

    @Override
    public LineWeightDTO add(LineWeightDTO lineWeightDTO) {
        List<WeightDTO> weightDTOS = lineWeightDTO.getWeightDTOS();
//        double sum = 0;
        int materialCode = lineWeightDTO.getMaterialCode();
        BasicInfoPrecursorMaterialLineWeightExample example = new BasicInfoPrecursorMaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(materialCode);
        int count = weightMapper.selectByExample(example).size();
        if (count > 0){
            return null;
        }
        for (WeightDTO dto : weightDTOS){
            BasicInfoPrecursorMaterialLineWeight lineWeight = new BasicInfoPrecursorMaterialLineWeight();
            lineWeight.setMaterialCode(materialCode);
            lineWeight.setLineCode(dto.getLineCode());
            lineWeight.setWeightValue(dto.getWeightValue());
//            sum += dto.getWeightValue();
            weightMapper.insertSelective(lineWeight);
        }
        //判断权重值之和是否为1
//        if (sum != 1){
//            return new LineWeightDTO();
//        }
        return lineWeightDTO;
    }

    @Override
    public void delete(Integer id) {
        BasicInfoPrecursorMaterialLineWeightExample example = new BasicInfoPrecursorMaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(id);
        weightMapper.deleteByExample(example);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids)
            delete(id);
    }

    @Override
    public List getAll(String condition) {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andMaterialNameLike(condition+"%");
        List<BasicInfoPrecursorMaterialDetails> list = detailsMapper.selectByExample(example);
        List<LineWeightDTO> weights = new ArrayList<>();

        BasicInfoPrecursorMaterialLineWeightExample example1 = new BasicInfoPrecursorMaterialLineWeightExample();
        example1.createCriteria();
        List<BasicInfoPrecursorMaterialLineWeight> lineWeights = weightMapper.selectByExample(example1);
        List<Integer> ids = new ArrayList<>();
        for (BasicInfoPrecursorMaterialLineWeight lineWeight : lineWeights){
            if (!ids.contains(lineWeight.getMaterialCode())){
                ids.add(lineWeight.getMaterialCode());
            }
        }
//        System.out.println(ids);
        for(BasicInfoPrecursorMaterialDetails details : list){
            if (ids.contains(details.getCode())){
                weights.add(getRecordById(details.getCode()));
            }
        }
        return weights;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

//    @Override
//    public BasicInfoPrecursorMaterialLineWeight update(BasicInfoPrecursorMaterialLineWeight basicInfoPrecursorMaterialLineWeight) {
//        weightMapper.updateByPrimaryKeySelective(basicInfoPrecursorMaterialLineWeight);
//        return basicInfoPrecursorMaterialLineWeight;
//    }


    @Override
    public LineWeightDTO update(LineWeightDTO lineWeightDTO) {
        int materialCode = lineWeightDTO.getMaterialCode();
        BasicInfoPrecursorMaterialLineWeightExample example = new BasicInfoPrecursorMaterialLineWeightExample();
        example.createCriteria().andMaterialCodeEqualTo(materialCode);
        weightMapper.deleteByExample(example);
        add(lineWeightDTO);
        return lineWeightDTO;
    }
}
