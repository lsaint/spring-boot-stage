package com.ethan.stage.common;

import com.ethan.stage.common.enums.ErrEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1;

    private int code;
    private String msg;

    public APIException(String msg) {
        this.code = ErrEnum.ERROR_HINT.getErrCode();
        this.msg = msg;
    }
}
