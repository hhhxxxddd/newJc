package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockOutLedgersDayReports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料出库日台账表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockOutLedgersDayReportsMapper extends BaseMapper<SwmsStockOutLedgersDayReports> {

    IPage<SwmsStockOutLedgersDayReports> selectPageVo(Page page,
                                                      @Param(value = "typeId") Integer typeId,
                                                      @Param(value = "subTypeId") Integer subTypeId,
                                                      @Param(value = "deptId") Integer supplierId,
                                                      @Param(value = "startTime") String startTime,
                                                      @Param(value = "endTime") String endTime);
}
