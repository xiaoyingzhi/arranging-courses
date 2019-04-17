package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teaching;
import com.hut.kwk.service.ITeachingService;
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
@RequestMapping("/techering/")
public class TeachingController {
    @Autowired
    private ITeachingService iTeachingService;

    @RequestMapping("add/{classId}/{teacherId}/{semId}/{className}/{teacherName}")
    public ServerResponse<String> add(@PathVariable("classId") Integer classId,
                                      @PathVariable("techerId") Integer teacherId,
                                      @PathVariable("semId") Integer semId,
                                      @PathVariable("className") String className,
                                      @PathVariable("techerName") String teacherName) {
        return iTeachingService.add(classId, teacherId, semId, className, teacherName);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id") Integer id) {
        return iTeachingService.del(id);
    }

    @RequestMapping("update/{id}/{classId}/{teacherId}/{semId}/{className}/{teacherName}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                         @PathVariable("classId") Integer classId,
                                         @PathVariable("techerId") Integer teacherId,
                                         @PathVariable("semId") Integer semId,
                                         @PathVariable("className") String className,
                                         @PathVariable("techerName") String teacherName) {
        return iTeachingService.update(id, classId, teacherId, semId, className, teacherName);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Teaching> find(@PathVariable("id") Integer id) {
        return iTeachingService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Teaching>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                      @PathVariable("pageSize") Integer pageSize) {
        return iTeachingService.findAll(pageNum, pageSize);
    }
}
