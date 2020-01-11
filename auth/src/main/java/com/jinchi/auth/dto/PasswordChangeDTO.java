package com.jinchi.auth.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @author：XudongHu
 * @className:PasswordChangeDTO
 * @description: 修改密码DTO
 * @date:13:58 2019/5/27
 * @Modifer:
 */
public class PasswordChangeDTO {
    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("原始密码")
    @NotBlank(message = "原始密码不能为空")
    private String oldPassword;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    public Integer getUserId() {
        return userId;
    }

    public PasswordChangeDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public PasswordChangeDTO setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordChangeDTO setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    @Override
    public String toString() {
        return "PasswordChangeDTO{" +
                "userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
