package com.jia.subject.doamin.entity;

import lombok.Data;

@Data
public class SubjectLabelBO {

    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;

    private String categoryId;

    private Integer isDeleted;
}
