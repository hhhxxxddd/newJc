package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInOutReports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物料进出库查询报表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsStockInOutReportsMapper extends BaseMapper<SwmsStockInOutReports> {

    IPage<SwmsStockInOutReports> selectPageVo(Page page,
                                              @Param(value = "typeId") Integer typeId,
                                              @Param(value = "subTypeId") Integer subTypeId,
                                              @Param(value = "materialCode") Integer materialCode,
                                              @Param(value = "supplierCode") Integer supplierCode,
                                              @Param(value = "batch") String batch);
}
