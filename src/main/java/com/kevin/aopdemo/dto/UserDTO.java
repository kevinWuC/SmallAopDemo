package com.kevin.aopdemo.dto;

import lombok.Data;

/**
 * @author dell
 * @version： UserDTO.java v 1.0, 2019年11月17日 21:50
 * @Description
 **/
@Data
public class UserDTO {

    /**
     * 用户id
     */

    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
}
