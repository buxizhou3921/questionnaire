package com.demo.controller;

import com.demo.beans.HttpResponseEntity;
import com.demo.dao.entity.ProjectEntity;
import com.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: ProjectController
 * Package: com.demo.controller
 * Description: 项目控制器
 *
 * @Author: 王方舟
 * @Create: 2023-06-08 - 17:54
 * @Version: v1.0
 */


@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * 查询项目列表
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/queryProjectList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity){
        List<ProjectEntity> result = projectService.queryProjectList(projectEntity);
        if(!CollectionUtils.isEmpty(result)) {
            return HttpResponseEntity.buildSuccess(result, "加载成功");
        }
        return HttpResponseEntity.buildError(result, "加载失败");
    }

    /**
     * 新增项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/addProjectInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity){
        int result = projectService.addProjectInfo(projectEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "创建成功");
        }
        return HttpResponseEntity.buildError(result, "创建失败或项目名已存在");
    }

    /**
     * 修改项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/modifyProjectInfo",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity){
        int result = projectService.modifyProjectInfo(projectEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "修改成功");
        }
        return HttpResponseEntity.buildError(result, "修改失败");
    }

    /**
     * 删除项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/deleteProjectById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity projectEntity){
        int result = projectService.deleteProjectById(projectEntity);
        if(result!=0) {
            return HttpResponseEntity.buildSuccess(result, "删除成功");
        }
        return HttpResponseEntity.buildError(result, "删除失败");
    }
}
