package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teacher;
import com.hut.kwk.service.ITeacherService;
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
@RequestMapping("/techer/")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping("add/{name}/{phone}")
    public ServerResponse<String> add(@PathVariable("name")String name,
                                      @PathVariable("phone")String phone){
        return iTeacherService.add(name,phone);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id")Integer id){
        return iTeacherService.del(id);
    }

    @RequestMapping("del/{id}/{name}/{phone}")
    public ServerResponse<String> update(@PathVariable("id")Integer id,
                                         @PathVariable("name")String name,
                                         @PathVariable("phone")String phone){
        return iTeacherService.update(id,name,phone);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Teacher> find(@PathVariable("id")Integer id){
        return iTeacherService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Teacher>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize){
        return iTeacherService.findAll(pageNum,pageSize);
    }
}
