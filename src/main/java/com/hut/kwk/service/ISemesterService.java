package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Semester;

public interface ISemesterService {
    ServerResponse<String> add(String year, String semester, Integer weekCount);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Integer id, String year, String semester, Integer weekCount);

    ServerResponse<PageInfo<Semester>> findAll(Integer pageNum, Integer pageSize);

    ServerResponse<Semester> find(Integer id);
}
