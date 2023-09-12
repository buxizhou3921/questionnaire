package com.demo.service;

import com.demo.dao.OptionMapper;
import com.demo.dao.ProblemMapper;
import com.demo.dao.entity.Option;
import com.demo.dao.entity.Problem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProblemServiceTest {

    private ProblemService problemServiceUnderTest;

    @BeforeEach
    void setUp() {
        problemServiceUnderTest = new ProblemService();
        problemServiceUnderTest.problemMapper = mock(ProblemMapper.class);
        problemServiceUnderTest.optionMapper = mock(OptionMapper.class);
    }

    @Test
    void testAddBatchsProblems() {

        final Problem problem = new Problem();
        problem.setId("id");
        problem.setQuestionnaireId("questionnaireId");
        final Option option = new Option();
        option.setId("id");
        option.setProblemId("problemId");
        problem.setOption(Arrays.asList(option));
        final List<Problem> problems = Arrays.asList(problem);

        final Problem record = new Problem();
        record.setId("id");
        record.setQuestionnaireId("questionnaireId");
        final Option option1 = new Option();
        option1.setId("id");
        option1.setProblemId("problemId");
        record.setOption(Arrays.asList(option1));
        when(problemServiceUnderTest.problemMapper.insert(record)).thenReturn(0);

        final int result = problemServiceUnderTest.addBatchsProblems(problems);

    }

    @Test
    void testGetProblemById() {

        final Problem expectedResult = new Problem();
        expectedResult.setId("id");
        expectedResult.setQuestionnaireId("questionnaireId");
        final Option option = new Option();
        option.setId("id");
        option.setProblemId("problemId");
        expectedResult.setOption(Arrays.asList(option));

        final Problem problem = new Problem();
        problem.setId("id");
        problem.setQuestionnaireId("questionnaireId");
        final Option option1 = new Option();
        option1.setId("id");
        option1.setProblemId("problemId");
        problem.setOption(Arrays.asList(option1));
        when(problemServiceUnderTest.problemMapper.selectByPrimaryKey("id")).thenReturn(problem);

        final Problem result = problemServiceUnderTest.getProblemById("id");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProblemProjectById() {

        final Problem problem = new Problem();
        problem.setId("id");
        problem.setQuestionnaireId("questionnaireId");
        final Option option = new Option();
        option.setId("id");
        option.setProblemId("problemId");
        problem.setOption(Arrays.asList(option));
        final List<Problem> expectedResult = Arrays.asList(problem);

        final Problem problem1 = new Problem();
        problem1.setId("id");
        problem1.setQuestionnaireId("questionnaireId");
        final Option option1 = new Option();
        option1.setId("id");
        option1.setProblemId("problemId");
        problem1.setOption(Arrays.asList(option1));
        final List<Problem> problems = Arrays.asList(problem1);
        when(problemServiceUnderTest.problemMapper.selectByProjectById("questionnaire_id")).thenReturn(problems);

        final List<Problem> result = problemServiceUnderTest.getProblemProjectById("questionnaire_id");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProblemProjectById_ProblemMapperReturnsNoItems() {

        when(problemServiceUnderTest.problemMapper.selectByProjectById("questionnaire_id"))
                .thenReturn(Collections.emptyList());

        final List<Problem> result = problemServiceUnderTest.getProblemProjectById("questionnaire_id");

        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
