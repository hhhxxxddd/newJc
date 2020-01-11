package com.jinchi.common.web.entity.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinchi.common.web.entity.po.BasePo;
import lombok.Data;

/**
 * @className BaseParam
 * @author XudongHu
 * @apiNote 参数封装
 * @since 2019/10/29日21:10
 * @modifer
 */
@Data
public class BaseParam<T extends BasePo> {

    public QueryWrapper<T> build() {
        return new QueryWrapper<>();
    }
}
