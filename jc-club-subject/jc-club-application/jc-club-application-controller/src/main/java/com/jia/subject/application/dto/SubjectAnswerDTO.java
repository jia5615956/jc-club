package com.jia.subject.application.dto;


import lombok.Data;

@Data
public class SubjectAnswerDTO {

    /**
     * 答案选项标识
     */
    private Long optionType;

    //题目名称
    private String optionContent;

    //是否正确
    private Integer isCorrect;



}
