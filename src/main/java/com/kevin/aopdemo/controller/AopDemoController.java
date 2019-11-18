package com.kevin.aopdemo.controller;

import com.kevin.aopdemo.anotation.LogRecord;
import com.kevin.aopdemo.dto.UserDTO;
import com.kevin.aopdemo.dto.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dell
 * @version： AopDemoController.java v 1.0, 2019年11月17日 21:48
 * @Description
 **/
@RestController
public class AopDemoController {

    @RequestMapping(value = "/user/hello")
    @LogRecord(operateType = 2, recordContent = {"userName"})
    public UserModel sayHello(@RequestBody UserDTO dto) {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }


}
