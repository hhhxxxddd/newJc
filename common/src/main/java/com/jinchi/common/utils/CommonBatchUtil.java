package com.jinchi.common.utils;

import com.jinchi.common.domain.CommonBatchNumberChild;
import com.jinchi.common.mapper.CommonBatchNumberChildMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XudongHu
 * @className CommonBatchUtil
 * @apiNote 批号工具类
 * @modifer
 * @since 2019/10/23 23:45
 */
@Component
public class CommonBatchUtil {
    @Autowired
    private CommonBatchNumberChildMapper commonBatchNumberChildMapper;

    /**
     * 找出最新的批号id
     * @param id
     * @return
     */
    public Integer lastId(Integer id){
        Integer lastId = id;
        while (commonBatchNumberChildMapper.selectByBatchNumberId(lastId)!=null){
            lastId = commonBatchNumberChildMapper.selectByBatchNumberId(lastId).getChildId();
        }
        return lastId;
    }

    /**
     * 找出所有的批号ids
     * @param id
     * @return
     */
    public List<Integer> historyIds(Integer id){
        List<Integer> historyIds = new ArrayList<>();
        historyIds.add(id);
        Integer lastId = id;
        while (commonBatchNumberChildMapper.selectByBatchNumberId(lastId)!=null){
            lastId = commonBatchNumberChildMapper.selectByBatchNumberId(lastId).getChildId();
            historyIds.add(lastId);
        }
        return historyIds;
    }
}
