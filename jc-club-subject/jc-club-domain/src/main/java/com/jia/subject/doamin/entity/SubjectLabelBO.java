package com.jia.subject.doamin.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectLabelBO implements Serializable {

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

    private Long categoryId;

    private Integer isDeleted;
}
