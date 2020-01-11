package com.jinchi.common.web.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jinchi.common.web.entity.param.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @className BaseQueryForm
 * @author XudongHu
 * @apiNote 查询/分页 提交
 * @since 2019/10/29日21:11
 * @modifer
 */
@ApiModel
@Slf4j
@Data
public class BaseQueryForm<P extends BaseParam> extends BaseForm {
    /**
     * 分页查询的参数,当前页数
     */
    @ApiModelProperty(value = "当前页数",example = "1")
    private long current = 1;
    /**
     * 分页查询的参数,当前页面每页显示的数量
     */
    @ApiModelProperty(value = "每页大小",example = "10")
    private long size = 10;
    /**
     * 分页查询的参数,顺序or逆序(物理分页)
     */
    @ApiModelProperty(value = "排序方式,ASC/DESC",example = "ASC")
    private String orderType = "ASC";

    @ApiModelProperty(value = "排序属性,注意!是数据库的属性名",example = "id")
    private String orderBy = "id";

    /**
     * Form转化为Param
     *
     * @param clazz
     * @return
     */
    public P toParam(Class<P> clazz) {
        P p = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, p);
        return p;
    }

    /**
     * 从form中获取page参数，用于分页查询参数
     *
     * @return 分页信息
     */
    @JsonIgnore
    public Page getPage() {
        Page page = new Page(this.getCurrent(), this.getSize());
        return this.getOrderType().equals("DESC")?page.setDesc(this.getOrderBy()):page.setAsc(this.getOrderBy());
    }
}
