package com.test.tutorial.web.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base response result.
 *
 * @author Bert Lee
 * @version 2014-8-20
 */
public class BaseResult {

    private int code;

    private String message;

    public BaseResult() {
        this.code = 0;
        this.message = "OK";
    }

    @JsonProperty(value = "code")
    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public BaseResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public BaseResult setError(ErrorCodeMessage errorCodeMessage) {
        code = errorCodeMessage.code();
        message = errorCodeMessage.message();
        return this;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
