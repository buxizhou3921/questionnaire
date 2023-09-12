package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.beans.AnalysisOption;
import com.demo.beans.AnalysisQuestion;
import com.demo.beans.ChooseTerm;
import com.demo.beans.ProblemEntity;
import com.demo.dao.AnalysisMapper;
import com.demo.dao.OptionMapper;
import com.demo.dao.ProblemMapper;
import com.demo.dao.entity.Analysis;
import com.demo.dao.entity.Option;
import com.demo.dao.entity.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class AnalysisService extends ServiceImpl<AnalysisMapper, Analysis> {

    @Autowired
    private AnalysisMapper analysisMapper;
    
    @Autowired
    private ProblemMapper problemMapper;
    
    @Autowired
    private OptionMapper optionMapper;

    /**
     * 将问题伴随选项的集合罗列出来, 加载问卷
     */
    public List<ProblemEntity> listQuestionsWithOptions(String questionnaireId){


        List<Problem> questionEntities = problemMapper.selectChoiceById(questionnaireId);

        List<ProblemEntity> problemEntitys = new ArrayList<>();

        //将Entities置入Bean容器以便传给前端
        for(Problem problem:questionEntities){

            ProblemEntity ProblemEntity = new ProblemEntity();

            ProblemEntity.setId(problem.getId());
            ProblemEntity.setProblemName(problem.getProblemName());
            ProblemEntity.setType(problem.getProblemType());

            //接下来生成ChooseTerm集合
            List<ChooseTerm> chooseTerms = new ArrayList<>();//声明ChooseTerm集合

            LambdaQueryWrapper<Option> opLqw = new LambdaQueryWrapper<>();
            opLqw.eq(Option::getProblemId, problem.getId());

            List<Option> optionEntities = optionMapper.selectList(opLqw);

            for(Option option : optionEntities){
                ChooseTerm chooseTerm = new ChooseTerm();//声明ChooseTerm
                chooseTerm.setId(option.getId());
                chooseTerm.setChooseTerm(option.getChooseTerm());
                chooseTerms.add(chooseTerm);//添加ChooseTerm
            }

            ProblemEntity.setOption(chooseTerms);//添加ChooseTerms

            problemEntitys.add(ProblemEntity);//添加ProblemEntity至ProblemEntitys
        }
        return problemEntitys;
    }

    /**
     * 加载Record数据
     */
    public AnalysisQuestion analysisByQuestionId(String id){

        List<AnalysisOption> analysisOptions = this.baseMapper.analysisByQuestionId(id);

        Integer total = 0;

        for(AnalysisOption analysisOption : analysisOptions){
            total += analysisOption.getTotal();
        }
        AnalysisQuestion analysisQuestion = new AnalysisQuestion(total, analysisOptions);

        return analysisQuestion;
    }


    public AnalysisQuestion analysisByQuestionName(String name, String projectId){

        List<AnalysisOption> analysisOptions = this.baseMapper.analysisByQuestionName(name, projectId);

        Integer total = 0;

        for(AnalysisOption analysisOption : analysisOptions){
            total += analysisOption.getTotal();
        }
        AnalysisQuestion analysisQuestion = new AnalysisQuestion(total, analysisOptions);

        return analysisQuestion;
    }
}
