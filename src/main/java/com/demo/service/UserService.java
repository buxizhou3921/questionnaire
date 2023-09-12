package com.demo.service;

import com.demo.dao.entity.UserEntity;
import com.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.UserEntityMapper;
import java.util.List;

/**
 * ClassName: UserService
 * Package: com.demo.service
 * Description: 用户服务层
 *
 * @Author: 王方舟
 * @Create: 2023-06-08 - 17:48
 * @Version: v1.0
 */

@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    public List<UserEntity> selectUserInfo(UserEntity userEntity) {
        return userEntityMapper.selectUserInfo(userEntity);
    }

    public List<UserEntity> queryUserList(UserEntity userEntity){
        return userEntityMapper.queryUserList(userEntity);
    }

    public int addUserInfo(UserEntity userEntity){
        userEntity.setId(UUIDUtil.getOneUUID());
        String name=userEntity.getUsername();
        int duplicate = userEntityMapper.queryUserListByName(name);
        if (duplicate == 0) {
            return userEntityMapper.insert(userEntity);
        }
        return 0; // 用户名称重复或者创建失败
    }

    public int modifyUserInfo(UserEntity userEntity){
        return userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    public int deleteUserById(UserEntity userEntity){
        return userEntityMapper.deleteUserById(userEntity);
    }

    public int closeUser(UserEntity userEntity){
        return userEntityMapper.updateStatus(userEntity);
    }

}
