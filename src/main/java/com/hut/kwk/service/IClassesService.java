package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classes;

public interface IClassesService {
    ServerResponse<String> add(String className, String classNum,Integer limit);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Integer id, String className, String classNum ,Integer limit);

    ServerResponse<Classes> find(Integer id);

    ServerResponse<PageInfo<Classes>> findAll(Integer pageNum, Integer pageSize);
}
