package com.jia.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jia.subject.application.convert.SubjectAnswerDTOConvert;
import com.jia.subject.application.convert.SubjectInfoDTOConvert;
import com.jia.subject.application.dto.SubjectInfoDTO;
import com.jia.subject.common.entity.PageResult;
import com.jia.subject.common.entity.Result;
import com.jia.subject.doamin.entity.SubjectAnswerBO;
import com.jia.subject.doamin.entity.SubjectInfoBO;
import com.jia.subject.doamin.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {


    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.add.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            //判断
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()),"分类Id不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()),"标签Id不能为空");
            //转换
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.subjectInfoDTOTOSubjectInfoBO(subjectInfoDTO);
            //答案转换
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConvert.INSTANCE.subjectAnswerDTOTOSubjectAnswerBO(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);
            //调用服务
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        }catch (Exception e){
            log.info("SubjectController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }

    //查询
    @PostMapping("/getSubjectPage")
    public Result<SubjectInfoDTO> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO){
        try {
            if(log.isInfoEnabled()){
                log.info("SubjectController.getSubjectPage.dto:{}", JSON.toJSONString(subjectInfoDTO));
            }
            //判断非空字段
            Preconditions.checkNotNull(subjectInfoDTO.getCategoryId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getLabelId(), "标签id不能为空");
            //转换
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConvert.INSTANCE.subjectInfoDTOTOSubjectInfoBO(subjectInfoDTO);
            PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
            return Result.ok(boPageResult);
        }catch (Exception e){
            log.info("SubjectController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }
    }
}
