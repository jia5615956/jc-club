package com.jia.subject.application.dto;


import lombok.Data;

@Data
public class SubjectAnswerDTO {

    //主键
    private Integer id;

    //题目名称
    private String optionContent;

    //是否正确
    private Integer isCorrect;



}
