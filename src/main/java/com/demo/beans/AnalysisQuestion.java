package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisQuestion {

    /**
     * 问卷总记录数
     */
    Integer total;

    /**
     * 选项记录数集合
     */
    List<AnalysisOption> analysisOptions;
}
