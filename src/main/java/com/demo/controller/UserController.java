package com.demo.controller;

import com.demo.beans.HttpResponseEntity;
import com.demo.dao.entity.UserEntity;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: UserController
 * Package: com.demo.controller
 * Description: 用户控制器
 *
 * @Author: 王方舟
 * @Create: 2023-06-08 - 17:48
 * @Version: v1.0
 */

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/userLogin",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity){
        List<UserEntity> result = userService.selectUserInfo(userEntity);
        if(!CollectionUtils.isEmpty(result)) {
            return HttpResponseEntity.buildSuccess(result, "登录成功");
        }
        return HttpResponseEntity.buildError(result, "登录失败");
    }

    /**
     * 查询用户列表
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/queryUserList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryUserList(@RequestBody UserEntity userEntity){
        List<UserEntity> result = userService.queryUserList(userEntity);
        if(!CollectionUtils.isEmpty(result)) {
            return HttpResponseEntity.buildSuccess(result, "加载成功");
        }
        return HttpResponseEntity.buildError(result, "加载失败");
    }

    /**
     * 新增用户
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/addUserInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity userEntity){
        int result = userService.addUserInfo(userEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "创建成功");
        }
        return HttpResponseEntity.buildError(result, "创建失败或用户名已存在");
    }

    /**
     * 修改用户信息
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/modifyUserInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity userEntity){
        int result = userService.modifyUserInfo(userEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "修改成功");
        }
        return HttpResponseEntity.buildError(result, "修改失败");
    }

    /**
     * 删除用户
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/deleteUserinfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteUserById(@RequestBody UserEntity userEntity){
        int result = userService.deleteUserById(userEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "删除成功");
        }
        return HttpResponseEntity.buildError(result, "删除失败");
    }

    /**
     * 假删，保留数据
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/closeUser",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity closeUser(@RequestBody UserEntity userEntity){
        int result = userService.closeUser(userEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "关闭成功");
        }
        return HttpResponseEntity.buildError(result, "关闭失败");
    }
}
