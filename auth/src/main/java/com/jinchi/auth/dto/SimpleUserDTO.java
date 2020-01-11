package com.jinchi.auth.dto;

import com.jinchi.auth.domain.User;

/**
 * @author：XudongHu
 * @description:  只包含用户名称和id的简单DTO
 * @date:16:46 2018/11/8
 */
public class SimpleUserDTO {
    private Integer id;
    private String username;
    private String name;

    public SimpleUserDTO(Integer id, String username,String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
    public SimpleUserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
