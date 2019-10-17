package com.rabbitmq.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户主表
 */
@Setter
@Getter
@Accessors(chain = true)
public class UserEntity implements Serializable{

    /**
     * 用户id
     */
    private Integer u_id;

    /**
     * 用户登录名
     */
    private String u_name;

    /**
     * 用户昵称
     */
    private String u_name_long;
}
