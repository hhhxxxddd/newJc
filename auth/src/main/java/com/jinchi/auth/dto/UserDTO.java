package com.jinchi.auth.dto;

/**
 * Created by Administrator on 2018/11/13.
 */
public class UserDTO {
    private Integer id;
    private String username;
    private String password = "123456";
    private Integer departmentId;
    private String phone;
    private String departmentName;
    private String name;
    private String idCardNumber;

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public UserDTO setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public UserDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public UserDTO setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
        return this;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", departmentId=" + departmentId +
                ", phone='" + phone + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", name='" + name + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                '}';
    }
}
