package com.test.tutorial.web.result;

/**
 * 错误码和错误信息。
 *
 * @author xingle
 * @since 2016年06月30日 15:52
 */
public enum ErrorCodeMessage {

    /**
     * 用户不存在
     */
    USER_NOT_EXISTS(1000401, "User not exists"),
    OPERATION_FAIL(1000402, "Operation is fail")
    ;

    private final int code;

    private final String message;

    ErrorCodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
