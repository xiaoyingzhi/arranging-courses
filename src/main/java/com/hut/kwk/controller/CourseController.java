package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Course;
import com.hut.kwk.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@RestController
@RequestMapping("/course/")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;

    @RequestMapping("add")
    public ServerResponse<String> add(String courseName, Integer courseTime) {
        return iCourseService.add(courseName, courseTime);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iCourseService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String courseName, Integer courseTime) {
        return iCourseService.update(id, courseName, courseTime);
    }

    @RequestMapping("find")
    public ServerResponse<Course> find(Integer id) {
        return iCourseService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Course>> findAll(Integer pageNum, Integer pageSize) {
        return iCourseService.findAll(pageNum, pageSize);
    }
}
