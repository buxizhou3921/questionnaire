package com.demo.service;

import com.demo.dao.RecordMapper;
import com.demo.dao.entity.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecordServiceTest {

    private RecordService recordServiceUnderTest;

    @BeforeEach
    void setUp() {
        recordServiceUnderTest = new RecordService();
        recordServiceUnderTest.recordMapper = mock(RecordMapper.class);
    }

    @Test
    void testAddRecord() {

        final Record record = new Record();
        record.setId("id");
        record.setProjectId("projectId");
        record.setQuestionnaireName("questionnaireName");
        record.setRespondent("respondent");
        record.setContent("content");

        final Record record1 = new Record();
        record1.setId("id");
        record1.setProjectId("projectId");
        record1.setQuestionnaireName("questionnaireName");
        record1.setRespondent("respondent");
        record1.setContent("content");
        when(recordServiceUnderTest.recordMapper.insertSelective(record1)).thenReturn(0);

        final int result = recordServiceUnderTest.addRecord(record);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void testGetRecordListByProjectId() {

        final Record record = new Record();
        record.setId("id");
        record.setProjectId("projectId");
        record.setQuestionnaireName("questionnaireName");
        record.setRespondent("respondent");
        record.setContent("content");
        final List<Record> expectedResult = Arrays.asList(record);

        final Record record1 = new Record();
        record1.setId("id");
        record1.setProjectId("projectId");
        record1.setQuestionnaireName("questionnaireName");
        record1.setRespondent("respondent");
        record1.setContent("content");
        final List<Record> records = Arrays.asList(record1);
        when(recordServiceUnderTest.recordMapper.getRecordListByProjectId("projectId")).thenReturn(records);

        final List<Record> result = recordServiceUnderTest.getRecordListByProjectId("projectId");

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetRecordListByProjectId_RecordMapperReturnsNoItems() {

        when(recordServiceUnderTest.recordMapper.getRecordListByProjectId("projectId"))
                .thenReturn(Collections.emptyList());

        final List<Record> result = recordServiceUnderTest.getRecordListByProjectId("projectId");

        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetRecordById() {

        final Record expectedResult = new Record();
        expectedResult.setId("id");
        expectedResult.setProjectId("projectId");
        expectedResult.setQuestionnaireName("questionnaireName");
        expectedResult.setRespondent("respondent");
        expectedResult.setContent("content");

        final Record record = new Record();
        record.setId("id");
        record.setProjectId("projectId");
        record.setQuestionnaireName("questionnaireName");
        record.setRespondent("respondent");
        record.setContent("content");
        when(recordServiceUnderTest.recordMapper.selectByPrimaryKey("recordId")).thenReturn(record);

        final Record result = recordServiceUnderTest.getRecordById("recordId");

        assertThat(result).isEqualTo(expectedResult);
    }
}
