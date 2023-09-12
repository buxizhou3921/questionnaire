package com.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Problem
 * Package: com.demo.dao.entity
 * Description: 问题实体
 *
 * @Author: 王方舟
 * @Create: 2023-06-19 - 16:19
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName("problem")
public class Problem {

    private String id; // 问题ID
    private String questionnaireId; // 所属问卷ID
    private String problemName; // 问题名
    private boolean mustAnswer; // 是否必答
    private Integer problemType; // 问题类型
    private String leftTitle; // 左标题
    List<Option> option; // 选项列

    public Problem(String id, String questionnaireId, String problemName, boolean mustAnswer, Integer problemType, String leftTitle) {
        this.id = id;
        this.questionnaireId = questionnaireId;
        this.problemName = problemName;
        this.mustAnswer = mustAnswer;
        this.problemType = problemType;
        this.leftTitle = leftTitle;
    }
}
