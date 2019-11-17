package com.kevin.aopdemo.enumeration;

import org.apache.commons.lang3.StringUtils;

/**
 * @author dell
 * @version： OperateTypeEnum.java v 1.0, 2019年11月17日 18:11
 * @Description 操作类型枚举类
 **/
public enum OperateTypeEnum {

    PUSH_TO_PROJECT(1, "推送候选人到项目时指定操作人"),
    MATERIAL_REVIEW(2, "材料审核时指定操作人"),
    EXPORT_EXCEL(3, "导出 excel表格"),
    UPLOAD_FILE(4, "上传文件"),
    USER_REGISTER(5, "用户注册");

    private Integer type;

    private String name;

    OperateTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据 name 获取 type
     *
     * @param code
     * @return
     */
    public static Integer getTypeByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (OperateTypeEnum object : OperateTypeEnum.values()) {
            if (StringUtils.equals(name, object.getName())) {
                return object.getType();
            }
        }
        return null;
    }

    /**
     * 根据 type 获取 name
     *
     * @param type
     * @return
     */
    public static String getNameByType(Integer type) {
        if (null == type) {
            return null;
        }
        for (OperateTypeEnum object : OperateTypeEnum.values()) {
            if (type.equals(object.getType())) {
                return object.getName();
            }
        }
        return null;
    }
}
