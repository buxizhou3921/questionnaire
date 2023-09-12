package com.demo.service;

import com.demo.dao.QuestionnaireInfoMapper;
import com.demo.dao.entity.QuestionnaireInfo;
import com.demo.utils.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: QuestionnaireService
 * Package: com.demo.service
 * Description: 问卷服务层
 *
 * @Author: 王方舟
 * @Create: 2023-06-24 - 2:39
 * @Version: v1.0
 */

@Service
public class QuestionnaireService {
    @Resource
    private QuestionnaireInfoMapper questionnaireInfoMapper;

    public String addQuestionnaire(QuestionnaireInfo questionnaireInfo) {
        String questionnaireId = UUIDUtil.getOneUUID();
        questionnaireInfo.setId(questionnaireId);
        questionnaireInfo.setStartTime(null);
        int insert = questionnaireInfoMapper.insert(questionnaireInfo);
        if (insert==0){
            return null;
        }else {
            return  questionnaireId;
        }
    }

    public List<QuestionnaireInfo> getQuestionnaireList(String projectId) {
        return questionnaireInfoMapper.selectQuestionnaireListByProjectId(projectId);
    }

    public int deleteQuestionnaireById(String id) {
        return questionnaireInfoMapper.deleteByPrimaryKey(id);
    }

    public QuestionnaireInfo getQuestionnaireById(String id) {
        return questionnaireInfoMapper.selectByPrimaryKey(id);
    }
}
