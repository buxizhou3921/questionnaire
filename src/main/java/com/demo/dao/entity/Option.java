package com.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: OptionEntity
 * Package: com.demo.dao.entity
 * Description: 选项实体
 *
 * @Author: 王方舟
 * @Create: 2023-06-19 - 16:25
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("choose_term")
public class Option {
    private String id; // 选项ID
    private String problemId; // 所属问题ID
    private String chooseTerm; // 选项文字
    private Integer fraction; // 分数
}
