package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teacher;
import com.hut.kwk.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("add")
    public ServerResponse<String> add(String name, String phone) {
        return iTeacherService.add(name, phone);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iTeacherService.del(id);
    }

    @RequestMapping("del")
    public ServerResponse<String> update(Integer id, String name, String phone) {
        return iTeacherService.update(id, name, phone);
    }

    @RequestMapping("find")
    public ServerResponse<Teacher> find(Integer id) {
        return iTeacherService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum, Integer pageSize) {
        return iTeacherService.findAll(pageNum, pageSize);
    }
}
