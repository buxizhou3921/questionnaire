package com.demo.service;

import com.demo.dao.RecordMapper;
import com.demo.dao.entity.Record;
import com.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: RecordService
 * Package: com.demo.service
 * Description: 回答记录服务层
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 3:11
 * @Version: v1.0
 */

@Service
public class RecordService {
    @Autowired
    RecordMapper recordMapper;

    public int addRecord(Record record) {
        record.setId(UUIDUtil.getOneUUID());
        return recordMapper.insertSelective(record);
    }

    public List<Record> getRecordListByProjectId(String projectId) {
        return recordMapper.getRecordListByProjectId(projectId);
    }

    public Record getRecordById(String recordId) {
        return recordMapper.selectByPrimaryKey(recordId);
    }
}
