package com.jia.subject.doamin.entity;


import com.jia.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectInfoBO extends PageInfo implements Serializable {

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

    //标签名字
    private List<String> labelName;
    //答案选项
    private List<SubjectAnswerBO> optionList;

    private Long categoryId;

    private Long labelId;

    //标签名字
    List<String> lebelNameList;

}
