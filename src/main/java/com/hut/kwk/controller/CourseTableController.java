package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.CourseTable;
import com.hut.kwk.service.ICourseTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by kwk on 2019-04-24
 *
 * @author kwk
 */
@RestController
@RequestMapping("/coursetable/")
public class CourseTableController {

    @Autowired
    private ICourseTableService iCourseTableService;

    @RequestMapping("findBy")
    public ServerResponse<List<CourseTable>> find(String className, String teacherName) {
        if ((className==null||"".equals(className)&&(teacherName==null||"".equals(teacherName)))){
            return ServerResponse.createByErrorMessage("请输入参数");

        }
        return iCourseTableService.findBy(className,teacherName);
    }

    @RequestMapping("add")
    public ServerResponse<String> add(CourseTable courseTable) {

        return iCourseTableService.add(courseTable);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iCourseTableService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(CourseTable courseTable) {
        return iCourseTableService.update(courseTable);
    }

    @RequestMapping("find")
    public ServerResponse<CourseTable> find(Integer id) {
        return iCourseTableService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<CourseTable>> findAll(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        System.out.println(request.getRequestURI());
        System.out.println(pageNum+"  "+pageSize);
        return iCourseTableService.findAll(pageNum, pageSize);
    }
}
