package com.demo;

import com.demo.beans.HttpResponseEntity;
import com.demo.controller.ProjectController;
import com.demo.controller.UserController;
import com.demo.dao.entity.ProjectEntity;
import com.demo.dao.entity.UserEntity;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private UserController userController;

    @Resource
    private ProjectController projectController;

    Logger log = Logger.getLogger(DemoApplicationTests.class);

    @Test
    public void testUserLogin() {
        // 测试登录成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("admin");
        userEntity1.setPassword("123");
        HttpResponseEntity httpResponseEntity1 = userController.userLogin(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试登录成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试登录失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("admin");
        userEntity2.setPassword("567");
        HttpResponseEntity httpResponseEntity2 = userController.userLogin(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试登录失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testQueryUserList(){
        // 测试用户查询成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("admin");
        HttpResponseEntity httpResponseEntity1 = userController.queryUserList(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户查询成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试用户查询失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("NULL");
        HttpResponseEntity httpResponseEntity2 = userController.queryUserList(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户查询失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testAddUserInfo(){
        // 测试用户添加成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("wfz_test");
        userEntity1.setPassword("20213921");
        HttpResponseEntity httpResponseEntity1 = userController.addUserInfo(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户添加成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试用户添加失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity1.setUsername("admin");
        userEntity1.setPassword("123");
        HttpResponseEntity httpResponseEntity2 = userController.addUserInfo(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户添加失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testModifyUserInfo(){
        // 测试用户修改成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("41eeca10e60843d4a0bda283611dd259");
        userEntity1.setUsername("wfz2");
        HttpResponseEntity httpResponseEntity1 = userController.modifyUserInfo(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户修改成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试用户修改失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("");
        userEntity2.setUsername("wfz2");
        HttpResponseEntity httpResponseEntity2 = userController.modifyUserInfo(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户修改失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testDeleteUserById(){
        // 测试用户删除成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("2ac4241685ff4566b09d6f17e6dbd9f8");
        HttpResponseEntity httpResponseEntity1 = userController.deleteUserById(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户删除成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试用户删除失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("");
        HttpResponseEntity httpResponseEntity2 = userController.deleteUserById(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试用户删除失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testCloseUser(){
        // 测试关闭成功情况
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("41eeca10e60843d4a0bda283611dd259");
        HttpResponseEntity httpResponseEntity1 = userController.closeUser(userEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试关闭成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试关闭失败情况
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("");
        HttpResponseEntity httpResponseEntity2 = userController.closeUser(userEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试关闭失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testQueryProjectList(){
        // 测试项目查询成功情况
        ProjectEntity projectEntity1 =new ProjectEntity();
        projectEntity1.setProjectName("项目一");
        HttpResponseEntity httpResponseEntity1 = projectController.queryProjectList(projectEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目查询成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试项目查询失败情况
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setProjectName("NULL");
        HttpResponseEntity httpResponseEntity2 = projectController.queryProjectList(projectEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目查询失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testAddProjectInfo(){
        // 测试项目添加成功情况
        ProjectEntity projectEntity1 =new ProjectEntity();
        projectEntity1.setProjectName("project_test");
        projectEntity1.setProjectContent("projectContent");
        HttpResponseEntity httpResponseEntity1 = projectController.addProjectInfo(projectEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目添加成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试项目添加失败情况
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setProjectName("项目一");
        projectEntity2.setProjectContent("项目一描述");
        HttpResponseEntity httpResponseEntity2 = projectController.addProjectInfo(projectEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目添加失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testModifyProjectInfo(){
        // 测试项目修改成功情况
        ProjectEntity projectEntity1 =new ProjectEntity();
        projectEntity1.setId("5dc97f1fb00247418829e8a9d3201f8e");
        projectEntity1.setProjectContent("Description");
        HttpResponseEntity httpResponseEntity1 = projectController.modifyProjectInfo(projectEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目修改成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试项目修改失败情况
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity1.setId("");
        projectEntity2.setProjectContent("Content");
        HttpResponseEntity httpResponseEntity2 = projectController.modifyProjectInfo(projectEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目修改失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }

    @Test
    public void testDeleteProjectById(){
        // 测试项目删除成功情况
        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setId("c2d4d8889b1341e1be90f6a15ba8af5d");
        HttpResponseEntity httpResponseEntity1 = projectController.deleteProjectById(projectEntity1);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目删除成功情况");
        log.info(httpResponseEntity1.getData().toString());
        log.info(">>" + httpResponseEntity1.getMessage().toString());

        // 测试项目删除失败情况
        ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity1.setId("");
        HttpResponseEntity httpResponseEntity2 = projectController.deleteProjectById(projectEntity2);
        log.info("-----------------------------------------------------------------");
        log.info(">>测试项目删除失败情况");
        log.info(httpResponseEntity2.getData().toString());
        log.info(">>" + httpResponseEntity2.getMessage().toString());
    }



}
