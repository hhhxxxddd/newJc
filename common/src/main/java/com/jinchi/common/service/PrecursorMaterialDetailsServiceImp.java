package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetailsExample;
import com.jinchi.common.domain.BasicInfoPrecursorProcessType;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.PrecursorMaterialDetailsDTO;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-26 19:48
 * @description:
 **/
@Service
public class PrecursorMaterialDetailsServiceImp implements PrecursorMaterialDetailsService{

    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper precursorMaterialDetailsMapper;

    @Autowired
    PrecursorProcessTypeService precursorProcessTypeService;

    @Override
    public BasicInfoPrecursorMaterialDetails add(BasicInfoPrecursorMaterialDetails basicInfoPrecursorMaterialDetails) {
        precursorMaterialDetailsMapper.insertSelective(basicInfoPrecursorMaterialDetails);
        return basicInfoPrecursorMaterialDetails;
    }

    @Override
    public void delete(Integer id) {
        precursorMaterialDetailsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids){
            delete(id);
        }
    }

    @Override
    public BasicInfoPrecursorMaterialDetails update(BasicInfoPrecursorMaterialDetails basicInfoPrecursorMaterialDetails) {
        precursorMaterialDetailsMapper.updateByPrimaryKeySelective(basicInfoPrecursorMaterialDetails);
        return basicInfoPrecursorMaterialDetails;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public List getAll(String condition) {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andMaterialNameLike(condition+"%");
        List<BasicInfoPrecursorMaterialDetails> detailsList = precursorMaterialDetailsMapper.selectByExample(example);
        List<PrecursorMaterialDetailsDTO> detailsDTOS = new ArrayList<>();

        List<BasicInfoPrecursorProcessType> all = precursorProcessTypeService.getAll();
        Map<Integer, String> map = new HashMap<>();
        for (BasicInfoPrecursorProcessType processType : all) {
            map.put(processType.getCode(), processType.getProcessName());
        }

        for (BasicInfoPrecursorMaterialDetails details : detailsList){
            PrecursorMaterialDetailsDTO detailsDTO = new PrecursorMaterialDetailsDTO();
            settings(detailsDTO, details, map);
            detailsDTOS.add(detailsDTO);
        }
        return detailsDTOS;
    }

    @Override
    public Page getByType(Byte type, Integer page, Integer size, String condition) {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andTypesEqualTo(type).andMaterialNameLike(condition + "%");
        List<BasicInfoPrecursorMaterialDetails> detailsList = precursorMaterialDetailsMapper.selectByExample(example);
        List<PrecursorMaterialDetailsDTO> detailsDTOS = new ArrayList<>();
        List<BasicInfoPrecursorProcessType> all = precursorProcessTypeService.getAll();
        Map<Integer, String> map = new HashMap<>();
        for (BasicInfoPrecursorProcessType processType : all) {
            map.put(processType.getCode(), processType.getProcessName());
        }
        for (BasicInfoPrecursorMaterialDetails details : detailsList) {
            PrecursorMaterialDetailsDTO detailsDTO = new PrecursorMaterialDetailsDTO();
            settings(detailsDTO, details, map);
            detailsDTOS.add(detailsDTO);
        }
        return new Page(detailsDTOS, page, size);
    }

    @Override
    public PrecursorMaterialDetailsDTO getOne(Integer id) {
        BasicInfoPrecursorMaterialDetails details = precursorMaterialDetailsMapper.selectByPrimaryKey(id);
        List<BasicInfoPrecursorProcessType> all = precursorProcessTypeService.getAll();
        Map<Integer, String> map = new HashMap<>();
        for (BasicInfoPrecursorProcessType processType : all) {
            map.put(processType.getCode(), processType.getProcessName());
        }
        PrecursorMaterialDetailsDTO detailsDTO = new PrecursorMaterialDetailsDTO();
        settings(detailsDTO, details, map);
        return detailsDTO;
    }

    public void settings(PrecursorMaterialDetailsDTO detailsDTO, BasicInfoPrecursorMaterialDetails details, Map<Integer, String> map) {
        detailsDTO.setCode(details.getCode());
        detailsDTO.setMaterialName(details.getMaterialName());
        detailsDTO.setDataType(details.getDataType());
        detailsDTO.setValueType(details.getValueType());
        detailsDTO.setProcessCode(details.getProcessCode());
        detailsDTO.setProcessName(map.get(details.getProcessCode()));
        detailsDTO.setTypes(details.getTypes());
        detailsDTO.setMn(details.getMn());
        detailsDTO.setCo(details.getCo());
        detailsDTO.setNi(details.getNi());
        detailsDTO.setAmm(details.getAmmoniaFlag());
        detailsDTO.setAlk(details.getAlkaliFlag());
    }

    @Override
    public List<BasicInfoPrecursorMaterialDetails> getDataByProcessAndType(Integer processCode, Byte types) {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andTypesEqualTo(types).andProcessCodeEqualTo(processCode);
        return precursorMaterialDetailsMapper.selectByExample(example);
    }

    @Override
    public List getMaterialByProcessType(Integer processCode) {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andProcessCodeEqualTo(processCode);
        return precursorMaterialDetailsMapper.selectByExample(example);
    }

    @Override
    public List getHCMaterial() {
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andProcessCodeEqualTo(3);
        return precursorMaterialDetailsMapper.selectByExample(example);
    }
}
