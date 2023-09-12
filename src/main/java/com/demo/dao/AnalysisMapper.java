package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.beans.AnalysisOption;
import com.demo.dao.entity.Analysis;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalysisMapper extends BaseMapper<Analysis> {

    List<AnalysisOption> analysisByQuestionId(String id);

    List<AnalysisOption> analysisByQuestionName(String name, String projectId);
}
