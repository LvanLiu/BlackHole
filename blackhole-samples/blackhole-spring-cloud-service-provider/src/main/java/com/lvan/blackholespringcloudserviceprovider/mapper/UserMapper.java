package com.lvan.blackholespringcloudserviceprovider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvan.blackholespringcloudserviceprovider.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Lvan
 * @since 2021/7/25
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
