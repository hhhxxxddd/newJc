package com.jinchi.common.service;

import com.jinchi.common.domain.BasicInfoDeviceProcess;
import com.jinchi.common.domain.BasicInfoUserDeviceDeptMap;
import com.jinchi.common.domain.BasicInfoUserDeviceProcessMap;
import com.jinchi.common.domain.BasicInfoUserDeviceProcessMapExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.UserProcessDTO;
import com.jinchi.common.mapper.BasicInfoDeviceProcessMapper;
import com.jinchi.common.mapper.BasicInfoUserDeviceDeptMapMapper;
import com.jinchi.common.mapper.BasicInfoUserDeviceProcessMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProcessServiceImp implements UserProcessService{

    @Autowired
    BasicInfoUserDeviceProcessMapMapper userDeviceProcessMapMapper;
    @Autowired
    BasicInfoDeviceProcessMapper deviceProcessMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    BasicInfoUserDeviceDeptMapMapper userDeviceDeptMapMapper;

    @Transactional
    @Override
    public void update(Integer userId, Integer[] processDeptIds) {

        BasicInfoUserDeviceProcessMapExample example = new BasicInfoUserDeviceProcessMapExample();
        example.createCriteria().andUserCodeEqualTo(userId);
        userDeviceProcessMapMapper.deleteByExample(example);

        for(Integer i:processDeptIds){
            BasicInfoUserDeviceProcessMap map = new BasicInfoUserDeviceProcessMap();
            map.setProcessCode(i.shortValue());
            map.setUserCode(userId);
            userDeviceProcessMapMapper.insertSelective(map);
        }
    }

    @Override
    public Page page(Integer deptId, String condition, Integer page, Integer size) {
        String sql = "select m.* from jc_integration_db.basic_info_user_device_dept_map as m,";
        sql += " jc_auth_db.auth_user as u";
        sql += " where m.auth_code = u.id";
        sql += " and m.dept_code = '" + deptId +"'";
        sql += " and u.username like '" + condition + "%'";
        Integer total = userDeviceDeptMapMapper.count(sql.replaceAll("m\\.\\*","count(*)"));
        sql += " limit " + (page-1)*size + "," + page*size;

        List<BasicInfoUserDeviceDeptMap> maps = userDeviceDeptMapMapper.selectByCondition(sql);
        List<UserProcessDTO> ans = new ArrayList<>();
        for(int i=0;i<maps.size();i++){
            UserProcessDTO processDTO = new UserProcessDTO();
            processDTO.setUser(authUserService.findById(maps.get(i).getAuthCode()));
            BasicInfoUserDeviceProcessMapExample example = new BasicInfoUserDeviceProcessMapExample();
            example.createCriteria().andUserCodeEqualTo(maps.get(i).getAuthCode());
            List<BasicInfoUserDeviceProcessMap> temp = userDeviceProcessMapMapper.selectByExample(example);
            processDTO.setProcesses(new ArrayList<>());
            for(int l=0;l<temp.size();l++) {
                processDTO.getProcesses().add(deviceProcessMapper.selectByPrimaryKey(temp.get(l).getProcessCode()));
            }
            ans.add(processDTO);
        }
        Page pageInfo = new Page(ans,1,size);
        pageInfo.setPage(page);
        pageInfo.setTotal(total);

        return pageInfo;
    }
}
