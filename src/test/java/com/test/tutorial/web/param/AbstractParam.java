package com.test.tutorial.web.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Base request param.
 *
 * @author	Bert Lee
 * @version 2014-8-19
 */
// https://github.com/FasterXML/jackson-annotations#annotations-for-ignoring-properties
@JsonIgnoreProperties(ignoreUnknown = true) // 忽略多传的参数
public abstract class AbstractParam {
    //
}
