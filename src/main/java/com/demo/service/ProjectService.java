package com.demo.service;

import com.demo.dao.ProjectEntityMapper;
import com.demo.dao.entity.ProjectEntity;
import com.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProjectService
 * Package: com.demo.service
 * Description:
 *
 * @Author: wfz
 * @Create: 2023-06-08 - 17:57
 * @Version: v1.0
 */

@Service
public class ProjectService {

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity){
        return projectEntityMapper.queryProjectList(projectEntity);
    }

    public int addProjectInfo(ProjectEntity projectEntity){
        projectEntity.setId(UUIDUtil.getOneUUID());
        String name=projectEntity.getProjectName();
        int duplicate = projectEntityMapper.queryProjectListByName(name);
        if (duplicate == 0) {
            return projectEntityMapper.insert(projectEntity);
        }
        return 0; // 项目名称重复或者创建失败
    }

    public int modifyProjectInfo(ProjectEntity projectEntity){
        return projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
    }

    public int deleteProjectById(ProjectEntity projectEntity){
        return projectEntityMapper.deleteProjectById(projectEntity);
    }
}
