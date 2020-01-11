package com.jinchi.app.service;

import com.jinchi.app.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-02 13:41
 * @description:
 **/

public interface DeviceSpotcheckService {
    List<DeviceSpotcheckPlansGetDTO> getSpotCheckPlans(DeviceSpotcheckPlansPostDTO deviceSpotcheckPlansPostDTO);

    List<DeviceSpotcheckPlansGetDTO> getSpotCheckPlansV1(DeviceSpotcheckPlansPostDTO deviceSpotcheckPlansPostDTO);

    DeviceSpotcheckRecordDetailDTO generateRecord(SpotcheckRecordDTO spotcheckRecordDTO);

    List<DeviceSpotcheckRecordHistoryGetDTO> history(DeviceSpotcheckRecordHistoryPostDTO dto);

    List<DeviceSpotcheckRecordHistoryGetDTO> historyV1(DeviceSpotcheckRecordHistoryPostDTO dto);

    List<DeviceSpotcheckRecordHistoryGetDTO> today(DeviceSpotcheckRecordHistoryPostDTO dto);

    DeviceSpotcheckRecordDetailDTO detail(IdDto idDto);//点检记录详情查询

    int updateByFlag(DeviceSpotcheckUpdateDTO dto);//提交或暂存

    SpotcheckNFCResultDTO getDataByNfc(SpotcheckNFCDTO dto);

    List<DeviceSpotcheckRecordHistoryGetDTO> page(DeviceSpotcheckRecordHistoryPostDTO dto);

//    List<DeviceSpotcheckRecordHistoryGetDTO> pageToday(DeviceSpotcheckRecordHistoryPostDTO dto);//点检主管 今日记录

    //点检主管 点检主管查询  今日记录statusId=0 异常记录statusId=1 历史记录statusId=2
    List<DeviceSpotcheckRecordHistoryGetDTO> pageAbnormal(DeviceSpotcheckRecordHistoryPostDTO dto);

    int managerConfirm(IdDto idDto);

    List getProcessById(IdDto idDto);

    List getDeviceStatus();

    String upload(MultipartFile file) throws IOException;

    void cancelLoad(String path);
}
