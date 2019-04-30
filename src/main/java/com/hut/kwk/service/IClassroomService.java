package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classroom;
import com.hut.kwk.model.entity.ClassroomFree;

import java.util.List;

public interface IClassroomService {
    ServerResponse<String> add(String roomName, Integer roomSpace, Integer roomLayer);

    ServerResponse<String> del(Integer id);

    ServerResponse<Classroom> find(Integer id);

    ServerResponse<PageInfo<Classroom>> findAll(Integer pageNum, Integer pageSize);

    ServerResponse<String> update(Integer id, String roomName, Integer roomSpace, Integer roomLaye);

    List<ClassroomFree> findByWeek(Integer week,String roomName);
}
