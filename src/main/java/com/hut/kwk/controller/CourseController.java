package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Course;
import com.hut.kwk.service.ICourseService;
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
@RequestMapping("/course/")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;

    @RequestMapping("add/{courseName}/{courseTime}")
    public ServerResponse<String> add(@PathVariable("courseName")String courseName,
                                      @PathVariable("courseTime")Integer courseTime){
        return iCourseService.add(courseName,courseTime);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id")Integer id){
        return iCourseService.del(id);
    }

    @RequestMapping("update/{id}/{courseName}/{courseTime}")
    public ServerResponse<String> update(@PathVariable("id")Integer id,
                                         @PathVariable("courseName")String courseName,
                                         @PathVariable("courseTime")Integer courseTime){
        return iCourseService.update(id,courseName,courseTime);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Course> find(@PathVariable("id")Integer id){
        return iCourseService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Course>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                    @PathVariable("pageSize") Integer pageSize){
        return iCourseService.findAll(pageNum,pageSize);
    }
}
