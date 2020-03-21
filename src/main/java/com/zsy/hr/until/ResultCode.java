package com.zsy.hr.until;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname ResultCode
 * @Description TODO
 * @Date 2020/3/21 17:22
 * @Created by Yinghao.He
 */
@AllArgsConstructor
@Getter
public enum  ResultCode {
    LOGIN_FAIL(500,"密码或者账号错误");


    private Integer code;
    private String message;


}
