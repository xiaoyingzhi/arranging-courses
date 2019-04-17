package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teacher;

public interface ITeacherService {
    ServerResponse<String> add(String name, String phone);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Integer id, String name, String phone);

    ServerResponse<Teacher> find(Integer id);

    ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum, Integer pageSize);
}
