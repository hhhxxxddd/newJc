package com.jinchi.app.service;

import com.jinchi.app.domain.BasicInfoEvaluationsItem;
import com.jinchi.app.domain.BasicInfoQuickPhrases;
import com.jinchi.app.dto.*;

import java.util.List;

public interface DeviceRepairReportService {

    RepairPageDataDTO page(RepairPostDTO repairPostDTO);

    void delete(Long id);

    void rescind(Long id);

    List<BasicInfoEvaluationsItem> evaluation();

    DeviceRepairDetail detail(Long id, Integer userId);

    void commit(DeviceRepairApplyDTO deviceRepairApplyDTO);

    List<BasicDeviceDTO> getDeviceByDeptId(RepairPostDTO repairPostDTO);

    List<BasicInfoQuickPhrases> getQuickPhase();

    List<DeptCata> getDeptCata(Integer userId);

    DeviceRepairEvaluationDTO comfirmEvalution(DeviceRepairEvaluationDTO deviceRepairEvaluationDTO);

    DeviceDTO getDeviceByIdCard(RepairPostDTO repairPostDTO);
}
