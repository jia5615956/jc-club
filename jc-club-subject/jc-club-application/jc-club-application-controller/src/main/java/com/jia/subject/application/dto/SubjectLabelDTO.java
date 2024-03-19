package com.jia.subject.application.dto;


import lombok.Data;

@Data
public class SubjectLabelDTO {

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
