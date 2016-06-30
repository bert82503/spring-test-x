package com.test.tutorial.web.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User info request param.
 *
 * @author	Bert Lee
 * @version 2014-8-20
 */
public class UserInfoParam extends UserIdParam {

	private String name;

    public String getName() {
        return name;
    }

    // 变量名与请求参数名不一样，在 @RequestBody 中用到
    // https://github.com/FasterXML/jackson-annotations#annotations-for-renaming-properties
    @JsonProperty(value = "userName", required = true)
    @NotNull(message = "'userName' param is null")
    @Size(min = 1, message = "'userName' param is empty")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfoParam{" +
                "name='" + name +
                "', userId=" + getUserId() +
                '}';
    }

}
