package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoCompoundCellVolumes;
import com.jinchi.common.domain.BasicInfoCompoundCellVolumesExample;
import com.jinchi.common.dto.CompoundCellDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoCompoundCellVolumesMapper;
import com.jinchi.common.mapper.BasicInfoPrecursorProductionLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-01 11:27
 * @description:
 **/
@Service
public class PrecursorCompoundCellVolumesServiceImp implements PrecursorCompoundCellVolumesService {

    @Autowired
    BasicInfoCompoundCellVolumesMapper volumesMapper;

    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;

    @Override
    public BasicInfoCompoundCellVolumes add(BasicInfoCompoundCellVolumes basicInfoCompoundCellVolumes) {
        volumesMapper.insertSelective(basicInfoCompoundCellVolumes);
        return basicInfoCompoundCellVolumes;
    }

    @Override
    public BasicInfoCompoundCellVolumes getOne(Integer id) {
        return volumesMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        volumesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public List getAll(String condition) {
        BasicInfoCompoundCellVolumesExample example = new BasicInfoCompoundCellVolumesExample();
        example.createCriteria().andMaterialNameLike(condition + "%");
        List<BasicInfoCompoundCellVolumes> volumes = volumesMapper.selectByExample(example);
        List<CompoundCellDTO> compoundCellDTOS = new ArrayList<>();
        volumes.forEach(volume -> {
            CompoundCellDTO dto = new CompoundCellDTO();
            dto.setCode(volume.getCode());
            dto.setLineCode(volume.getLineCode());
            dto.setLineName(lineMapper.selectByPrimaryKey(volume.getLineCode()).getName());
            dto.setMaterialCode(volume.getMaterialCode());
            dto.setMaterialName(volume.getMaterialName());
            dto.setVolumesValue(volume.getVolumesValue());
            compoundCellDTOS.add(dto);
        });
        return compoundCellDTOS;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        Page pageInfo = new Page(getAll(condition), page, size);
        return pageInfo;
    }

    @Override
    public BasicInfoCompoundCellVolumes update(BasicInfoCompoundCellVolumes basicInfoCompoundCellVolumes) {
        BasicInfoCompoundCellVolumesExample example = new BasicInfoCompoundCellVolumesExample();
        example.createCriteria().andCodeEqualTo(basicInfoCompoundCellVolumes.getCode());
        volumesMapper.updateByExampleSelective(basicInfoCompoundCellVolumes, example);
        return basicInfoCompoundCellVolumes;
    }

    @Override
    public Float getSumOfVolumesValue() {
        BasicInfoCompoundCellVolumesExample example = new BasicInfoCompoundCellVolumesExample();
        example.createCriteria();
        List<BasicInfoCompoundCellVolumes> basicInfoCompoundCellVolumes = volumesMapper.selectByExample(example);
        float totals = 0;
        for (BasicInfoCompoundCellVolumes volumes : basicInfoCompoundCellVolumes) {
            totals += volumes.getVolumesValue();
        }
        return totals;
    }
}
