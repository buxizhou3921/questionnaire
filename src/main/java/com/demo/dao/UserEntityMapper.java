package com.demo.dao;

import com.demo.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * ClassName: UserEntityMapper
 * Package: com.demo.dao
 * Description: 用户持久层
 *
 * @Author: 王方舟
 * @Create: 2023-06-08 - 17:48
 * @Version: v1.0
 */

@Mapper
public interface UserEntityMapper {

    List<UserEntity> queryUserList(UserEntity userEntity);

    /**
     * 插入用户
     * @param userEntity
     * @return
     */
    int insert(UserEntity userEntity);

    /**
     * 根据id删除用户
     * @param userEntity
     * @return
     */
    int deleteUserById(UserEntity userEntity);

    /**
     * 更新用户信息
     * @param userEntity
     * @return
     */
    int updateByPrimaryKeySelective(UserEntity userEntity);

    List<UserEntity> selectUserInfo(UserEntity userEntity);

    /**
     * 更新用户状态
     * @param userEntity
     * @return
     */
    int updateStatus(UserEntity userEntity);

    /**
     * 根据用户名查询用户（用户名重复验证）
     * @param username
     * @return
     */
    int queryUserListByName(String username);
}
