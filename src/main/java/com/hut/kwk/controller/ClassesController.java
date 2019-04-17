package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classes;
import com.hut.kwk.service.IClassesService;
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
@RequestMapping("classes")
public class ClassesController {
    @Autowired
    private IClassesService iClassesService;

    @RequestMapping("add/{className}/{classNum}")
    public ServerResponse<String> add(@PathVariable("className")String className,
                                      @PathVariable("classNum")String classNum){
        return iClassesService.add(className,classNum);
    }

    @RequestMapping("del/{id}/")
    public ServerResponse<String> del(@PathVariable("id")Integer id){
        return iClassesService.del(id);
    }

    @RequestMapping("update/{id}/{className}/{classNum}")
    public ServerResponse<String> update(@PathVariable("id")Integer id,
                                         @PathVariable("className")String className,
                                         @PathVariable("classNum")String classNum){
        return iClassesService.update(id,className,classNum);
    }

    @RequestMapping("find/{id}/")
    public ServerResponse<Classes> find(@PathVariable("id")Integer id){
        return iClassesService.find(id);
    }

    @RequestMapping("findAll/{pageNum}/{pageSize}")
    public ServerResponse<PageInfo<Classes>> findAll(@PathVariable(value = "pageNum") Integer pageNum,
                                                     @PathVariable("pageSize") Integer pageSize){
        return iClassesService.findAll(pageNum,pageSize);
    }
}
