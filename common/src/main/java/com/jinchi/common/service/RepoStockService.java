package com.jinchi.common.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.common.domain.RepoDiffRecord;
import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoStockDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhihao on 2018/11/30.
 */
@Service
public interface RepoStockService {
    /**
     * 根据id查询库存
     */
    RepoStock getById(Integer id);


    /**
     * 根据id查询库存-> 杜敏添加
     */
    RepoStock getBySerialNumberId(Integer serialNumberId);


    /**
     * 根据货物名称查询所有-分页
     */

    PageBean findAllByNameByPage(Integer materialClass, String materialName, Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有
     */
    List<RepoStockDTO> findAllByMaterialClass(Integer materialClass);

    /**
     * 根据物料类型和物料名称查询所有库存记录
     */
    List<RepoStockDTO> findAllByClassAndName(Integer materialClass, String materialName);

    Object oneKeyStock(Integer id, Integer creator);

    List<RepoDiffRecord> getAllRepoDiffRecord();
}
