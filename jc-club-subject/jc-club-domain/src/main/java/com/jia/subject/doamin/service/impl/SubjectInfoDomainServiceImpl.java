package com.jia.subject.doamin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jia.subject.common.enums.IsDeletedFlagEnum;
import com.jia.subject.doamin.convert.SubjectInfoConvert;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.entity.SubjectOptionBO;
import com.jia.subject.doamin.handle.subject.SubjectTypeHandle;
import com.jia.subject.doamin.handle.subject.SubjectTypeHandleFactory;
import com.jia.subject.doamin.service.SubjectInfoDomainService;
import com.jia.subject.infra.basic.entity.SubjectInfo;
import com.jia.subject.infra.basic.entity.SubjectMapping;
import com.jia.subject.infra.basic.service.SubjectInfoService;
import com.jia.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandleFactory subjectTypeHandleFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //转换
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToSubjectInfo(subjectInfoBO);
        //插入不删除
        subjectInfo.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        //调用服务插入题目信息
        subjectInfoService.insert(subjectInfo);
        //利用工厂+策略模式添加答案对应的信息
        SubjectTypeHandle handle = subjectTypeHandleFactory.getHandle(subjectInfo.getSubjectType());
        //subjectInfo插入主键是自动生成的，后面的答案内容需要这个
        subjectInfoBO.setId(subjectInfo.getId());
        System.out.println(subjectInfoBO.getId());
        handle.add(subjectInfoBO);
        //将映射id插入
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> subjectMappingList = new LinkedList<>();
        categoryIds.forEach(categoryId->{
            labelIds.forEach(labelId->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setLabelId(labelId);
                subjectMappingList.add(subjectMapping);
            });
        });
        //调用服务批量插入
        subjectMappingService.batchInsert(subjectMappingList);
    }

    //查询题目详情
    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        //转换
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToSubjectInfo(subjectInfoBO);
        //调用工厂服务
        SubjectTypeHandle handle = subjectTypeHandleFactory.getHandle(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = handle.query(subjectInfo.getId());
        //转换
        SubjectInfoBO info = SubjectInfoConvert.INSTANCE.subjectOptionBOTOSubjectInfo(subjectOptionBO,subjectInfo);
        List<String> lebelNameList = new LinkedList<>();
        return info;
    }
}