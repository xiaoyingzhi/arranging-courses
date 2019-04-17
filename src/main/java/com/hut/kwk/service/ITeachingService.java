package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teaching;

public interface ITeachingService {
    ServerResponse<String> add(Integer classId, Integer teacherId, Integer semId, String className, String techerName);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Integer id, Integer classId, Integer teacherId, Integer semId, String className, String techerName);

    ServerResponse<Teaching> find(Integer id);

    ServerResponse<PageInfo<Teaching>> findAll(Integer pageNum, Integer pageSize);
}
