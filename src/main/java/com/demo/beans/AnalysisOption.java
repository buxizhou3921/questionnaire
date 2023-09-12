package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisOption {
    /**
     * 选项id
     */
    private String id;

    /**
     * 总的选项记录数
     */
    private Integer total;

    /**
     * 所占百分比
     */
    private String percentage;
}
