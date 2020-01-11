package com.jinchi.common.service;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoDiffRecordDTO;
import com.jinchi.common.dto.repository.RepoStockDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhihao on 2018/12/17.
 */
@Service
public interface RepoDiffRecordService {

    List<RepoStockDTO> stockByMaterialClassAndName(Integer materialClass, String materialName);

    List<RepoDiffRecordDTO> byMaterialClass(Integer materialClass);

    List<RepoDiffRecordDTO> byMaterialClassByNameLike(Integer materialClass, String materialName);

    PageBean byPageByMaterialNameByNameLike(Integer materialClass, String materialName, PageBean pageBean);

}
