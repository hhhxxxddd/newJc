package com.jinchi.common.service;


import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;

import java.util.List;
import java.util.Map;

/**
 * @author：XudongHu
 * @className:RepoInRecord
 * @description:
 * @date:14:22 2018/11/29
 */
public interface RepoInRecordService {
    /**
     * 根据名称模糊和物料类型查询所有
     */
    List<Map<Object,Object>> byNameLikeAndType(String materialName, Integer materialType);

    /**
     * 新增入库记录
     */
    String stockpiling(RepoOutHeadDTO repoInRecordDTO);

    /**
     * 根据名称模糊和物料类型查询所有-分页
     */
    PageBean byNameLikeAndTypeByPage(String materialName, Integer materialType,
                                     Integer page, Integer size, String fieldName, String orderType);
}
