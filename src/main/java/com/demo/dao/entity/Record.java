package com.demo.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: Record
 * Package: com.demo.dao.entity
 * Description: 回答记录实体
 *
 * @Author: 王方舟
 * @Create: 2023-06-26 - 2:20
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    private String id; // 记录ID
    private String projectId; // 所属项目ID
    private String questionnaireName; // 问卷名
    private String respondent; // 答题人
    private String content; // 答题内容
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date answerTime; // 回答时间
}
