package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teaching;
import com.hut.kwk.service.ITeachingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("add")
    public ServerResponse<String> add(Integer classId, Integer teacherId, Integer semId,
                                      String className, String teacherName) {
        return iTeachingService.add(classId, teacherId, semId, className, teacherName);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iTeachingService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, Integer classId, Integer teacherId,
                                         Integer semId, String className, String teacherName) {
        return iTeachingService.update(id, classId, teacherId, semId, className, teacherName);
    }

    @RequestMapping("find")
    public ServerResponse<Teaching> find(Integer id) {
        return iTeachingService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Teaching>> findAll(Integer pageNum, Integer pageSize) {
        return iTeachingService.findAll(pageNum, pageSize);
    }
}
