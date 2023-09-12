package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dao.entity.QuestionnaireInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: QuestionnaireInfoMapper
 * Package: com.demo.dao
 * Description: 问卷持久层
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 2:41
 * @Version: v1.0
 */

@Mapper
public interface QuestionnaireInfoMapper extends BaseMapper<QuestionnaireInfo> {

    int deleteByPrimaryKey(String id);

    int insert(QuestionnaireInfo record);

    int insertSelective(QuestionnaireInfo record);

    QuestionnaireInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QuestionnaireInfo record);

    int updateByPrimaryKey(QuestionnaireInfo record);

    List<QuestionnaireInfo> selectQuestionnaireListByProjectId(String project_id);

}
