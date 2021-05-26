package com.at.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 转化json
 * @author user
 * @create 2021-05-22 19:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code,message,null);
    }

}
