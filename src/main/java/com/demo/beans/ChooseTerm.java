package com.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选项接受Bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChooseTerm {

    /**
     * 选项id
     */
    private String id;

    /**
     * 选项内容
     */
    private String chooseTerm;
}
