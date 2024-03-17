package com.jia.subject.application.controller;


import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jia.subject.application.convert.SubjectLabelDTOConvert;
import com.jia.subject.application.dto.SubjectLabelDTO;
import com.jia.subject.common.entity.Result;
import com.jia.subject.doamin.convert.SubjectLabelConvert;
import com.jia.subject.doamin.entity.SubjectCategoryBO;
import com.jia.subject.doamin.entity.SubjectLabelBO;
import com.jia.subject.doamin.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //判断
            Preconditions.checkNotNull(subjectLabelDTO.getLabelName(),"标签名字不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类Id不能为空");
            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(),"排序不能为空");
            //转换
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.subjectLabelDTOTOSubjectLabelBO(subjectLabelDTO);
            //调用服务
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.info("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    //更新
    @PostMapping("/updateLabel")
    public Result<Boolean> updateLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.updateLabel.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //判断
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"Id不能为空");
            //转换
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.subjectLabelDTOTOSubjectLabelBO(subjectLabelDTO);
            //调用服务
            Boolean flag = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(flag);
        }catch (Exception e){
            log.info("SubjectCategoryController.updateLabel.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/deleteLabel")
    //删除
    public Result<Boolean> deleteLabel(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.deleteLabel.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //判断
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"Id不能为空");
            //转换
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.subjectLabelDTOTOSubjectLabelBO(subjectLabelDTO);
            //调用服务
            Boolean update = subjectLabelDomainService.deleteLabel(subjectLabelBO);
            return Result.ok(update);
        }catch (Exception e){
            log.info("SubjectCategoryController.deleteLabel.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //转换
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConvert.INSTANCE.subjectLabelDTOTOSubjectLabelBO(subjectLabelDTO);
            //调用服务
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            //转换
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConvert.INSTANCE.SubjectLabelBOListTOSubjectLabelDTO(subjectLabelBOList);
            return Result.ok(subjectLabelDTOS);
        }catch (Exception e){
            log.info("SubjectCategoryController.queryLabelByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }
}
