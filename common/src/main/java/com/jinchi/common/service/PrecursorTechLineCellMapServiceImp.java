package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoTechLineCellMap;
import com.jinchi.common.domain.BasicInfoTechLineCellMapExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PrecursorMaterialDTO;
import com.jinchi.common.dto.TechLineCellMapDTO;
import com.jinchi.common.mapper.BasicInfoTechLineCellMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-10-15 19:35
 * @description:
 **/
@Service
public class PrecursorTechLineCellMapServiceImp implements PrecursorTechLineCellMapService{

    @Autowired
    BasicInfoTechLineCellMapMapper mapMapper;

    @Override
    public TechLineCellMapDTO add(TechLineCellMapDTO techLineCellMapDTO) {
        int lineCode = techLineCellMapDTO.getLineCode();
        String lineName = techLineCellMapDTO.getLineName();

        List<PrecursorMaterialDTO> materialDTOS = techLineCellMapDTO.getMaterialDTOS();
        BasicInfoTechLineCellMapExample example = new BasicInfoTechLineCellMapExample();
        example.createCriteria().andLineCodeEqualTo(lineCode);

        List<Integer> materialCodes = new ArrayList<>();
        materialDTOS.forEach(materialDTO->materialCodes.add(materialDTO.getMaterialCode()));
        BasicInfoTechLineCellMapExample example1 = new BasicInfoTechLineCellMapExample();
        example1.createCriteria().andMaterialCodeIn(materialCodes);

        if (mapMapper.selectByExample(example).size() > 0){
            return new TechLineCellMapDTO();
        }

        if (mapMapper.selectByExample(example1).size() > 0){
            return new TechLineCellMapDTO();
        }

        for (PrecursorMaterialDTO materialDTO : materialDTOS){
            BasicInfoTechLineCellMap techLineCellMap = new BasicInfoTechLineCellMap();
            techLineCellMap.setLineCode(lineCode);
            techLineCellMap.setLineName(lineName);
            techLineCellMap.setMaterialCode(materialDTO.getMaterialCode());
            techLineCellMap.setMaterialName(materialDTO.getMaterialName());
            mapMapper.insertSelective(techLineCellMap);
        }
        return techLineCellMapDTO;
    }

    @Override
    public void deleteById(Integer lineCode) {
        BasicInfoTechLineCellMapExample example = new BasicInfoTechLineCellMapExample();
        example.createCriteria().andLineCodeEqualTo(lineCode);
        mapMapper.deleteByExample(example);
    }

    @Override
    public void deleteByIds(Integer[] lineCodes) {
        for (Integer lineCode : lineCodes){
            deleteById(lineCode);
        }
    }

    @Override
    public TechLineCellMapDTO update(TechLineCellMapDTO techLineCellMapDTO) {
        BasicInfoTechLineCellMapExample example = new BasicInfoTechLineCellMapExample();
        example.createCriteria().andLineCodeEqualTo(techLineCellMapDTO.getLineCode());
        mapMapper.deleteByExample(example);
        add(techLineCellMapDTO);
        return techLineCellMapDTO;
    }

    @Override
    public TechLineCellMapDTO getByLineCode(Integer lineCode) {
        BasicInfoTechLineCellMapExample example = new BasicInfoTechLineCellMapExample();
        example.createCriteria().andLineCodeEqualTo(lineCode);
        List<BasicInfoTechLineCellMap> maps = mapMapper.selectByExample(example);

        if (maps.size() > 0) {
            TechLineCellMapDTO lineCellMapDTO = new TechLineCellMapDTO();
            lineCellMapDTO.setLineCode(lineCode);
            lineCellMapDTO.setLineName(maps.get(0).getLineName());
            List<PrecursorMaterialDTO> materialDTOS = new ArrayList<>();
            for (BasicInfoTechLineCellMap map : maps) {
                PrecursorMaterialDTO materialDTO = new PrecursorMaterialDTO();
                materialDTO.setMaterialCode(map.getMaterialCode());
                materialDTO.setMaterialName(map.getMaterialName());
                materialDTOS.add(materialDTO);
            }
            lineCellMapDTO.setMaterialDTOS(materialDTOS);
            return lineCellMapDTO;
        } else {
            return null;
        }


    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public List getAll(String condition) {
        BasicInfoTechLineCellMapExample example = new BasicInfoTechLineCellMapExample();
        example.createCriteria().andLineNameLike(condition + "%");
        List<BasicInfoTechLineCellMap> maps = mapMapper.selectByExample(example);
        List<Integer> lineCodes = new ArrayList<>();
        for (BasicInfoTechLineCellMap map : maps){
            if (!lineCodes.contains(map.getLineCode())){
                lineCodes.add(map.getLineCode());
            }
        }
        List<TechLineCellMapDTO> mapDTOS = new ArrayList<>();
        for (Integer lineCode : lineCodes){
            mapDTOS.add(getByLineCode(lineCode));
        }
        return mapDTOS;
    }

    @Override
    public List getByIds(Integer[] lineCodes) {
        List<TechLineCellMapDTO> list = new ArrayList<>();
        for (Integer id : lineCodes) {
            TechLineCellMapDTO byLineCode = getByLineCode(id);
            if (byLineCode != null) {
                list.add(byLineCode);
            }
        }
        return list;
    }
}
