package com.demo.controller;

import com.demo.beans.HttpResponseEntity;
import com.demo.dao.entity.Problem;
import com.demo.dao.entity.QuestionnaireInfo;
import com.demo.service.ProblemService;
import com.demo.service.QuestionnaireService;
import com.demo.utils.IPUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: QuestionnaireController
 * Package: com.demo.controller
 * Description: 问卷控制器
 *
 * @Author: 王方舟
 * @Create: 2023-06-19 - 16:22
 * @Version: v1.0
 */

@Slf4j
@RestController
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;
    private final ProblemService problemService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/add")
    public HttpResponseEntity addQuestionnaire(@RequestBody QuestionnaireInfo questionnaireInfo){
        log.info(questionnaireInfo.toString());
        System.out.println(questionnaireService);
        String res = questionnaireService.addQuestionnaire(questionnaireInfo);
        if (res==null){
            return HttpResponseEntity.buildError("新增问卷失败");
        }else {
            return HttpResponseEntity.buildSuccess(res,"新增成功");
        }
    }

    @PostMapping("/modifyQuestionnaire/{quesId}")
    public HttpResponseEntity modifyQuestionnaire(@RequestBody List<Problem> problems, @PathVariable("quesId") String quesId){
        System.out.println(problems);
        for (Problem problem : problems) {
            problem.setQuestionnaireId(quesId);
        }
        int res = problemService.addBatchsProblems(problems);
        if (res==0){
            return HttpResponseEntity.buildError("设置问卷问题失败");
        }else {
            return HttpResponseEntity.buildSuccess(res,"新增成功");
        }
    }

    @GetMapping("/list/{projectId}")
    public HttpResponseEntity getQuestionnaireList(@PathVariable("projectId") String projectId){
        List<QuestionnaireInfo> list = questionnaireService.getQuestionnaireList(projectId);
        return HttpResponseEntity.buildSuccess(list,"获取成功");
    }
    @GetMapping("/{id}")
    public HttpResponseEntity getQuestionnaireInfoById(@PathVariable("id") String id){
        QuestionnaireInfo questionnaireInfo = questionnaireService.getQuestionnaireById(id);
        if (questionnaireInfo!=null){
            return HttpResponseEntity.buildSuccess(questionnaireInfo,"查找问卷成功");
        }else {
            return HttpResponseEntity.buildError("没有对应的问卷");
        }
    }
    @DeleteMapping("/{id}")
    public HttpResponseEntity deleteQuestionnaireById(@PathVariable("id") String id){
        int res = questionnaireService.deleteQuestionnaireById(id);
        if (res==0){
            return HttpResponseEntity.buildError("删除问卷失败");
        }else {
            return HttpResponseEntity.buildSuccess(res,"删除成功");
        }
    }

    @PostMapping("/fabu/{id}")
    public HttpResponseEntity fabu(@PathVariable("id") String id){
        System.out.println();
        return HttpResponseEntity.buildSuccess(IPUtil.getIp()+':'+port,"发布成功");
    }
}
