package com.demo.service;

import com.demo.dao.OptionMapper;
import com.demo.dao.ProblemMapper;
import com.demo.dao.entity.Option;
import com.demo.dao.entity.Problem;
import com.demo.utils.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: ProblemService
 * Package: com.demo.service
 * Description: 问题服务层
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 3:02
 * @Version: v1.0
 */

@Service
public class ProblemService {
    @Resource
    ProblemMapper problemMapper;
    @Resource
    OptionMapper optionMapper;

    public int addBatchsProblems(List<Problem> problems) {
        int count = 0;
        for (Problem problem : problems) {
            String problemId = UUIDUtil.getOneUUID();
            problem.setId(problemId);
            count+=problemMapper.insert(problem);
            List<Option> options = problem.getOption();
            for (Option insertedOption : options) {
                insertedOption.setId(UUIDUtil.getOneUUID());
                insertedOption.setProblemId(problemId);
                optionMapper.insert(insertedOption);
            }
        }
        return count;
    }

    public Problem getProblemById(String id) {
        Problem problem = problemMapper.selectByPrimaryKey(id);
        Problem returnProblem = new Problem();
        BeanUtils.copyProperties(problem,returnProblem);
        return returnProblem;
    }

    public List<Problem> getProblemProjectById(String questionnaire_id) {
        List<Problem> problems = problemMapper.selectByProjectById(questionnaire_id);
        return problems;
    }
}
