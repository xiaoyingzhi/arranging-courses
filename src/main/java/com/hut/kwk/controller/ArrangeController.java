package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Arrange;
import com.hut.kwk.service.IArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by kwk on 2019-04-23
 *
 * @author kwk
 */
@RestController
@RequestMapping("/arrange/")
public class ArrangeController {

    @Autowired
    private IArrangeService iArrangeService;

    @RequestMapping("add")
    public ServerResponse<String> add(Arrange arrange) {
        return iArrangeService.add(arrange);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iArrangeService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Arrange arrange) {
        return iArrangeService.update(arrange);
    }

    @RequestMapping("find")
    public ServerResponse<Arrange> find(Integer id) {
        return iArrangeService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Arrange>> findAll(Integer pageNum, Integer pageSize) {
        return iArrangeService.findAll(pageNum, pageSize);
    }

}
