package com.ethan.stage.common.enums;

// 0 - 999      公共
// 1000 - 1999  业务1
// 2000 - 2999  业务2
public enum ErrEnum {
    // 公共
    SUCCESS(0, "success"),
    UNKNOWN_ERROR(-1, "未知错误"),
    INTERNAL_SERVER_ERROR(500, "内部错误"),
    BAD_REQUEST(400, "无效请求"),
    NOT_FOUND(404, "访问地址不存在"),
    INVALID_TOKEN(401, "token非法"),
    ERROR_HINT(499, "显示给终端用户的信息"),

    // 业务1
    BIZ1_ERROR(1000, "业务1错误"),
    // 业务2
    BIZ2_ERROR(2000, "业务2错误");

    private int errCode;
    private String errMsg;

    ErrEnum(int errCode, String errMsg) {
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
