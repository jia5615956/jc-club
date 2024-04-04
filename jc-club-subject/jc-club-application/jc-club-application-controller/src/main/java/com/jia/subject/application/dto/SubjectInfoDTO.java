package com.jia.subject.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubjectInfoDTO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    //题目答案
    private String subjectAnswer;

    private List<Long> categoryIds;

    private List<Long> labelIds;


    //答案选项
    private List<SubjectAnswerDTO> optionList;
}
