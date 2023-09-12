package com.demo.service;

import com.demo.dao.ProjectEntityMapper;
import com.demo.dao.entity.ProjectEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectEntityMapper mockProjectEntityMapper;

    @InjectMocks
    private ProjectService projectServiceUnderTest;

    @Test
    void testQueryProjectList() {

        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");
        projectEntity.setUserId("userId");
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("createdBy");

        final ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setId("id");
        projectEntity1.setUserId("userId");
        projectEntity1.setProjectName("projectName");
        projectEntity1.setProjectContent("projectContent");
        projectEntity1.setCreatedBy("createdBy");
        final List<ProjectEntity> expectedResult = Arrays.asList(projectEntity1);

        final ProjectEntity projectEntity2 = new ProjectEntity();
        projectEntity2.setId("id");
        projectEntity2.setUserId("userId");
        projectEntity2.setProjectName("projectName");
        projectEntity2.setProjectContent("projectContent");
        projectEntity2.setCreatedBy("createdBy");
        final List<ProjectEntity> projectEntities = Arrays.asList(projectEntity2);
        final ProjectEntity projectEntity3 = new ProjectEntity();
        projectEntity3.setId("id");
        projectEntity3.setUserId("userId");
        projectEntity3.setProjectName("projectName");
        projectEntity3.setProjectContent("projectContent");
        projectEntity3.setCreatedBy("createdBy");
        when(mockProjectEntityMapper.queryProjectList(projectEntity3)).thenReturn(projectEntities);

        final List<ProjectEntity> result = projectServiceUnderTest.queryProjectList(projectEntity);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryProjectList_ProjectEntityMapperReturnsNoItems() {

        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");
        projectEntity.setUserId("userId");
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("createdBy");

        final ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setId("id");
        projectEntity1.setUserId("userId");
        projectEntity1.setProjectName("projectName");
        projectEntity1.setProjectContent("projectContent");
        projectEntity1.setCreatedBy("createdBy");
        when(mockProjectEntityMapper.queryProjectList(projectEntity1)).thenReturn(Collections.emptyList());

        final List<ProjectEntity> result = projectServiceUnderTest.queryProjectList(projectEntity);

        assertThat(result).isEqualTo(Collections.emptyList());
    }

//    @Test
//    void testAddProjectInfo() {
//
//        final ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setId("id");
//        projectEntity.setUserId("userId");
//        projectEntity.setProjectName("projectName");
//        projectEntity.setProjectContent("projectContent");
//        projectEntity.setCreatedBy("createdBy");
//
//        when(mockProjectEntityMapper.queryProjectListByName("projectName")).thenReturn(0);
//
//        final ProjectEntity projectEntity1 = new ProjectEntity();
//        projectEntity1.setId("id");
//        projectEntity1.setUserId("userId");
//        projectEntity1.setProjectName("projectName");
//        projectEntity1.setProjectContent("projectContent");
//        projectEntity1.setCreatedBy("createdBy");
//        when(mockProjectEntityMapper.insert(projectEntity1)).thenReturn(0);
//
//        final int result = projectServiceUnderTest.addProjectInfo(projectEntity);
//    }

    @Test
    void testModifyProjectInfo() {

        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");
        projectEntity.setUserId("userId");
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("createdBy");

        final ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setId("id");
        projectEntity1.setUserId("userId");
        projectEntity1.setProjectName("projectName");
        projectEntity1.setProjectContent("projectContent");
        projectEntity1.setCreatedBy("createdBy");
        when(mockProjectEntityMapper.updateByPrimaryKeySelective(projectEntity1)).thenReturn(0);

        final int result = projectServiceUnderTest.modifyProjectInfo(projectEntity);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testDeleteProjectById() {

        final ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("id");
        projectEntity.setUserId("userId");
        projectEntity.setProjectName("projectName");
        projectEntity.setProjectContent("projectContent");
        projectEntity.setCreatedBy("createdBy");

        final ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setId("id");
        projectEntity1.setUserId("userId");
        projectEntity1.setProjectName("projectName");
        projectEntity1.setProjectContent("projectContent");
        projectEntity1.setCreatedBy("createdBy");
        when(mockProjectEntityMapper.deleteProjectById(projectEntity1)).thenReturn(0);

        final int result = projectServiceUnderTest.deleteProjectById(projectEntity);

        assertThat(result).isEqualTo(0);
    }
}
