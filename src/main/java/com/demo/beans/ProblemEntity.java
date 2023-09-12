package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemEntity {

    /**
     * 问题id
     *
     */
    private String id;

    /**
     * 问题名称
     */
    private String problemName;

    /**
     * 题目类型
     */
    private Integer type;

    /**
     * 选项集合
     */
    private List<ChooseTerm> option;

}
