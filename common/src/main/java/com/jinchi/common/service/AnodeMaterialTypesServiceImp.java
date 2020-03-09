package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.LineWeightDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.WeightDTO;
import com.jinchi.common.mapper.BasicInfoAnodeMaterialLineMapMapper;
import com.jinchi.common.mapper.BasicInfoAnodeMaterialTypesMapper;
import com.jinchi.common.mapper.BasicInfoAnodeProcessNameMapper;
import com.jinchi.common.mapper.BasicInfoAnodeProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-25 17:09
 * @description:
 **/
@Service
@Transactional
public class AnodeMaterialTypesServiceImp implements AnodeMaterialTypesService {

    @Autowired
    BasicInfoAnodeMaterialTypesMapper typesMapper;
    @Autowired
    BasicInfoAnodeMaterialLineMapMapper materialLineMapMapper;
    @Autowired
    BasicInfoAnodeProcessNameMapper nameMapper;
    @Autowired
    BasicInfoAnodeProductionLineMapper lineMapper;

    @Override
    public LineWeightDTO add(LineWeightDTO dto) {
        String name = dto.getMaterialName();
        Byte dataType = dto.getTypes();
        Integer processCode = dto.getProcessCode();
        BasicInfoAnodeMaterialTypes materialTypes = new BasicInfoAnodeMaterialTypes();
        materialTypes.setMaterialName(name);
        materialTypes.setDataType(dataType);
        materialTypes.setProcessCode(processCode);
        materialTypes.setFlag(dto.getFlag());

        typesMapper.insertSelective(materialTypes);
        List<WeightDTO> weightDTOS = dto.getWeightDTOS();
        weightDTOS.forEach(item -> {
            BasicInfoAnodeMaterialLineMap map = new BasicInfoAnodeMaterialLineMap();
            map.setMaterialCode(materialTypes.getCode());
            map.setLineCode(item.getLineCode());
            materialLineMapMapper.insertSelective(map);
        });
        return dto;
    }

    @Override
    public LineWeightDTO update(LineWeightDTO dto) {

//        deleteById(dto.getMaterialCode());
//        add(dto);
        //第一步 更新主表
        BasicInfoAnodeMaterialTypes materialTypes = new BasicInfoAnodeMaterialTypes();
        materialTypes.setCode(dto.getMaterialCode());
        materialTypes.setMaterialName(dto.getMaterialName());
        materialTypes.setDataType(dto.getTypes());
        materialTypes.setProcessCode(dto.getProcessCode());
        materialTypes.setFlag(dto.getFlag());
        typesMapper.updateByPrimaryKeySelective(materialTypes);

        //第二步 更新产线映射表
        //先删除
        BasicInfoAnodeMaterialLineMapExample example = new BasicInfoAnodeMaterialLineMapExample();
        example.createCriteria().andMaterialCodeEqualTo(materialTypes.getCode());
        materialLineMapMapper.deleteByExample(example);

        List<WeightDTO> weightDTOS = dto.getWeightDTOS();
        weightDTOS.forEach(item -> {
            BasicInfoAnodeMaterialLineMap map = new BasicInfoAnodeMaterialLineMap();
            map.setMaterialCode(materialTypes.getCode());
            map.setLineCode(item.getLineCode());
            materialLineMapMapper.insertSelective(map);
        });
        return dto;
    }

    @Override
    public void deleteById(Integer id) {

        BasicInfoAnodeMaterialLineMapExample example = new BasicInfoAnodeMaterialLineMapExample();
        example.createCriteria().andMaterialCodeEqualTo(id);
        materialLineMapMapper.deleteByExample(example);
        // 先删从表 再删主表
        typesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List getAll(String condition) {
        List<LineWeightDTO> dtos = new ArrayList<>();
        BasicInfoAnodeMaterialTypesExample example = new BasicInfoAnodeMaterialTypesExample();
        example.createCriteria().andMaterialNameLike(condition + "%");
        List<BasicInfoAnodeMaterialTypes> materialTypes = typesMapper.selectByExample(example);

        BasicInfoAnodeProcessNameExample example1 = new BasicInfoAnodeProcessNameExample();
        example1.createCriteria();
        List<BasicInfoAnodeProcessName> names = nameMapper.selectByExample(example1);

        BasicInfoAnodeProductionLineExample example2 = new BasicInfoAnodeProductionLineExample();
        example2.createCriteria();
        List<BasicInfoAnodeProductionLine> lines = lineMapper.selectByExample(example2);

        Map<Integer, String> mapProcess = new HashMap<>();
        Map<Integer, String> mapLine = new HashMap<>();
        names.forEach(name -> mapProcess.put(name.getCode(), name.getProcessName()));
        lines.forEach(line -> mapLine.put(line.getCode(), line.getName()));


        for (BasicInfoAnodeMaterialTypes item : materialTypes) {
            LineWeightDTO dto = new LineWeightDTO();

            dto.setMaterialCode(item.getCode());
            dto.setMaterialName(item.getMaterialName());
            dto.setTypes(item.getDataType());
            dto.setProcessCode(item.getProcessCode());
            dto.setProcessName(mapProcess.get(item.getProcessCode()));

            dto.setFlag(item.getFlag());

            BasicInfoAnodeMaterialLineMapExample mapExample = new BasicInfoAnodeMaterialLineMapExample();
            mapExample.createCriteria().andMaterialCodeEqualTo(item.getCode());
            List<BasicInfoAnodeMaterialLineMap> materialLineMaps = materialLineMapMapper.selectByExample(mapExample);

            List<WeightDTO> weightDTOS = new ArrayList<>();
            for (BasicInfoAnodeMaterialLineMap map1 : materialLineMaps) {
                WeightDTO dto1 = new WeightDTO();
                dto1.setLineCode(map1.getLineCode());
                dto1.setLineName(mapLine.get(map1.getLineCode()));
                weightDTOS.add(dto1);
            }
            dto.setWeightDTOS(weightDTOS);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition), page, size);
    }
}
