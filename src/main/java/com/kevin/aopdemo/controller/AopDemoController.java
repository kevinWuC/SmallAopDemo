package com.kevin.aopdemo.controller;

import com.kevin.aopdemo.anotation.LogRecord;
import com.kevin.aopdemo.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dell
 * @version： AopDemoController.java v 1.0, 2019年11月17日 21:48
 * @Description
 **/
@RestController
public class AopDemoController {

    @RequestMapping(value = "/user/hello")
    @LogRecord(operateType = 2, recordContent = {"userId", "userName"})
    public Boolean sayHello(@RequestBody UserDTO dto) {
        return true;
    }


}
