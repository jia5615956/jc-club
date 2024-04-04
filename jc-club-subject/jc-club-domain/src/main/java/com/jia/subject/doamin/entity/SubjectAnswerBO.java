package com.jia.subject.doamin.entity;


import lombok.Data;

@Data
public class SubjectAnswerBO {

    /**
     * 答案选项标识
     */
    private Long optionType;

    //题目名称
    private String optionContent;

    //是否正确
    private Integer isCorrect;



}
