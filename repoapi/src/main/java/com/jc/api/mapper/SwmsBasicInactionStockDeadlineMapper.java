package com.jc.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicInactionStockDeadline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 呆滞期限设置表 Mapper 接口
 * </p>
 *
 * @author LiuTaoYi
 * @since 2020-01-10
 */
@Repository
@Mapper
public interface SwmsBasicInactionStockDeadlineMapper extends BaseMapper<SwmsBasicInactionStockDeadline> {

    /**
     * @Description:    呆滞期限-不分页
     * @Author: River
     * @Date: 2020/1/12 18:19
     {@link List< SwmsBasicInactionStockDeadline>}
     java.util.List<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    List<SwmsBasicInactionStockDeadline> findList(@Param(value = "subTypeName")String subTypeName);

    /**
     * @Description:    呆滞期限-分页
     * @Author: River
     * @Date: 2020/1/12 18:28
     {@link IPage< SwmsBasicInactionStockDeadline>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    IPage<SwmsBasicInactionStockDeadline> selectPage(Page<?> page, @Param(value = "subTypeName") String subTypeName);
}
