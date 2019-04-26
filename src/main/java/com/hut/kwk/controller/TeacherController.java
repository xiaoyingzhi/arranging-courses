package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teacher;
import com.hut.kwk.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@RestController
@RequestMapping("/teacher/")
public class TeacherController {
    @Autowired
    private ITeacherService iTeacherService;

    @RequestMapping("add")
    public ServerResponse<String> add(String name, String phone,Integer countLimit) {
        return iTeacherService.add(name, phone,countLimit);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iTeacherService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String name, String phone,Integer countLimit) {
        return iTeacherService.update(id, name, phone,countLimit);
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
