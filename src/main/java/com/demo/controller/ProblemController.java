package com.demo.controller;

import com.demo.beans.HttpResponseEntity;
import com.demo.dao.entity.Problem;
import com.demo.service.ProblemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: ProblemController
 * Package: com.demo.controller
 * Description: 问题控制器
 *
 * @Author: 王方舟
 * @Create: 2023-06-24 - 3:01
 * @Version: v1.0
 */

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Resource
    private ProblemService problemService;


    @GetMapping("/{questionnaire_id}")
    public HttpResponseEntity getProblemByProjectId(@PathVariable("questionnaire_id") String questionnaire_id){
        List<Problem> problem =  problemService.getProblemProjectById(questionnaire_id);
        if (problem!=null){
            return HttpResponseEntity.buildSuccess(problem,"查找成功");
        }else {
            return HttpResponseEntity.buildError("插入失败");
        }
    }
}
