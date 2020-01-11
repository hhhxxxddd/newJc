package com.jinchi.common.service;


import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ResultUtil;
import com.jinchi.common.utils.UploadUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.BasicInfoDeptExample;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class DeviceSpotcheckModelsServiceImp implements DeviceSpotcheckModelsService {
    @Autowired
    DeviceSpotcheckModelsHeadMapper deviceSpotcheckModelsHeadMapper;

    @Autowired
    DeviceSpotcheckModelsDetailsMapper deviceSpotcheckModelsDetailsMapper;

    @Autowired
    BasicInfoDeptMapper basicInfoDeptMapper;

    @Autowired
    DeviceDocumentMainMapper deviceDocumentMainMapper;

    @Autowired
    AuthUserService authUserService;

    @Autowired
    DeviceSpotcheckPlansMapper deviceSpotcheckPlansMapper;

    @Autowired
    DeviceSpotcheckPlansService deviceSpotcheckPlansService;

    @Autowired
    DeviceSpotcheckRecordDetailsMapper deviceSpotcheckRecordDetailsMapper;

    @Autowired
    DeviceSpotcheckRecordHeadMapper deviceSpotcheckRecordHeadMapper;



    @Override
    public void deleteById(Long id) {
        DeviceSpotcheckPlansExample example = new DeviceSpotcheckPlansExample();
        example.createCriteria().andModelCodeEqualTo(id);
        List<DeviceSpotcheckPlans> deviceSpotcheckPlans = deviceSpotcheckPlansMapper.selectByExample(example);
        

        for (int i=0;i<deviceSpotcheckPlans.size();i++){
            DeviceSpotcheckRecordHeadExample deviceSpotcheckRecordHeadExample= new DeviceSpotcheckRecordHeadExample();
            deviceSpotcheckRecordHeadExample.createCriteria().andPlanCodeEqualTo(deviceSpotcheckPlans.get(i).getCode());
            List<DeviceSpotcheckRecordHead> deviceSpotcheckRecordHeads=deviceSpotcheckRecordHeadMapper.selectByExample(deviceSpotcheckRecordHeadExample);
            for(int j=0;j<deviceSpotcheckRecordHeads.size();j++){
                DeviceSpotcheckRecordDetailsExample deviceSpotcheckRecordDetailsExample= new DeviceSpotcheckRecordDetailsExample();
                deviceSpotcheckRecordDetailsExample.createCriteria().andRecordCodeEqualTo(deviceSpotcheckRecordHeads.get(j).getCode());
                deviceSpotcheckRecordDetailsMapper.deleteByExample(deviceSpotcheckRecordDetailsExample);
            }
            deviceSpotcheckRecordHeadMapper.deleteByExample(deviceSpotcheckRecordHeadExample);
        }
        DeviceSpotcheckModelsDetailsExample example3 = new DeviceSpotcheckModelsDetailsExample();
        example3.createCriteria().andModelCodeEqualTo(id);
        deviceSpotcheckModelsDetailsMapper.deleteByExample(example3);
        deviceSpotcheckPlansMapper.deleteByExample(example);

        DeviceSpotcheckModelsHeadExample example4 = new DeviceSpotcheckModelsHeadExample();
        example4.createCriteria().andCodeEqualTo(id);
        deviceSpotcheckModelsHeadMapper.deleteByExample(example4);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }

    @Override
    @Transactional
    public DeviceSpotcheckModelsHeadDTO add(DeviceSpotcheckModelsHeadDTO deviceSpotcheckModelsHeadDTO) {
        /*if (deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getModelStatus() == false) {
            DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
            example.createCriteria().andModelStatusEqualTo(false).andDeviceNameLike(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getDeviceName().split("-")[0]);
            DeviceSpotcheckModelsHead deviceSpotcheckModelsHead = new DeviceSpotcheckModelsHead();
            deviceSpotcheckModelsHead.setModelStatus(true);
            deviceSpotcheckModelsHeadMapper.updateByExampleSelective(deviceSpotcheckModelsHead, example);

        }*/
        deviceSpotcheckModelsHeadDTO.setPeopleName(authUserService.findById(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getSetPeople()).getName());
        deviceSpotcheckModelsHeadMapper.insertSelective(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead());

        for (int i = 0; i < deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().size(); i++) {
            DeviceSpotcheckModelsDetails deviceSpotcheckModelsDetails = new DeviceSpotcheckModelsDetails();
            deviceSpotcheckModelsDetails.setModelCode(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getCode());
            deviceSpotcheckModelsDetails.setSpotcheckItems(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckItems());
            deviceSpotcheckModelsDetails.setSpotcheckContent(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckContent());
            deviceSpotcheckModelsDetails.setSpotcheckPeriod(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckPeriod());
            deviceSpotcheckModelsDetails.setSpotcheckAddress(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckAddress());
            deviceSpotcheckModelsDetailsMapper.insertSelective(deviceSpotcheckModelsDetails);
        }
        return deviceSpotcheckModelsHeadDTO;
    }

    @Override
    @Transactional
    public DeviceSpotcheckModelsHeadDTO updataById(DeviceSpotcheckModelsHeadDTO deviceSpotcheckModelsHeadDTO) {
        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        example.createCriteria().andCodeEqualTo(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getCode());

        if (deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getModelStatus() == false) {
            DeviceSpotcheckModelsHeadExample example2 = new DeviceSpotcheckModelsHeadExample();
            example2.createCriteria().andModelStatusEqualTo(false).andDeviceNameLike(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getDeviceName());
            DeviceSpotcheckModelsHead deviceSpotcheckModelsHead = new DeviceSpotcheckModelsHead();
            deviceSpotcheckModelsHead.setModelStatus(true);
            deviceSpotcheckModelsHeadMapper.updateByExampleSelective(deviceSpotcheckModelsHead, example);
        }

        deviceSpotcheckModelsHeadMapper.updateByExampleSelective(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead(), example);

        DeviceSpotcheckModelsDetailsExample example1 = new DeviceSpotcheckModelsDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsHead().getCode());
        deviceSpotcheckModelsDetailsMapper.deleteByExample(example1);

        for (int i = 0; i < deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().size(); i++) {
            DeviceSpotcheckModelsDetails deviceSpotcheckModelsDetails = new DeviceSpotcheckModelsDetails();
            deviceSpotcheckModelsDetails.setModelCode(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getModelCode());
            deviceSpotcheckModelsDetails.setSpotcheckItems(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckItems());
            deviceSpotcheckModelsDetails.setSpotcheckContent(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckContent());
            deviceSpotcheckModelsDetails.setSpotcheckPeriod(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckPeriod());
            deviceSpotcheckModelsDetails.setSpotcheckAddress(deviceSpotcheckModelsHeadDTO.getDeviceSpotcheckModelsDetails().get(i).getSpotcheckAddress());
            deviceSpotcheckModelsDetailsMapper.insertSelective(deviceSpotcheckModelsDetails);
        }
        return deviceSpotcheckModelsHeadDTO;
    }

    @Override
    public DeviceSpotcheckModelsHeadDTO detail(Long id) {
        DeviceSpotcheckModelsHeadDTO deviceSpotcheckModelsHeadDTO = new DeviceSpotcheckModelsHeadDTO();

        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        example.createCriteria().andCodeEqualTo(id);
        Integer peopleId = deviceSpotcheckModelsHeadMapper.selectByExample(example).get(0).getSetPeople();
        deviceSpotcheckModelsHeadDTO.setDeviceSpotcheckModelsHead(deviceSpotcheckModelsHeadMapper.selectByExample(example).get(0));

        DeviceSpotcheckModelsDetailsExample example1 = new DeviceSpotcheckModelsDetailsExample();
        example1.createCriteria().andModelCodeEqualTo(id);
        deviceSpotcheckModelsHeadDTO.setDeviceSpotcheckModelsDetails(deviceSpotcheckModelsDetailsMapper.selectByExample(example1));

        deviceSpotcheckModelsHeadDTO.setPeopleName(authUserService.findById(peopleId).getName());


        return deviceSpotcheckModelsHeadDTO;
    }

    @Override
    public List<BasicInfoDept> getChildDeptByCode(int id) {
        ArrayList<BasicInfoDept> res = new ArrayList<BasicInfoDept>();
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeEqualTo(id);
        List<BasicInfoDept> Array = basicInfoDeptMapper.selectByExample(example);
        for (int i = 0; i < Array.size(); i++) {
            res.add(Array.get(i));
        }
        return res;
    }

    @Override
    public List<String> getDeviceByCode(int id) {
        List<String> res = new ArrayList<>();
        DeviceDocumentMainExample example = new DeviceDocumentMainExample();
        example.createCriteria().andDeptCodeEqualTo(id);
        List<DeviceDocumentMain> Array = deviceDocumentMainMapper.selectByExample(example);
        for (int i = 0; i < Array.size(); i++) {
            String[] temp = Array.get(i).getDeviceName().split("-");
            if (!res.contains(temp[0])) {
                res.add(temp[0]);
            }
        }
        return res;
    }

    @Override
    public DeviceSpotcheckModelsDTO getDevicesNameByMainCode(int id) {
        DeviceSpotcheckModelsDTO deviceSpotcheckModelsDTO = new DeviceSpotcheckModelsDTO();

        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andCodeEqualTo(id);
        List<BasicInfoDept> temp = basicInfoDeptMapper.selectByExample(example);

        ArrayList res1 = new ArrayList<>();

        ArrayList<BasicInfoDept> res2 = new ArrayList<BasicInfoDept>();
        //找到一级部门的设备
        res1.addAll(getDeviceByCode(id));

        //找一级部门下面的二级部门
        res2.addAll(getChildDeptByCode(id));
        //找到Head中满足二级id的设备
        for (int i = 0; i < res2.size(); i++) {

            DeviceDocumentMainExample example2 = new DeviceDocumentMainExample();
            example2.createCriteria().andDeptCodeEqualTo(res2.get(i).getCode());
            List<DeviceDocumentMain> array = deviceDocumentMainMapper.selectByExample(example2);
            for (int j = 0; j < array.size(); j++) {
                if (!res1.contains(array.get(j).getDeviceName().split("-")[0])) {
                    res1.add(array.get(j).getDeviceName().split("-")[0]);
                }
            }
        }
        deviceSpotcheckModelsDTO.setBasicInfoDept(temp.get(0));
        deviceSpotcheckModelsDTO.setDeviceName(res1);
        return deviceSpotcheckModelsDTO;
    }

    @Override
    public List<DeviceSpotcheckModelsDTO> getAllDevices() {
        ArrayList<DeviceSpotcheckModelsDTO> res = new ArrayList<DeviceSpotcheckModelsDTO>();
        BasicInfoDeptExample example = new BasicInfoDeptExample();
        example.createCriteria().andParentCodeIsNull();
        List<BasicInfoDept> array = basicInfoDeptMapper.selectByExample(example);
        for (int i = 0; i < array.size(); i++) {
            res.add(getDevicesNameByMainCode(array.get(i).getCode()));
        }
        return res;
    }


    @Override
    public List<DeviceSpotcheckModelsHeadDTO> getAllByDeviceName(String deviceName, Integer deptId, Integer page, Integer size) {
        List array = new ArrayList();
        BasicInfoDeptExample temp = new BasicInfoDeptExample();
        temp.createCriteria().andCodeEqualTo(deptId);
        //只用管一级部门
        Integer id = basicInfoDeptMapper.selectByExample(temp).get(0).getCode();
        array.add(id);
        if (basicInfoDeptMapper.selectByExample(temp).get(0).getParentCode() == null) {
            List<BasicInfoDept> array2 = getChildDeptByCode(deptId);
            for (int i = 0; i < array2.size(); i++) {
                array.add(array2.get(i).getCode());
            }
        }

        List<DeviceSpotcheckModelsHeadDTO> deviceSpotcheckModelsHeadDTOS = new ArrayList<>();
        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        example.createCriteria().andDeviceNameEqualTo(deviceName).andDeptCodeIn(array);


        List<DeviceSpotcheckModelsHead> deviceSpotcheckModelsHeads = deviceSpotcheckModelsHeadMapper.selectByExample(example);

        for (int i = 0; i < deviceSpotcheckModelsHeads.size(); i++) {

            DeviceSpotcheckModelsDetailsExample example2 = new DeviceSpotcheckModelsDetailsExample();
            example2.createCriteria().andModelCodeEqualTo(deviceSpotcheckModelsHeads.get(i).getCode());
            List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails = deviceSpotcheckModelsDetailsMapper.selectByExample(example2);

            deviceSpotcheckModelsHeadDTOS.add(new DeviceSpotcheckModelsHeadDTO());
            deviceSpotcheckModelsHeadDTOS.get(i).setDeviceSpotcheckModelsHead(deviceSpotcheckModelsHeads.get(i));
            deviceSpotcheckModelsHeadDTOS.get(i).setDeviceSpotcheckModelsDetails(deviceSpotcheckModelsDetails);
            deviceSpotcheckModelsHeadDTOS.get(i).setPeopleName(authUserService.findById(deviceSpotcheckModelsHeads.get(i).getSetPeople()).getName());

        }
        return deviceSpotcheckModelsHeadDTOS;
    }


    @Override
    public Page getByPage(String deviceName, Integer deptId, Integer page, Integer size) {
        Page<DeviceSpotcheckModelsHeadDTO> pageInfo = new Page<>(getAllByDeviceName(deviceName, deptId, page, size), page, size);
        return pageInfo;
    }

    @Override
    public List<DeviceSpotcheckModelsHeadDTO> getModelsByDeptId(Integer deptId) {
        List<DeviceSpotcheckModelsHeadDTO> res = new ArrayList<>();
        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        example.createCriteria().andDeptCodeEqualTo(deptId);
        List<DeviceSpotcheckModelsHead> Array = new ArrayList<>();
        Array = deviceSpotcheckModelsHeadMapper.selectByExample(example);
        for (int i = 0; i < Array.size(); i++) {
            res.add(detail(Array.get(i).getCode()));
        }
        return res;
    }

    @Override
    public List<DeviceSpotcheckModelsDTO>getByDeviceName(String deviceName){
        List<DeviceSpotcheckModelsDTO> res = new ArrayList<>();

        List<DeviceSpotcheckModelsDTO> example = getAllDevices();
        if(deviceName == null){return example;}
        for (int i=0;i<example.size();i++){
            List<String> array2 =new ArrayList<>();
            List<String> array = example.get(i).getDeviceName();
            for(int j=0;j<array.size();j++){
                if (array.get(j).contains(deviceName)){
                    array2.add(array.get(j));
                }
            }
            if(!array2.isEmpty()){
                DeviceSpotcheckModelsDTO deviceSpotcheckModelsDTO = new DeviceSpotcheckModelsDTO();
                deviceSpotcheckModelsDTO.setDeviceName(array2);
                deviceSpotcheckModelsDTO.setBasicInfoDept(example.get(i).getBasicInfoDept());
                res.add(deviceSpotcheckModelsDTO);
            }
        }
        return res;
    }

    @Override
    public void deleteDetailById(Long headCode,Long id){
        DeviceSpotcheckRecordDetailsExample deviceSpotcheckRecordDetailsExample = new DeviceSpotcheckRecordDetailsExample();
        deviceSpotcheckRecordDetailsExample.createCriteria().andModelDetailCodeEqualTo(id);
        deviceSpotcheckRecordDetailsMapper.deleteByExample(deviceSpotcheckRecordDetailsExample);


        List<DeviceSpotcheckModelsDetails> deviceSpotcheckModelsDetails = new ArrayList<>();
        DeviceSpotcheckModelsDetailsExample example = new DeviceSpotcheckModelsDetailsExample();
        example.createCriteria().andCodeEqualTo(id).andModelCodeEqualTo(headCode);
        deviceSpotcheckModelsDetailsMapper.deleteByExample(example);
    }

    @Override
    @Transactional
    public String upload(MultipartFile file) throws IOException {
        String fileCode = UploadUtil.uploadPic(AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_PHOTO.getCode()), file,10);
        return fileCode;
    }

    @Override
    @Transactional
    public void cancelLoad(String path) {
            UploadUtil.deleteFile(AddressEnum.getCurrentPath(AddressEnum.DEVICE_SPOTCHECK_PHOTO.getCode()), path);
    }

    @Override
    public List getModelByDeviceName(Integer deptCode,String deviceName) {
        DeviceSpotcheckModelsHeadExample example = new DeviceSpotcheckModelsHeadExample();
        example.createCriteria().andDeviceNameEqualTo(deviceName).andDeptCodeEqualTo(deptCode).andModelStatusEqualTo(false);
        return deviceSpotcheckModelsHeadMapper.selectByExample(example);
    }
}
