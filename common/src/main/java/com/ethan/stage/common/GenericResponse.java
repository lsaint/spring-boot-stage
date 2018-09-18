package com.ethan.stage.common;

import com.ethan.stage.common.enums.ErrEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.HashMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Json 统一返回格式
 * { code: 0,
 *   msg: "sucess",
 *   data: {}
 * }
 *
 * 当客户端的请求字段出错时
 * data格式：
 * data {
 *     errors: {
 *          field1: msg1,
 *          field2: msg2,
 *          ...
 *     }
 * }
 */

@Slf4j
@Data
@NoArgsConstructor
@SuppressWarnings("rawtypes")
public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = 1;

    // 响应代码
    private int code;

    // 响应描述
    private String msg = "";

    // 响应体
    private T data;

    public GenericResponse(ErrEnum ee, T data) {
        super();
        this.code = ee.getErrCode();
        this.msg = ee.getErrMsg();
        this.data = data;
    }

    public GenericResponse(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.warn("toJson error", e);
            return "";
        }
    }

    // 成功，空数据
    public static GenericResponse onSucess() {
        return new GenericResponse<>(ErrEnum.SUCCESS, Const.EMPTY_MAP);
    }

    // 成功，带自定义数据
    public static <T> GenericResponse<T> onSucess(T data) {
        return new GenericResponse<>(ErrEnum.SUCCESS, data);
    }

    // 成功，带自定义提示信息
    public static GenericResponse<HashMap> onSucess(String msg) {
        return new GenericResponse<HashMap>(ErrEnum.SUCCESS.getErrCode(), msg, Const.EMPTY_MAP);
    }

    // 失败，服务端500异常,尽量不使用
    public static GenericResponse onError() {
        return new GenericResponse<>(ErrEnum.INTERNAL_SERVER_ERROR, Const.EMPTY_MAP);
    }

    // 失败，返回自定义错误
    public static GenericResponse onError(ErrEnum ee) {
        return new GenericResponse<>(ee, Const.EMPTY_MAP);
    }

    // 失败，返回自定义错误和数据
    public static <T> GenericResponse<T> onError(ErrEnum ee, T data) {
        return new GenericResponse<>(ee, data);
    }

    // 失败，返回错误提示信息给终端用户
    public static GenericResponse<HashMap> onError(String msg) {
        return new GenericResponse<HashMap>(ErrEnum.ERROR_HINT.getErrCode(), msg, Const.EMPTY_MAP);
    }
}
