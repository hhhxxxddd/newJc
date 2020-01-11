package com.jinchi.auth.exceptions;

/**
 * 所有异常类型
 * <p>
 * huxudong
 */
public enum EnumExceptions {
    UNKOWN_ERROR(-1, "未知错误"),
    SQL_ERROR(-1, "开发人员很可能使用了错误的SQL语句"),
    TYPE_MISMATCH_ERROR(-1, "错误的数据类型插入数据库"),
    NULL_POINTER_ERROR(-1, "空指针异常,运行时操作了Null数据"),
    //以上都是错误,错误code为-1
    SUCCESS(0, "操作成功"),
    REQUEST_METHOD(1, "请求方法不匹配"),
    ARGU_MISMATCH_EXCEPTION(2, "参数类型不匹配错误, 请检查"),
    FAILED_CONNECTED_DATABASE(3, "数据库资源访问失败"),
    VIOLATION_CONSTRAINED_OPERATION(4, "禁止操作,违反数据库约束"),//外键,非空,唯一键等
    NOT_NULL_COLUMN_CANT_BE_NULL(4, "存在非空字段设置为空现象"),
    ADD_FAILED_FOREIGN_KEY_NOT_EXIST(4,"新增失败,外键不存在"),
    //以上是全局捕捉异常,请勿修改

    ADD_FAILED_NAME_DUPLICATED(5, "新增失败,名称重复"),
    LOGIN_FAILED_USER_NOT_EXISTS(6, "登陆失败,用户不存在"),
    DELETE_FAILED_NOT_EXISTS(7, "删除失败,不存在"),
    UPDATE_FAILED_NOT_EXISTS(8, "更新失败,不存在"),
    LOGIN_FAILED_ERROR(9, "登陆失败,账号或密码错误"),
    ASSIGN_FAILED_ROLE_NOT_EXISTS(10, "分配失败,角色不存在"),
    ASSIGN_FAILED_USER_NOT_EXISTS(11, "分配失败,用户不存在"),
    DELETE_FAILED_REF_KEY_NOT_EXISTS(12, "删除失败,传入的数据有外键不存在"),
    OPERATION_NAME_NOT_EXISTS(13, "新增失败,操作名称为空"),
    FIND_FAILED_NOT_EXISTS(14, "查找失败,不存在"),
    DELETE_FAILED_EXISTS_CHILD_MENU(15,"删除失败,存在子菜单"),

    ;

    /**
     * 编码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;

    /**
     * 构造函数
     *
     * @param code
     * @param message
     */
    EnumExceptions(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
