package com.tickets.dto;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 结果集
 */
@Data
@Accessors(chain = true)
public class ResponseResult {
    private Integer code;
    private String msg;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }



    public static ResponseResult SUCCESS() {
        return new ResponseResult(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg());
    }

    public static ResponseResult SUCCESS(Object data) {
        return new ResponseResult(HttpCode.SUCCESS.getCode(), ResponseResult.SUCCESS().getMsg(), data);
    }

    public static ResponseResult SUCCESS(String msg, Object data) {
        return new ResponseResult(HttpCode.SUCCESS.getCode(), msg, data);
    }
}
