package com.jia.subject.doamin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jia.subject.doamin.convert.SubjectInfoConvert;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.service.SubjectInfoDomainService;
import com.jia.subject.infra.basic.entity.SubjectInfo;
import com.jia.subject.infra.basic.service.SubjectInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Override
    public boolean add(SubjectInfoBO subjectInfoBO) {
        if(log.isInfoEnabled()){
            log.info("SubjectInfoDomainServiceImpl.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //转换
        SubjectInfo subjectInfo = SubjectInfoConvert.INSTANCE.convertBoToCategory(subjectInfoBO);
        //调用服务插入题目信息
        subjectInfoService.insert(subjectInfo);
        //利用工厂+策略模式添加答案对应的信息

        return false;
    }
}
