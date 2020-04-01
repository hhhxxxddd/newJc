package com.jinchi.common.service;

import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.RawTestReportDTO;
import com.jinchi.common.dto.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by WangZhihao on 2018/12/11.
 */
@Service
public interface MiddleProductionDetectionService {

    List<RawTestReportDTO> findAll();

    RawTestReportDTO findDetailsById(Integer id);

    RawTestReportDTO update(RawTestReportDTO rawTestReportDTO);

    PageBean<RawTestReportDTO> findByFactoryNameByPage(String factoryName, PageBean pageBean);

    Result<Object> publish(Integer id);

    Object findDetailsByBatchNumberId(Integer id);

    Object findDetailsByBatchNumberIdForClient(Integer id);

    /**
     * 查看送样人的中间品送样结果
     * 返回24小时之内的数据
     * 刷卡
     * @return
     */
    List<Map<Object,Object>> delivererTask(String idCard);
}
