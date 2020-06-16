package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-30 21:08
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IdDto {
    private Long id;
    private Integer userId;

    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getUserId() {
        return userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getId() {
        return id;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public void setId(Long id) {
        this.id = id;
    }
}
