package com.jinchi.common.service;


import com.jinchi.common.domain.BasicInfoDept;
import com.jinchi.common.domain.BasicInfoLocation;
import com.jinchi.common.dto.DeviceSpotcheckModelsDTO;
import com.jinchi.common.dto.DeviceSpotcheckModelsHeadDTO;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BasicInfoLocationService {

    BasicInfoLocation add(BasicInfoLocation basicInfoLocation);

    void deleteById(Integer id);

    void deleteByIds(Integer[] ids);

    BasicInfoLocation updataById(BasicInfoLocation basicInfoLocation) ;

    Page getByPage(Integer deptId,Integer page,Integer size);

    List<BasicInfoLocation> getAllByDeptName(Integer deptName,Integer page,Integer size);

    List<BasicInfoLocation> getLocationById(Integer id);

}
