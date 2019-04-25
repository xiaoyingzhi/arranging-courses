package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.CourseTable;

/**
 * Create by Wang Heng on 2019-04-24
 *
 * @author Wang Heng
 */
public interface ICourseTableService {
    ServerResponse<String> add(CourseTable courseTable);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(CourseTable courseTable);

    ServerResponse<CourseTable> find(Integer id);

    ServerResponse<PageInfo<CourseTable>> findAll(Integer pageNum, Integer pageSize);

    ServerResponse<String> arr();
}
