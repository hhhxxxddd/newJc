package com.jinchi.common.service;

import com.jinchi.common.domain.AuxiliaryMaterialsStatisticHead;
import com.jinchi.common.dto.AuxiliaryAddDTO;
import com.jinchi.common.dto.AuxiliaryDetailDTO;
import com.jinchi.common.dto.Page;

import java.util.List;
import java.util.Map;

public interface AuxiliaryService {

    Object addComfirm(AuxiliaryMaterialsStatisticHead head);

    AuxiliaryMaterialsStatisticHead update(AuxiliaryMaterialsStatisticHead head);

    AuxiliaryAddDTO afterComfirm(Long id);

    Map nextPeroidNumber(Integer periodId);

    String saveOrCommit(AuxiliaryAddDTO auxiliaryAddDTO, Integer flag);

    Page getPageUnCommit(AuxiliaryMaterialsStatisticHead head, Integer page, Integer size);

    Page getPageCommit(AuxiliaryMaterialsStatisticHead head, Integer page, Integer size);

    Map stasticByProcess(AuxiliaryMaterialsStatisticHead head);

    List stasticByLine(AuxiliaryMaterialsStatisticHead head);

    List processCur(AuxiliaryMaterialsStatisticHead head,Integer processId);

    List lineCur(AuxiliaryMaterialsStatisticHead head,Integer lineId);

    AuxiliaryDetailDTO detail(Long id);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    List getAllDate();

    List getVolumeWeight(Integer processId);

    Page defaultPage();

}