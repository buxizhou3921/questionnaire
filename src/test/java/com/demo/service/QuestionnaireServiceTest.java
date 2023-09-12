package com.demo.service;

import com.demo.dao.QuestionnaireInfoMapper;
import com.demo.dao.entity.QuestionnaireInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionnaireServiceTest {

    @Mock
    private QuestionnaireInfoMapper mockQuestionnaireInfoMapper;

    @InjectMocks
    private QuestionnaireService questionnaireServiceUnderTest;

//    @Test
//    void testAddQuestionnaire() {
//
//        final QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
//        questionnaireInfo.setId("id");
//        questionnaireInfo.setProjectId("projectId");
//        questionnaireInfo.setQuestionnaireName("questionnaireName");
//        questionnaireInfo.setDescription("description");
//        questionnaireInfo.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//
//        final QuestionnaireInfo record = new QuestionnaireInfo();
//        record.setId("id");
//        record.setProjectId("projectId");
//        record.setQuestionnaireName("questionnaireName");
//        record.setDescription("description");
//        record.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
//        when(mockQuestionnaireInfoMapper.insert(record)).thenReturn(0);
//
//        final String result = questionnaireServiceUnderTest.addQuestionnaire(questionnaireInfo);
//    }

    @Test
    void testGetQuestionnaireList() {

        final QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
        questionnaireInfo.setId("id");
        questionnaireInfo.setProjectId("projectId");
        questionnaireInfo.setQuestionnaireName("questionnaireName");
        questionnaireInfo.setDescription("description");
        questionnaireInfo.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<QuestionnaireInfo> expectedResult = Arrays.asList(questionnaireInfo);

        final QuestionnaireInfo questionnaireInfo1 = new QuestionnaireInfo();
        questionnaireInfo1.setId("id");
        questionnaireInfo1.setProjectId("projectId");
        questionnaireInfo1.setQuestionnaireName("questionnaireName");
        questionnaireInfo1.setDescription("description");
        questionnaireInfo1.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<QuestionnaireInfo> questionnaireInfos = Arrays.asList(questionnaireInfo1);
        when(mockQuestionnaireInfoMapper.selectQuestionnaireListByProjectId("projectId"))
                .thenReturn(questionnaireInfos);

        final List<QuestionnaireInfo> result = questionnaireServiceUnderTest.getQuestionnaireList("projectId");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetQuestionnaireList_QuestionnaireInfoMapperReturnsNoItems() {

        when(mockQuestionnaireInfoMapper.selectQuestionnaireListByProjectId("projectId"))
                .thenReturn(Collections.emptyList());

        final List<QuestionnaireInfo> result = questionnaireServiceUnderTest.getQuestionnaireList("projectId");

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteQuestionnaireById() {

        when(mockQuestionnaireInfoMapper.deleteByPrimaryKey("id")).thenReturn(0);

        final int result = questionnaireServiceUnderTest.deleteQuestionnaireById("id");

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testGetQuestionnaireById() {

        final QuestionnaireInfo expectedResult = new QuestionnaireInfo();
        expectedResult.setId("id");
        expectedResult.setProjectId("projectId");
        expectedResult.setQuestionnaireName("questionnaireName");
        expectedResult.setDescription("description");
        expectedResult.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final QuestionnaireInfo questionnaireInfo = new QuestionnaireInfo();
        questionnaireInfo.setId("id");
        questionnaireInfo.setProjectId("projectId");
        questionnaireInfo.setQuestionnaireName("questionnaireName");
        questionnaireInfo.setDescription("description");
        questionnaireInfo.setStartTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        when(mockQuestionnaireInfoMapper.selectByPrimaryKey("id")).thenReturn(questionnaireInfo);

        final QuestionnaireInfo result = questionnaireServiceUnderTest.getQuestionnaireById("id");

        assertThat(result).isEqualTo(expectedResult);
    }
}
