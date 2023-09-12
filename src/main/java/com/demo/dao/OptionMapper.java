package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.dao.entity.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * ClassName: OptionMapper
 * Package: com.demo.dao
 * Description: 选项持久层
 *
 * @Author: 王方舟
 * @Create: 2023-06-25 - 3:19
 * @Version: v1.0
 */

@Mapper
public interface OptionMapper extends BaseMapper<Option> {
    int deleteByPrimaryKey(String id);

    int insert(Option record);

    int insertSelective(Option record);

    Option selectByPrimaryKey(String id);

    @Select("select * from choose_term where problem_id = #{problemId}")
    Option selectByProblemId(String problemId);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKey(Option record);
}
