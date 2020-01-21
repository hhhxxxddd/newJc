package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInventoryDailyReports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料库存日报表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInventoryDailyReportsMapper extends BaseMapper<SwmsStockInventoryDailyReports> {
    IPage<SwmsStockInventoryDailyReports> selectPageVo(Page page, @Param(value = "typeId") Integer typeId,
                                                       @Param(value = "subTypeId") Integer subTypeId,
                                                       @Param(value = "startTime") String startTime,
                                                       @Param(value = "endTime") String endTime);

    void updateComments(@Param(value = "id") Long id,
            @Param(value = "comments") String comments);
}
