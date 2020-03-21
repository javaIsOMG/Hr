package com.zsy.hr.controller;

import com.zsy.hr.domian.dto.Result;
import com.zsy.hr.domian.vo.HrVo;
import com.zsy.hr.until.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2020/3/21 16:05
 * @Created by Yinghao.He
 */
@Slf4j
@RestController
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Result login(@Valid HrVo hrVo){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken();
        usernamePasswordToken.setUsername(hrVo.getUserName());
        //将账号密码放入主体中
        usernamePasswordToken.setPassword(hrVo.getPassword().toCharArray());
        try{
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            log.info("异常");
           return Result.fail(ResultCode.LOGIN_FAIL.getMessage());
        }
        return Result.ok();
    }
}
