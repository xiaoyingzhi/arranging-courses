package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Semester;
import com.hut.kwk.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@RestController
@RequestMapping("/semester/")
public class SemesterController {
    @Autowired
    private ISemesterService iSemesterService;

    @RequestMapping("add")
    public ServerResponse<String> add(String year,
                                      String semester,
                                      Integer weekCount) {
        return iSemesterService.add(year, semester, weekCount);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iSemesterService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String year, String semester, Integer weekCount) {
        return iSemesterService.update(id, year, semester, weekCount);
    }

    @RequestMapping("find")
    public ServerResponse<Semester> find(Integer id) {
        return iSemesterService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Semester>> findAll(Integer pageNum, Integer pageSize) {
        return iSemesterService.findAll(pageNum, pageSize);
    }
}
