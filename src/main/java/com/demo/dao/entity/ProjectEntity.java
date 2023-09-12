package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * ClassName: ProjectEntitiy
 * Package: com.demo.dao.entity
 * Description: 项目实体
 *
 * @Author: wfz
 * @Create: 2023-06-08 - 17:48
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    private String id; // 项目ID
    private String userId; // 用户id
    private String projectName; // 项目名称
    private String projectContent; // 项目说明
    private String createdBy; // 创建人
    private Date creationDate; // 创建时间
    private String lastUpdatedBy; // 修改人
    private Date lastUpdateDate; // 修改时间
}
