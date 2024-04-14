package com.jia.subject.infra.basic.service;

import com.jia.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2024-03-20 13:31:24
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);


    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    //批量新增数据
    void batchInsert(List<SubjectMultiple> subjectMultipleList);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    List<SubjectMultiple> queryByCondition(SubjectMultiple subjectMultiple);
}
