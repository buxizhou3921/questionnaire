package com.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.AnalysisMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Analysis{

    /**
     * 记录id
     */
    String id;

    /**
     * 答卷人
     */
    private String respondent;

    /**
     * 选项id
     */
    String optionId;

    /**
     * 问题id
     */
    String problemId;

    /**
     * 问题名称
     */
    String problemContent;

    /**
     * 问卷id
     */
    String questionnaireId;

    /**
     * 项目id
     */
    String projectId;

    /**
     * 回答日期
     */
    Date answerTime;

    /**
     * 逻辑删除字段
     */
    private boolean is_del;
}
