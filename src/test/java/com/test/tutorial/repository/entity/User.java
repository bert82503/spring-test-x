package com.test.tutorial.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @date 2014-7-25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
//@TableName(value = "t_digital_user")
public class User implements Serializable {
	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户名称
	 */
	@TableField(value = "user_name")
	private String userName;

	private static final long serialVersionUID = 1L;
}
