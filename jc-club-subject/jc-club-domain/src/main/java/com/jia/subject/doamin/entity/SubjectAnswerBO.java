package com.jia.subject.doamin.entity;


import lombok.Data;

import java.io.Serializable;


//题目答案Bo
@Data
public class SubjectAnswerBO implements Serializable {

    /**
     * 答案选项标识
     */
    private Long optionType;

    //题目名称
    private String optionContent;

    //是否正确
    private Integer isCorrect;



}
