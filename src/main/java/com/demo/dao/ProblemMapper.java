package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dao.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: ProblemMapper
 * Package: com.demo.dao
 * Description: 问题持久层
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 3:04
 * @Version: v1.0
 */

@Mapper
public interface ProblemMapper extends BaseMapper<Problem> {
    int deleteByPrimaryKey(Long id);

    int insert(Problem record);

//    int insertSelective(Problem record);

    Problem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKey(Problem record);

    List<Problem> selectByProjectById(String questionnaire_id);

    List<Problem> selectChoiceById(String questionnaire_id);
}
