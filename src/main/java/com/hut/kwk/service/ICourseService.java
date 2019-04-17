package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Course;

public interface ICourseService {
    ServerResponse<String> add(String courseName, Integer courseTime);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Integer id, String courseName, Integer courseTime);

    ServerResponse<Course> find(Integer id);

    ServerResponse<PageInfo<Course>> findAll(Integer pageNum, Integer pageSize);
}
