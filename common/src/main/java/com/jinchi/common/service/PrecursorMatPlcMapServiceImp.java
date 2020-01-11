package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoPrecursorMaterialDetails;
import com.jinchi.common.domain.BasicInfoPrecursorMaterialPlcMap;
import com.jinchi.common.domain.BasicInfoPrecursorPlcAddress;
import com.jinchi.common.dto.MatPlcDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.BasicInfoPrecursorMaterialPlcMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrecursorMatPlcMapServiceImp implements PrecursorMatPlcMapService{
    @Autowired
    BasicInfoPrecursorMaterialPlcMapMapper materialPlcMapMapper;
    @Autowired
    PrecursorPlcAddressService addressService;
    @Autowired
    PrecursorMaterialService typeService;
    @Override
    public BasicInfoPrecursorMaterialPlcMap add(BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap) {
       materialPlcMapMapper.insertSelective(basicInfoPrecursorMaterialPlcMap);
        return basicInfoPrecursorMaterialPlcMap;
    }

    @Override
    public List getAll(String condition) {
        String sql = "select distinct mp.* from basic_info_precursor_material_PLC_map as mp,basic_info_precursor_PLC_address as p,basic_info_precursor_material_details as m";
        if(!"".equals(condition)){
            sql += " where mp.material_code = m.code and mp.plc_code = p.code and (m.material_name like '" + condition + "%' or p.address like '" + condition + "%')";
        }
        List<MatPlcDTO> ans = new ArrayList<>();
        List<BasicInfoPrecursorMaterialPlcMap> maps = materialPlcMapMapper.selectByconditions(sql);
        System.out.println(maps.size());
        for(int i=0;i<maps.size();i++){
            MatPlcDTO mp = new MatPlcDTO();
            Integer mId = maps.get(i).getMaterialCode();
            Integer pId = maps.get(i).getPlcCode();
            BasicInfoPrecursorPlcAddress address = addressService.getById(pId);
            BasicInfoPrecursorMaterialDetails details = typeService.getById(mId);
            mp.setMaterialId(mId);
            mp.setPlcId(pId);
            mp.setMaterial(details.getMaterialName());
            mp.setPlcAddress(address.getAddress());
            mp.setCode(maps.get(i).getCode());
            ans.add(mp);
        }
        return ans;
    }

    @Override
    public Page getAllByPage(String condition, Integer page, Integer size) {
        List<BasicInfoPrecursorMaterialPlcMap> info = getAll(condition);
        return new Page(info,page,size);
    }

    @Override
    public void delete(Integer id) {
        materialPlcMapMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for(Integer i:ids){
            delete(i);
        }
    }

    @Override
    public BasicInfoPrecursorMaterialPlcMap update(BasicInfoPrecursorMaterialPlcMap basicInfoPrecursorMaterialPlcMap) {
        materialPlcMapMapper.updateByPrimaryKeySelective(basicInfoPrecursorMaterialPlcMap);
        return basicInfoPrecursorMaterialPlcMap;
    }

    @Override
    public MatPlcDTO getById(Integer id) {
        BasicInfoPrecursorMaterialPlcMap map = materialPlcMapMapper.selectByPrimaryKey(id);
        MatPlcDTO matPlcDTO = new MatPlcDTO();
        BasicInfoPrecursorPlcAddress address = addressService.getById(map.getPlcCode());
        BasicInfoPrecursorMaterialDetails details = typeService.getById(map.getMaterialCode());
        matPlcDTO.setCode(map.getCode());
        matPlcDTO.setMaterialId(map.getMaterialCode());
        matPlcDTO.setPlcId(map.getPlcCode());
        matPlcDTO.setMaterial(details.getMaterialName());
        matPlcDTO.setPlcAddress(address.getAddress());
        return matPlcDTO;
    }
}
