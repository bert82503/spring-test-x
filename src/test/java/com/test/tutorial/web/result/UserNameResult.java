package com.test.tutorial.web.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User name response result.
 *
 * @author	Bert Lee
 * @version 2014-8-19
 */
public class UserNameResult extends BaseResult {

	private String name;

    @JsonProperty(value = "name")
    public String getName() {
        return name;
    }

    public UserNameResult setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "UserNameResult{" +
                "name='" + name +
                "', code=" + getCode() +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
