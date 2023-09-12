package com.demo.dao;

import com.demo.dao.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName: RecordMapper
 * Package: com.demo.dao
 * Description:
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 3:12
 * @Version: v1.0
 */

@Mapper
public interface RecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    List<Record> getRecordListByProjectId(String projectId);
}
