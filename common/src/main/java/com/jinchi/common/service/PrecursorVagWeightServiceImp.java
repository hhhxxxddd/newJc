package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.BasicInfoPrecursorProductionLineDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.VgaWeightDTO;
import com.jinchi.common.mapper.BasicInfoPrecursorProductionLineMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorVgaLineWeightMapper;
import com.jinchi.common.mapper.BasicInfoVgaPointMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrecursorVagWeightServiceImp implements PrecursorVagWeightService{

    @Autowired
    BasicInfoPrecursorVgaLineWeightMapper weightMapper;
    @Autowired
    BasicInfoVgaPointMapper pointMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;

    @Override
    public String add(List<BasicInfoPrecursorVgaLineWeight> weightList) {
        BasicInfoPrecursorVgaLineWeightExample example = new BasicInfoPrecursorVgaLineWeightExample();
        example.createCriteria().andVgaCodeEqualTo(weightList.get(0).getVgaCode());
        List<BasicInfoPrecursorVgaLineWeight> weights = weightMapper.selectByExample(example);
        if(weights.size()>0){
            return "新增失败，已存在VGA产线权重";
        }
        for(int i=0;i<weightList.size();i++){
            weightMapper.insertSelective(weightList.get(i));
        }
        return "新增成功";
    }

    @Override
    public List<BasicInfoPrecursorVgaLineWeight> update(List<BasicInfoPrecursorVgaLineWeight> weightList) {
        Integer vgaId = weightList.get(0).getVgaCode();
        BasicInfoPrecursorVgaLineWeightExample example = new BasicInfoPrecursorVgaLineWeightExample();
        example.createCriteria().andVgaCodeEqualTo(vgaId);
        weightMapper.deleteByExample(example);
        add(weightList);
        return weightList;
    }

    @Override
    public void delete(Integer vgaId) {
        BasicInfoPrecursorVgaLineWeightExample example = new BasicInfoPrecursorVgaLineWeightExample();
        example.createCriteria().andVgaCodeEqualTo(vgaId);
        weightMapper.deleteByExample(example);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer i:ids){
            delete(i);
        }
    }

    @Override
    public List getAll(String condition) {
        String sql = "select distinct vga_code from basic_info_precursor_VGA_line_weight as lw,basic_info_VGA_point as vp";
        if(!"".equals(condition)){
            sql += " where (lw.vga_code = vp.code and vp.vga_name like '"+condition+"%')";
        }
        List<BasicInfoPrecursorVgaLineWeight> weights = weightMapper.selectBycondition(sql);
        List<VgaWeightDTO> ans = new ArrayList<>();
        for(int i=0;i<weights.size();i++){
            VgaWeightDTO weightDTO = new VgaWeightDTO();
            BasicInfoVgaPoint point = pointMapper.selectByPrimaryKey(weights.get(i).getVgaCode());
            weightDTO.setVgaPoint(point);
            ans.add(weightDTO);
        }
        for(int i=0;i<ans.size();i++){
            Integer vgaId = ans.get(i).getVgaPoint().getCode();
            BasicInfoPrecursorVgaLineWeightExample example = new BasicInfoPrecursorVgaLineWeightExample();
            example.createCriteria().andVgaCodeEqualTo(vgaId);
            List<BasicInfoPrecursorVgaLineWeight> temp = weightMapper.selectByExample(example);
            List<BasicInfoPrecursorProductionLineDTO> lines = new ArrayList<>();
            for(int j=0;j<temp.size();j++){
                BasicInfoPrecursorProductionLineDTO lineDTO = new BasicInfoPrecursorProductionLineDTO();
                BasicInfoPrecursorProductionLine line = lineMapper.selectByPrimaryKey(temp.get(j).getLineCode());
                lineDTO.setCode(temp.get(j).getLineCode());
                lineDTO.setName(line.getName());
                lineDTO.setValue(temp.get(j).getWeightValue());
                lines.add(lineDTO);
            }
            ans.get(i).setLines(lines);
        }
        return ans;
    }

    @Override
    public Page getAllByPage(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public VgaWeightDTO getInfoByVgaId(Integer vgaId) {
        VgaWeightDTO ans = new VgaWeightDTO();
        List<BasicInfoPrecursorProductionLineDTO> lineDTOS = new ArrayList<>();

        ans.setVgaPoint(pointMapper.selectByPrimaryKey(vgaId));
        BasicInfoPrecursorVgaLineWeightExample example = new BasicInfoPrecursorVgaLineWeightExample();
        example.createCriteria().andVgaCodeEqualTo(vgaId);
        List<BasicInfoPrecursorVgaLineWeight> lines = weightMapper.selectByExample(example);
        for(int i=0;i<lines.size();i++){
            BasicInfoPrecursorProductionLineDTO dto = new BasicInfoPrecursorProductionLineDTO();
            BasicInfoPrecursorProductionLine line = lineMapper.selectByPrimaryKey(lines.get(i).getLineCode());
            dto.setCode(line.getCode());
            dto.setName(line.getName());
            dto.setValue(lines.get(i).getWeightValue());
            lineDTOS.add(dto);
        }
        ans.setLines(lineDTOS);
        return ans;
    }
}
