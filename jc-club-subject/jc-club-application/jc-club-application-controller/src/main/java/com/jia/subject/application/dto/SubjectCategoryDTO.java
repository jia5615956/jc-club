package com.jia.subject.application.dto;

import com.jia.subject.doamin.entity.SubjectLabelBO;
import lombok.Data;

import java.util.List;

@Data
public class SubjectCategoryDTO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类类型
     */
    private Integer categoryType;
    /**
     * 图标连接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;

    //数量
    private Integer count;

    //标签
    private List<SubjectLabelDTO> subjectLabelDTOOList;
}
