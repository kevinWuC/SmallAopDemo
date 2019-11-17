package com.kevin.aopdemo.operate;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dell
 * @version： OperatorInfo.java v 1.0, 2019年11月17日 18:52
 * @Description
 **/
@Data
public class OperatorInfo implements Serializable {

    private static final long serialVersionUID = -7811421021694236725L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
}
