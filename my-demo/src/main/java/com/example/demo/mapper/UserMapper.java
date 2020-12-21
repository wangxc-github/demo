package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangxc
 */
@Mapper
public interface UserMapper {

    /**
     * 通过map查询用户
     * @param map 参数
     * @return
     */
    User getUserByMap(Map map);

}
