package com.demo.controller;

import com.demo.beans.AnalysisQuestion;
import com.demo.beans.QueryAnalysis;
import com.demo.dao.entity.Analysis;
import com.demo.dao.entity.QuestionnaireInfo;
import com.demo.service.AnalysisService;
import com.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import com.demo.beans.HttpResponseEntity;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @PostMapping("/getAllProblems")
    public HttpResponseEntity getAllProblems(@RequestBody QueryAnalysis queryAnalysis){
        return  HttpResponseEntity.buildSuccess(analysisService.listQuestionsWithOptions(queryAnalysis.getQuestionnaireId()),
                "加载成功" );
    }

    @PostMapping("/listById")
    public HttpResponseEntity listById(@RequestBody QueryAnalysis queryAnalysis){

        return HttpResponseEntity.buildSuccess(analysisService.analysisByQuestionId(queryAnalysis.getQuestionId())
                , "加载成功");
    }

    @PostMapping("/listByName")
    public HttpResponseEntity listByName(@RequestBody QueryAnalysis queryAnalysis){

        return HttpResponseEntity.buildSuccess(analysisService.analysisByQuestionName(queryAnalysis.getQuestionName(),
                queryAnalysis.getProjectId()),"加载成功");
    }

    @PostMapping("/add")
    public HttpResponseEntity addRecordList(@RequestBody List<Analysis> analysisList){



        for(Analysis analysis : analysisList){
            System.out.print("传过来的记录如下所示：");
            System.out.println(analysisList);
            analysis.setId(UUIDUtil.getOneUUID());
            analysis.setAnswerTime(Date.valueOf(LocalDate.now()));
        }

            analysisService.saveBatch(analysisList);

        return HttpResponseEntity.buildSuccess(1, "创建成功");
    }

}

