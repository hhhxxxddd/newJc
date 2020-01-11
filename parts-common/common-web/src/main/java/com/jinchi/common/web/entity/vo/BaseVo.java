package com.jinchi.common.web.entity.vo;


import com.jinchi.common.web.entity.po.BasePo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @className BaseVo
 * @author XudongHu
 * @apiNote 所有的Vo也要保证id的显示
 * @since 2019/11/1日16:13
 * @modifer
 */
@Data
@NoArgsConstructor
public class BaseVo<T extends BasePo> implements Serializable {
    private String id;
}
