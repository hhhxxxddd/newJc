package com.jinchi.common.service;


import com.jinchi.common.domain.TestReportRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.quality.product.ProductReportHeadDTO;

/**
 * 说明:
 * <br>
 *
 * @author huxudong
 * @Description 成品检验
 * <br>
 * 日期: 2018/11/19
 * <br>
 * 版本: 1.0
 */
public interface ProductRecordService {
    /**
     * 查询所有-分页 条件
     */
    PageBean pagesByFactory(String factory,PageBean pageBean);

    /**
     * 查看详情
     */
    ProductReportHeadDTO byBatchNumberId(Integer batchNumberId);

    /**
     * 更新
     */
    ProductReportHeadDTO updateValue(ProductReportHeadDTO productReportHeadDTO);

    /**
     * 更新择优人
     * @param testReportRecord
     * @return
     */
    TestReportRecord updateRate(TestReportRecord testReportRecord);
}
