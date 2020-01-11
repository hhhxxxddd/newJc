package com.jinchi.common.web.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @className BasePo
 * @author XudongHu
 * @apiNote 所有的po都具有自增长的id,
 *          String类型保证id大小
 * @since 2019/11/1日16:14
 * @modifer
 */
@Data
public class BasePo implements Serializable {
    public final static String DEFAULT_USERNAME = "system";
    @TableId(type = IdType.AUTO)
    private String id;

//

//    private String createdBy;
//
//    @TableField(fill = FieldFill.INSERT)
//    private Date createdTime;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updatedBy;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updatedTime;
}
