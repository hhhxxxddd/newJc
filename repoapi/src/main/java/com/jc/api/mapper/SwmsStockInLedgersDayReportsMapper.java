package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInLedgersDayReports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物料入库日台账表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInLedgersDayReportsMapper extends BaseMapper<SwmsStockInLedgersDayReports> {

    IPage<SwmsStockInLedgersDayReports> selectPageVo(Page page,
                                                     @Param(value = "typeId") Integer typeId,
                                                     @Param(value = "subTypeId") Integer subTypeId,
                                                     @Param(value = "supplierId") Integer supplierId,
                                                     @Param(value = "startTime") String startTime,
                                                     @Param(value = "endTime") String endTime);

    void updateById(@Param(value = "id") Long id, @Param(value = "status") Integer status);

    List<String> selectByBatchLike(@Param(value = "batch") String batch);
}
