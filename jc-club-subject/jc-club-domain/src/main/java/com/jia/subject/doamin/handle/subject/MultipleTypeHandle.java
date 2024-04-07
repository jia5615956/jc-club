package com.jia.subject.doamin.handle.subject;

import com.google.common.base.Preconditions;
import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.common.enums.SubjectInfoTypeEnum;
import com.jia.subject.doamin.convert.MultipleSubjectConvert;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.infra.basic.entity.SubjectMultiple;
import com.jia.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

//多选
@Component
public class MultipleTypeHandle implements SubjectTypeHandle{

    @Resource
    private SubjectMultipleService subjectMultipleService;


    @Override
    public SubjectInfoTypeEnum getHandleType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }


    //多选的添加功能，与单选类似
    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断是否为空
        Preconditions.checkNotNull(subjectInfoBO.getOptionList(),"选项不能为空");
        //创建选项集合
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        //遍历
        subjectInfoBO.getOptionList().forEach(option->{
            //转换补充字段
            SubjectMultiple subjectMultiple = MultipleSubjectConvert.INSTANCE.subjectInfoBOTOSubjectMultiple(option);
            //补充默认字段
            subjectMultiple.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultipleList.add(subjectMultiple);
        });
        //调用服务添加
        subjectMultipleService.batchInsert(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        return null;
    }
}
