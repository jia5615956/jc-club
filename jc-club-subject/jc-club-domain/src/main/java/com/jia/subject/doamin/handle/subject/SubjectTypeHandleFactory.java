package com.jia.subject.doamin.handle.subject;

import com.jia.subject.common.enums.ResultCodeEnum;
import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//题目类型工厂
@Component
public class SubjectTypeHandleFactory {

    @Resource
    private List<SubjectTypeHandle> subjectTypeHandleList;

    private Map<SubjectInfoTypeEnum,SubjectTypeHandle> handleMap = new HashMap<>();

    public SubjectTypeHandle getHandle(int subjectType){
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handleMap.get(subjectInfoTypeEnum);
    }


}
