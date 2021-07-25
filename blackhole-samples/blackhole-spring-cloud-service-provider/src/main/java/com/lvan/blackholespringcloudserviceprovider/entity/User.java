package com.lvan.blackholespringcloudserviceprovider.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Data
public class User {

    @TableId
    private Integer id;
    private String name;
    private Integer age;
}
