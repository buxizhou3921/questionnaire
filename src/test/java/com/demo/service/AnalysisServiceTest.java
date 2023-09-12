package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.demo.beans.AnalysisOption;
import com.demo.beans.AnalysisQuestion;
import com.demo.beans.ChooseTerm;
import com.demo.beans.ProblemEntity;
import com.demo.dao.AnalysisMapper;
import com.demo.dao.OptionMapper;
import com.demo.dao.ProblemMapper;
import com.demo.dao.entity.Option;
import com.demo.dao.entity.Problem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalysisServiceTest {

    @Mock
    private AnalysisMapper mockAnalysisMapper;
    @Mock
    private ProblemMapper mockProblemMapper;
    @Mock
    private OptionMapper mockOptionMapper;

    @InjectMocks
    private AnalysisService analysisServiceUnderTest;

    @Test
    void testListQuestionsWithOptions() {

        final ProblemEntity problemEntity = new ProblemEntity();
        problemEntity.setId("id");
        problemEntity.setProblemName("problemName");
        problemEntity.setType(0);
        final ChooseTerm chooseTerm = new ChooseTerm();
        chooseTerm.setId("id");
        chooseTerm.setChooseTerm("chooseTerm");
        problemEntity.setOption(Arrays.asList(chooseTerm));
        final List<ProblemEntity> expectedResult = Arrays.asList(problemEntity);

        final Problem problem = new Problem();
        problem.setId("id");
        problem.setQuestionnaireId("questionnaireId");
        problem.setProblemName("problemName");
        problem.setMustAnswer(false);
        problem.setProblemType(0);
        final List<Problem> problems = Arrays.asList(problem);
        when(mockProblemMapper.selectChoiceById("questionnaireId")).thenReturn(problems);

        final List<Option> options = Arrays.asList(new Option("id", "problemId", "chooseTerm", 0));
        when(mockOptionMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(options);

        final List<ProblemEntity> result = analysisServiceUnderTest.listQuestionsWithOptions("questionnaireId");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testListQuestionsWithOptions_ProblemMapperReturnsNoItems() {

        when(mockProblemMapper.selectChoiceById("questionnaireId")).thenReturn(Collections.emptyList());

        final List<ProblemEntity> result = analysisServiceUnderTest.listQuestionsWithOptions("questionnaireId");

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testListQuestionsWithOptions_OptionMapperReturnsNoItems() {

        final Problem problem = new Problem();
        problem.setId("id");
        problem.setQuestionnaireId("questionnaireId");
        problem.setProblemName("problemName");
        problem.setMustAnswer(false);
        problem.setProblemType(0);
        final List<Problem> problems = Arrays.asList(problem);
        when(mockProblemMapper.selectChoiceById("questionnaireId")).thenReturn(problems);

        when(mockOptionMapper.selectList(any(LambdaQueryWrapper.class))).thenReturn(Collections.emptyList());

        final List<ProblemEntity> result = analysisServiceUnderTest.listQuestionsWithOptions("questionnaireId");

    }

//    @Test
//    void testAnalysisByQuestionId() {
//
//        final AnalysisQuestion expectedResult = new AnalysisQuestion(0,
//                Arrays.asList(new AnalysisOption("id", 0, "percentage")));
//
//        final AnalysisQuestion result = analysisServiceUnderTest.analysisByQuestionId("id");
//
//        assertThat(result).isEqualTo(expectedResult);
//    }
//
//    @Test
//    void testAnalysisByQuestionName() {
//
//        final AnalysisQuestion expectedResult = new AnalysisQuestion(0,
//                Arrays.asList(new AnalysisOption("id", 0, "percentage")));
//
//        final AnalysisQuestion result = analysisServiceUnderTest.analysisByQuestionName("name", "projectId");
//
//        assertThat(result).isEqualTo(expectedResult);
//    }
}
