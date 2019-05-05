package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classroom;
import com.hut.kwk.model.entity.ClassroomFree;
import com.hut.kwk.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@RestController
@RequestMapping("/classroom/")
public class ClassroomController {

    @Autowired
    private IClassroomService iClassroomService;

    @RequestMapping("add")
    public ServerResponse<String> add(String roomName, Integer roomSpace, Integer roomLayer) {
        return iClassroomService.add(roomName, roomSpace, roomLayer);
    }

    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iClassroomService.del(id);
    }

    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String roomName, Integer roomSpace, Integer roomLayer) {
        return iClassroomService.update(id, roomName, roomSpace, roomLayer);
    }

    @RequestMapping("find")
    public ServerResponse<Classroom> find(Integer id) {
        return iClassroomService.find(id);
    }

    @RequestMapping("findAll")
    public ServerResponse<PageInfo<Classroom>> findAll(Integer pageNum, Integer pageSize) {
        return iClassroomService.findAll(pageNum, pageSize);
    }

    @RequestMapping("findByWeek")
    public ServerResponse<PageInfo<ClassroomFree>> findByWeek(Integer pageNum, Integer pageSize, Integer week,String roomName,String day) {
        List<ClassroomFree> byWeek = iClassroomService.findByWeek(week,roomName);
        List<ClassroomFree> collect;
        if (day!=null&&!"".equals(day)){
             collect= byWeek.stream().filter(s->s.getDay().equals(day)).skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        }else {
            collect= byWeek.stream().skip((pageNum - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        }
        PageInfo<ClassroomFree> pageInfo = new PageInfo<>(collect);
        pageInfo.setTotal(byWeek.size());
        return ServerResponse.createBySuccess(pageInfo);
    }

}
