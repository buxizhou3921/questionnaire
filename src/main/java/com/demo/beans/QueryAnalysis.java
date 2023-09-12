package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAnalysis {


    /**
     * 项目Id
     */
    private String projectId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 问卷Id
     */
    private String questionnaireId;

    /**
     * 问题id
     */
    private String questionId;

    /**
     * 问题姓名
     */
    private String questionName;

}
