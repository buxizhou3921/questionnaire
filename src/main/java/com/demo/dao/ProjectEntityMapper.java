package com.demo.dao;

import com.demo.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProjectEntityMapper
 * Package: com.demo.dao
 * Description: 项目持久层
 *
 * @Author: wfz
 * @Create: 2023-06-08 - 17:56
 * @Version: v1.0
 */


@Mapper
public interface ProjectEntityMapper {

    /**
     * 查询项目列表
     * @param projectEntity
     * @return
     */
    List<ProjectEntity> queryProjectList(ProjectEntity projectEntity);

    /**
     * 插入项目
     * @param projectEntity
     * @return
     */
    int insert(ProjectEntity projectEntity);

    /**
     * 根据id删除项目
     * @param projectEntity
     * @return
     */
    int deleteProjectById(ProjectEntity projectEntity);

    /**
     * 更新项目
     * @param projectEntity
     * @return
     */
    int updateByPrimaryKeySelective(ProjectEntity projectEntity);

    /**
     * 根据项目名查询项目（项目名重复验证）
     * @param projectName
     * @return
     */
    int queryProjectListByName(String projectName);
}
