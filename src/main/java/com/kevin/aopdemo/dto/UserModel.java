package com.kevin.aopdemo.dto;

import lombok.Data;

/**
 * @author dell
 * @version： UserModel.java v 1.0, 2019年11月17日 18:49
 * @Description 用户 model
 **/
@Data
public class UserModel {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
}
