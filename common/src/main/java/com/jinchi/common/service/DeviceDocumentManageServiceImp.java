package com.jinchi.common.service;

import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.DeviceDocumentMainDTO;
import com.jinchi.common.dto.DeviceMainManualDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceDocumentManageServiceImp implements DeviceDocumentManageService {

    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;
    @Autowired
    DeviceMainAttributelistMapper deviceMainAttributelistMapper;
    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;
    @Autowired
    BasicInfoDeviceStatusMapper basicInfoDeviceStatusMapper;
    @Autowired
    DeviceManualService deviceManualService;
    @Autowired
    DeviceMainAccessoryMapper deviceMainAccessoryMapper;
    @Autowired
    DeviceDocumentUnitMapper deviceDocumentUnitMapper;
    @Autowired
    DeviceUnitAccessoryMapper deviceUnitAccessoryMapper;

    @Override
    public String getSpecificationById(Long id) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andCodeEqualTo(id);
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByExample(example);
        if (deviceDocumentMains.size() > 0) {
            return deviceDocumentMains.get(0).getSpecification();
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Integer> getDeviceNameByDeptId(Integer deptId) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        List<DeviceDocumentMain> l = deviceDocumentMainMapper.selectByExample(example);
        Map<String, Integer> ans = new HashMap<>();
        for (DeviceDocumentMain d : l) {
            String name = d.getDeviceName().split("-")[0];
            if (!ans.containsKey(name)) {
                ans.put(name, 1);
            } else {
                int num = ans.get(name);
                ans.put(name, num + 1);
            }
        }
        return ans;
    }

    @Override
    public List<DeviceDocumentMainDTO> getByDeptIdByDeviceName(Integer deptId, String deviceName, String condition) {
        String sql = "SELECT * FROM device_document_main WHERE (dept_code = " + deptId + ") and (device_name LIKE '" + deviceName + "-%' OR device_name = '" + deviceName + "')";
        if (!condition.equals("")) {
            sql += " AND (fixedassets_code LIKE '" + condition + "%' OR id_code LIKE '" + condition + "%' OR device_name LIKE '" + condition + "%')";
        }
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByConditions(sql);
        List<DeviceDocumentMainDTO> ans = new ArrayList<>();
        for (int i = 0; i < deviceDocumentMains.size(); i++) {
            DeviceDocumentMainDTO deviceDocumentMainDTO = new DeviceDocumentMainDTO();
            BasicInfoDeviceStatusExample example1 = new BasicInfoDeviceStatusExample();
            example1.createCriteria().andCodeEqualTo(deviceDocumentMains.get(i).getStatusCode());
            BasicInfoDeviceStatus basicInfoDeviceStatus = basicInfoDeviceStatusMapper.selectByExample(example1).get(0);
            deviceDocumentMainDTO.setDeviceDocumentMain(deviceDocumentMains.get(i));
            deviceDocumentMainDTO.setBasicInfoDeviceStatus(basicInfoDeviceStatus);
            ans.add(deviceDocumentMainDTO);
        }
        return ans;
    }

    @Override
    @Transactional
    public DeviceDocumentMainDTO addOne(DeviceDocumentMainDTO deviceDocumentMainDTO) {
        deviceDocumentMainMapper.insertSelective(deviceDocumentMainDTO.getDeviceDocumentMain());
        if (deviceDocumentMainDTO.getArrName() != null) {
            for (int i = 0; i < deviceDocumentMainDTO.getArrName().size(); i++) {
                DeviceMainAttributelist d = new DeviceMainAttributelist();
                d.setName(deviceDocumentMainDTO.getArrName().get(i));
                d.setAttValue(deviceDocumentMainDTO.getArrValue().get(i));
                d.setMainCode(deviceDocumentMainDTO.getDeviceDocumentMain().getCode());
                deviceMainAttributelistMapper.insert(d);
            }
        }
        if (deviceDocumentMainDTO.getAdds() != null) {
            for (Long d : deviceDocumentMainDTO.getAdds()) {
                deviceManualService.update(d, deviceDocumentMainDTO.getDeviceDocumentMain().getCode());
            }
        }
        if (deviceDocumentMainDTO.getDels() != null) {
            cancelLoad(deviceDocumentMainDTO.getDels());
        }
        return deviceDocumentMainDTO;

    }

    @Override
    public DeviceMainAccessory updateMainAccessory(DeviceMainAccessory deviceMainAccessory) {
        DeviceMainAccessoryExample deviceMainAccessoryExample = new DeviceMainAccessoryExample();
        deviceMainAccessoryExample.createCriteria().andCodeEqualTo(deviceMainAccessory.getCode());
        deviceMainAccessoryMapper.updateByExampleSelective(deviceMainAccessory, deviceMainAccessoryExample);
        deviceMainAccessoryMapper.updateByExampleSelective(deviceMainAccessory, deviceMainAccessoryExample);
        return deviceMainAccessory;
    }


    @Override
    public String deleteById(Long id) {
        DeviceDocumentUnitExample example1 = new DeviceDocumentUnitExample();
        example1.createCriteria().andMainCodeEqualTo(id);
        if(deviceDocumentUnitMapper.countByExample(example1)!=0){
            return "存在部件，不允许删除！";
        }
        DeviceMainAccessoryExample example2 = new DeviceMainAccessoryExample();
        example2.createCriteria().andMainCodeEqualTo(id);
        if(deviceMainAccessoryMapper.countByExample(example2)!=0){
            return "存在配件，不允许删除！";
        }
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andCodeEqualTo(id);
        deviceDocumentMainMapper.deleteByExample(example);
        return "操作成功！";
    }

    @Override
    public Page getAllByDeptIdByDeviceNameByPage(Integer deptId, String deviceName, String condition, Integer page, Integer size, String fieldName, String orderType) {
        List<DeviceDocumentMainDTO> deviceDocumentMains = getByDeptIdByDeviceName(deptId, deviceName, condition);
        Page<DeviceDocumentMainDTO> pageInfo = new Page<>(deviceDocumentMains, page, size);
        return pageInfo;
    }

    @Override
    @Transactional
    public DeviceDocumentMainDTO update(DeviceDocumentMainDTO deviceDocumentMainDTO) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        Long code = deviceDocumentMainDTO.getDeviceDocumentMain().getCode();
        example.createCriteria().andCodeEqualTo(code);
        deviceDocumentMainMapper.updateByExampleSelective(deviceDocumentMainDTO.getDeviceDocumentMain(), example);
        DeviceMainAttributelistExample dExample = new DeviceMainAttributelistExample();
        dExample.createCriteria().andMainCodeEqualTo(code);
        deviceMainAttributelistMapper.deleteByExample(dExample);
        if (deviceDocumentMainDTO.getArrName() != null) {
            for (int i = 0; i < deviceDocumentMainDTO.getArrName().size(); i++) {
                DeviceMainAttributelist deviceMainAttributelist = new DeviceMainAttributelist();
                deviceMainAttributelist.setName(deviceDocumentMainDTO.getArrName().get(i));
                deviceMainAttributelist.setAttValue(deviceDocumentMainDTO.getArrValue().get(i));
                deviceMainAttributelist.setMainCode(code);
                deviceMainAttributelistMapper.insertSelective(deviceMainAttributelist);
            }
        }
        /*for(DeviceMainManual d:deviceDocumentMainDTO.getManuals()){
            deviceManualService.update(d.getCode(),deviceDocumentMainDTO.getDeviceDocumentMain().getCode());
        }*/
        if (deviceDocumentMainDTO.getAdds() != null) {
            for (Long d : deviceDocumentMainDTO.getAdds()) {
                deviceManualService.update(d, deviceDocumentMainDTO.getDeviceDocumentMain().getCode());
            }
        }
        if (deviceDocumentMainDTO.getDels() != null) {
            cancelLoad(deviceDocumentMainDTO.getDels());
        }
        return deviceDocumentMainDTO;
    }

    @Override
    public DeviceDocumentMainDTO getDetailById(Long id) {
        DeviceDocumentMainDTO ans = new DeviceDocumentMainDTO();
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andCodeEqualTo(id);
        ans.setDeviceDocumentMain(deviceDocumentMainMapper.selectByExample(example).get(0));
        List<String> attName = new ArrayList<>();
        List<String> attValue = new ArrayList<>();
        DeviceMainAttributelistExample dExample = new DeviceMainAttributelistExample();
        dExample.createCriteria().andMainCodeEqualTo(id);
        List<DeviceMainAttributelist> list = deviceMainAttributelistMapper.selectByExample(dExample);
        for (DeviceMainAttributelist d : list) {
            attName.add(d.getName());
            attValue.add(d.getAttValue());
        }
        ans.setArrName(attName);
        ans.setArrValue(attValue);
        Integer statusCode = ans.getDeviceDocumentMain().getStatusCode();
        Integer deptCode = ans.getDeviceDocumentMain().getDeptCode();
        BasicInfoDeptExample deptExample = new BasicInfoDeptExample();
        deptExample.createCriteria().andCodeEqualTo(deptCode);
        BasicInfoDeviceStatusExample statusExample = new BasicInfoDeviceStatusExample();
        statusExample.createCriteria().andCodeEqualTo(statusCode);
        ans.setDeptName(basicInfoDeptMapper.selectByExample(deptExample).get(0).getName());
        ans.setDeviceStatus(basicInfoDeviceStatusMapper.selectByExample(statusExample).get(0).getName());
        ans.setManuals(deviceManualService.getByMainCode(id));
        return ans;
    }

    @Override
    @Transactional
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public DeviceMainManualDTO upLoad(MultipartFile file) throws IOException {
        DeviceMainManualDTO deviceMainManualDTO = new DeviceMainManualDTO();
        String fileCode = UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getCode()), file, 10,".pdf");
        String name = file.getOriginalFilename();
        DeviceMainManual deviceMainManual = new DeviceMainManual();
        deviceMainManual.setName(name);
        deviceMainManual.setStorepaths(fileCode);
        deviceManualService.addOne(deviceMainManual);
        deviceMainManualDTO.setId(deviceMainManual.getCode());
        deviceMainManualDTO.setPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getPath()+deviceMainManual.getStorepaths());
        return deviceMainManualDTO;
    }

    @Override
    @Transactional
    public void cancelLoad(Long[] id) {
        for (Long i : id) {
            //删除文件
            DeviceMainManual deviceMainManual = deviceManualService.getByCode(i);
            UploadUtil.deleteFile(AddressEnum.getCurrentPath(AddressEnum.EQUIPMENT_ARCHIVE_ABS_ADDRESS.getCode()), deviceMainManual.getStorepaths());
            //删除记录
            deviceManualService.deleteById(i);
        }
    }

    @Override
    @Transactional
    public void duplicateDevice(Long id) {
        //主设备复制
        DeviceDocumentMainExample example1 = new DeviceDocumentMainExample();
        example1.createCriteria().andCodeEqualTo(id);
        DeviceDocumentMain deviceDocumentMain = deviceDocumentMainMapper.selectByExample(example1).get(0);
        deviceDocumentMain.setCode(null);
        deviceDocumentMainMapper.insertSelective(deviceDocumentMain);
        Long newId = deviceDocumentMain.getCode();

        //主设备配件复制
        DeviceMainAccessoryExample example2 = new DeviceMainAccessoryExample();
        example2.createCriteria().andMainCodeEqualTo(id);
        List<DeviceMainAccessory> deviceMainAccessories = deviceMainAccessoryMapper.selectByExample(example2);
        for(int i=0;i<deviceMainAccessories.size();i++){
            deviceMainAccessories.get(i).setCode(null);
            deviceMainAccessories.get(i).setMainCode(newId);
            deviceMainAccessoryMapper.insertSelective(deviceMainAccessories.get(i));
        }

        //主设备部件复制
        DeviceDocumentUnitExample example3 = new DeviceDocumentUnitExample();
        example3.createCriteria().andMainCodeEqualTo(id);
        List<DeviceDocumentUnit> deviceDocumentUnits = deviceDocumentUnitMapper.selectByExample(example3);
        for(int i=0;i<deviceDocumentUnits.size();i++){
            DeviceUnitAccessoryExample example4 = new DeviceUnitAccessoryExample();
            example4.createCriteria().andUnitCodeEqualTo(deviceDocumentUnits.get(i).getCode());
            List<DeviceUnitAccessory> deviceUnitAccessories = deviceUnitAccessoryMapper.selectByExample(example4);
            deviceDocumentUnits.get(i).setCode(null);
            deviceDocumentUnits.get(i).setMainCode(newId);
            deviceDocumentUnitMapper.insertSelective(deviceDocumentUnits.get(i));
            Long newCode = deviceDocumentUnits.get(i).getCode();
            for(int j=0;j<deviceUnitAccessories.size();j++){
                deviceUnitAccessories.get(j).setCode(null);
                deviceUnitAccessories.get(j).setUnitCode(newCode);
                deviceUnitAccessoryMapper.insertSelective(deviceUnitAccessories.get(j));
            }
        }
    }

    @Override
    public Page getByPageByUnitName(String unitName, Integer page, Integer size) {
        String sql = "select a.* from device_document_main as a,device_document_unit as b where a.code=b.main_code and b.device_name like '" + unitName + "%'";
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByConditions(sql);
        List<DeviceDocumentMainDTO> ans = new ArrayList<>();
        for (int i = 0; i < deviceDocumentMains.size(); i++) {
            DeviceDocumentMainDTO deviceDocumentMainDTO = new DeviceDocumentMainDTO();
            BasicInfoDeviceStatusExample example1 = new BasicInfoDeviceStatusExample();
            example1.createCriteria().andCodeEqualTo(deviceDocumentMains.get(i).getStatusCode());
            BasicInfoDeviceStatus basicInfoDeviceStatus = basicInfoDeviceStatusMapper.selectByExample(example1).get(0);
            deviceDocumentMainDTO.setDeviceDocumentMain(deviceDocumentMains.get(i));
            deviceDocumentMainDTO.setBasicInfoDeviceStatus(basicInfoDeviceStatus);
            ans.add(deviceDocumentMainDTO);
        }
        Page pageInfo = new Page(ans,page,size);
        return pageInfo;
    }

    @Override
    public Page getByPageByAccName(String accName, Integer page, Integer size) {
        String sql = "select a.* from device_document_main as a,device_main_accessory as b where a.code=b.main_code and b.name like '" + accName + "%'";
        List<DeviceDocumentMain> deviceDocumentMains = deviceDocumentMainMapper.selectByConditions(sql);
        List<DeviceDocumentMainDTO> ans = new ArrayList<>();
        for (int i = 0; i < deviceDocumentMains.size(); i++) {
            DeviceDocumentMainDTO deviceDocumentMainDTO = new DeviceDocumentMainDTO();
            BasicInfoDeviceStatusExample example1 = new BasicInfoDeviceStatusExample();
            example1.createCriteria().andCodeEqualTo(deviceDocumentMains.get(i).getStatusCode());
            BasicInfoDeviceStatus basicInfoDeviceStatus = basicInfoDeviceStatusMapper.selectByExample(example1).get(0);
            deviceDocumentMainDTO.setDeviceDocumentMain(deviceDocumentMains.get(i));
            deviceDocumentMainDTO.setBasicInfoDeviceStatus(basicInfoDeviceStatus);
            ans.add(deviceDocumentMainDTO);
        }
        Page pageInfo = new Page(ans,page,size);
        return pageInfo;
    }

    @Override
    @Transactional
    public int duplicateDeviceAcc(Long originDeviceId, Long newDeviceId) {
        DeviceMainAccessoryExample example = new DeviceMainAccessoryExample();
        example.createCriteria().andMainCodeEqualTo(originDeviceId);
        List<DeviceMainAccessory> deviceMainAccessories = deviceMainAccessoryMapper.selectByExample(example);
        for(int i=0;i<deviceMainAccessories.size();i++){
            deviceMainAccessories.get(i).setMainCode(newDeviceId);
            deviceMainAccessories.get(i).setCode(null);
            deviceMainAccessoryMapper.insertSelective(deviceMainAccessories.get(i));
        }
        return 0;
    }

    @Override
    @Transactional
    public int duplicateDeviceUnit(Long originDeviceId, Long newUnitId, Integer flag) {
        DeviceDocumentUnitExample example = new DeviceDocumentUnitExample();
        example.createCriteria().andCodeEqualTo(newUnitId);
        DeviceDocumentUnit deviceDocumentUnit = deviceDocumentUnitMapper.selectByExample(example).get(0);
        deviceDocumentUnit.setCode(null);
        deviceDocumentUnit.setMainCode(originDeviceId);
        deviceDocumentUnitMapper.insertSelective(deviceDocumentUnit);
        Long nnid = deviceDocumentUnit.getCode();
        if(flag == 0){
            DeviceUnitAccessoryExample example1 = new DeviceUnitAccessoryExample();
            example1.createCriteria().andUnitCodeEqualTo(newUnitId);
            List<DeviceUnitAccessory> deviceUnitAccessories = deviceUnitAccessoryMapper.selectByExample(example1);
            for(int i=0;i<deviceUnitAccessories.size();i++){
                deviceUnitAccessories.get(i).setCode(null);
                deviceUnitAccessories.get(i).setUnitCode(nnid);
                deviceUnitAccessoryMapper.insertSelective(deviceUnitAccessories.get(i));
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public int duplicateUnitAcc(Long originUnitId, Long newUnitId) {
        DeviceUnitAccessoryExample example = new DeviceUnitAccessoryExample();
        example.createCriteria().andUnitCodeEqualTo(originUnitId);
        List<DeviceUnitAccessory> deviceUnitAccessories = deviceUnitAccessoryMapper.selectByExample(example);
        for(int i=0;i<deviceUnitAccessories.size();i++){
            deviceUnitAccessories.get(i).setUnitCode(newUnitId);
            deviceUnitAccessories.get(i).setCode(null);
            deviceUnitAccessoryMapper.insertSelective(deviceUnitAccessories.get(i));
        }
        return 0;
    }

    @Override
    public List<DeviceDocumentUnit> getAllUnitByDeptCodeByDeviceName(String deviceName) {
        String sql = "select u.* from device_document_main as m,device_document_unit as u where u.main_code = m.code and (m.device_name = '" + deviceName + "' or m.device_name like '" + deviceName + "-%')" ;
        List<DeviceDocumentUnit> deviceDocumentUnits = deviceDocumentUnitMapper.selectByCondition(sql);
        return deviceDocumentUnits;
    }

    @Override
    public List<DeviceDocumentMain> getAllMainByDeptCodeByDeviceName(String deviceName) {
        String sql = "select * from device_document_main where device_name = '" +deviceName + "' or device_name like '"+deviceName+"-%'";
        return deviceDocumentMainMapper.selectByConditions(sql);
    }

    @Override
    public int duplicateMutipleDevice(Long deviceId, Integer cnt) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andCodeEqualTo(deviceId);
        DeviceDocumentMain deviceDocumentMain = deviceDocumentMainMapper.selectByExample(example).get(0);
        deviceDocumentMain.setCode(null);
        for(int i=0;i<cnt;i++){
            deviceDocumentMainMapper.insertSelective(deviceDocumentMain);
            deviceDocumentMain.setCode(null);
        }
        return cnt;
    }

    @Override
    public List<DeviceDocumentMain> getAllByDeptId(Integer deptId) {
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        return deviceDocumentMainMapper.selectByExample(example);
    }
}
