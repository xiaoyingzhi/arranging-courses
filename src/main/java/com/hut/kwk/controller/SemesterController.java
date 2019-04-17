package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Semester;
import com.hut.kwk.service.ISemesterService;
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
@RequestMapping("/semester/")
public class SemesterController {
    @Autowired
    private ISemesterService iSemesterService;

    @RequestMapping("add/{year}/{semester}/{weekCount}")
    public ServerResponse<String> add(@PathVariable("year")String year,
                                      @PathVariable("semester")String semester,
                                      @PathVariable("weekCount")Integer weekCount){
        return iSemesterService.add(year,semester,weekCount);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id")Integer id){
        return iSemesterService.del(id);
    }

    @RequestMapping("update/{id}/{year}/{semester}/{weekCount}")
    public ServerResponse<String> update(@PathVariable("id")Integer id,
                                         @PathVariable("year")String year,
                                         @PathVariable("semester")String semester,
                                         @PathVariable("weekCount")Integer weekCount){
        return iSemesterService.update(id,year,semester,weekCount);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Semester> find(@PathVariable("id")Integer id){
        return iSemesterService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Semester>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize){
        return iSemesterService.findAll(pageNum,pageSize);
    }
}
