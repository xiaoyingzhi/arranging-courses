package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classroom;
import com.hut.kwk.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("add/{roomName}/{roomSpace}/{roomLayer}")
    public ServerResponse<String> add(@PathVariable("roomName")String roomName,
                                      @PathVariable("roomSpace")Integer roomSpace,
                                      @PathVariable("roomLayer")Integer roomLayer){
        return iClassroomService.add(roomName,roomSpace,roomLayer);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id")Integer id){
        return iClassroomService.del(id);
    }

    @RequestMapping("update/{id}/{roomName}/{roomSpace}/{roomLayer}")
    public ServerResponse<String> update(@PathVariable("id")Integer id,
                                         @PathVariable("roomName")String roomName,
                                         @PathVariable("roomSpace")Integer roomSpace,
                                         @PathVariable("roomLayer")Integer roomLaye){
        return iClassroomService.update(id,roomName,roomSpace,roomLaye);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Classroom> find(@PathVariable("id")Integer id){
        return iClassroomService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Classroom>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                       @PathVariable("pageSize") Integer pageSize){
        return iClassroomService.findAll(pageNum,pageSize);
    }
}
