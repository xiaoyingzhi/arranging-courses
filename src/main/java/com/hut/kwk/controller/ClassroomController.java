package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classroom;
import com.hut.kwk.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@RestController
@RequestMapping("/classroom/")
public class ClassroomController {

    @Autowired
    private IClassroomService iClassroomService;

    @RequestMapping("add")
    public ServerResponse<String> add(String roomName, Integer roomSpace, Integer roomLayer) {
        return iClassroomService.add(roomName, roomSpace, roomLayer);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iClassroomService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String roomName, Integer roomSpace, Integer roomLayer) {
        return iClassroomService.update(id, roomName, roomSpace, roomLayer);
    }

    @RequestMapping("find")
    public ServerResponse<Classroom> find(Integer id) {
        return iClassroomService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Classroom>> findAll(Integer pageNum, Integer pageSize) {
        return iClassroomService.findAll(pageNum, pageSize);
    }
}
