package com.example.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 丁祥
 * @since 2022-10-04
 */
@Getter
@Setter
@TableName("user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String phone;

    private Short level;
    private String rId;
    /**
     * 余额
     */
    private BigDecimal money;

    private String createTime;
}
