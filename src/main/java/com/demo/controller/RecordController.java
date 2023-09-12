package com.demo.controller;

import com.demo.beans.HttpResponseEntity;
import com.demo.dao.entity.Record;
import com.demo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: RecordController
 * Package: com.demo.controller
 * Description: 回答记录控制器
 *
 * @Author: 王方舟
 * @Create: 2023-06-24 - 3:09
 * @Version: v1.0
 */

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;

    @PostMapping("/add")
    public HttpResponseEntity addRecord(@RequestBody Record record){
        int res = recordService.addRecord(record);
        if (res!=0) return HttpResponseEntity.buildSuccess(record,"添加成功");
        else return HttpResponseEntity.buildError("添加失败");
    }

    @PostMapping("/{projectId}")
    public HttpResponseEntity getRecordListByProjectId(@PathVariable("projectId") String projectId){
        List<Record> records = recordService.getRecordListByProjectId(projectId);
        return HttpResponseEntity.buildSuccess(records,"查找成功");
    }

    @GetMapping("/{recordId}")
    public HttpResponseEntity getRecordById(@PathVariable("recordId") String recordId){
        Record record = recordService.getRecordById(recordId);
        if (record!=null){
            return HttpResponseEntity.buildSuccess(record,"查询成功");
        }else {
            return HttpResponseEntity.buildError("查询出错");
        }
    }
}
