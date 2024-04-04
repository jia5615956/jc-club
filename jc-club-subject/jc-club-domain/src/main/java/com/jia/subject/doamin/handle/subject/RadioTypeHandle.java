package com.jia.subject.doamin.handle.subject;

import com.google.common.base.Preconditions;
import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.convert.RadioSubjectConvert;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.infra.basic.entity.SubjectInfo;
import com.jia.subject.infra.basic.entity.SubjectRadio;
import com.jia.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


//单选的题目策略类
@Component
public class RadioTypeHandle implements SubjectTypeHandle{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandleType()   {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //首先判断是否为空
        Preconditions.checkNotNull(subjectInfoBO.getOptionList(),"答案不能为空");
        //单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        //遍历
        subjectInfoBO.getOptionList().forEach(option->{
           //转换
            SubjectRadio subjectRadio = RadioSubjectConvert.INSTANCE.convertBoToEntity(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            //放入集合中
            subjectRadioList.add(subjectRadio);
        });
        //批量插入
        subjectRadioService.batchInsert(subjectRadioList);
    }
}
