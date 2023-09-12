package com.demo.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: QuestionnaireInfo
 * Package: com.demo.dao.entity
 * Description: 问卷实体
 *
 * @Author: 王方舟
 * @Create: 2023-06-19 - 16:17
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireInfo {

    private String id; // 问卷ID
    private String projectId; // 项目ID
    private String questionnaireName; // 问卷名称
    private String description; // 问卷说明
    private String type; // 问卷类型
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime; // 开始时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date stopTime; // 结束时间
}
