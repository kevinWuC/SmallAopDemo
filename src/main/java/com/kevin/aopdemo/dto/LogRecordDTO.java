package com.kevin.aopdemo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author dell
 * @version： LogRecordDTO.java v 1.0, 2019年11月17日 17:36
 * @Description
 **/
@Data
public class LogRecordDTO {
    /**
     * 操作类型名称
     */
    private String operateTypeName;
    /**
     * 需要记录的内容
     */
    private String recordContent;
    /**
     * 操作人id
     */
    private Long operatorId;
    /**
     * 操作人name
     */
    private String operatorName;
    /**
     * 操作时间
     */
    private Date operatorTime;
}
