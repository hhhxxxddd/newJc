package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import com.jc.api.entity.SwmsStockInventoryDailyReportsTotals;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SwmsStockInventoryDailyReportsTotalsMapper extends BaseMapper<SwmsStockInventoryDailyReportsTotals> {
    IPage<SwmsStockInventoryDailyReportsTotals> selectPageVo(Page page, @Param(value = "typeId") Integer typeId,
                                                       @Param(value = "subTypeId") Integer subTypeId,
                                                       @Param(value = "startTime") String startTime,
                                                       @Param(value = "endTime") String endTime);

    void updateComments(@Param(value = "id") Long id,
                        @Param(value = "comments") String comments);

    List<SwmsStockInventoryDailyReports> detail(@Param("matId") Integer matId,
                                                @Param("startTime") String startTime,
                                                @Param("endTime") String endTime);
}
