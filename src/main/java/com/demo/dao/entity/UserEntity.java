package com.demo.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: UserEntity
 * Package: com.demo.dao.entity
 * Description: 用户实体
 *
 * @Author: 王方舟
 * @Create: 2023-06-08 - 17:48
 * @Version: v1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {
    private String id; // 用户ID
    private String username; // 用户名
    private String password; // 密码
    private Date startTime; // 开始时间
    private Date stopTime; // 结束时间
    private String status; // 状态（1显示，0隐藏）
    private String createdBy; // 创建人
    private Date creationDate; // 创建时间
    private String lastUpdatedBy; // 修改人
    private Date lastUpdateDate; // 修改时间
}
