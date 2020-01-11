package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginAuthDTO {

    Integer id;

    String username;

    List<String> auths;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getId() {
        return id;
    }

    public void setId(Integer userid) {
        this.id = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }
}
