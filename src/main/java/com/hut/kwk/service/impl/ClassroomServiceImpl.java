package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classroom;
import com.hut.kwk.model.entity.ClassroomQuery;
import com.hut.kwk.model.mapper.ClassroomMapper;
import com.hut.kwk.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@Service
public class ClassroomServiceImpl implements IClassroomService {

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    public ServerResponse<String> add(String roomName, Integer roomSpace, Integer roomLayer) {
        ClassroomQuery query = new ClassroomQuery();
        query.createCriteria().andRoomNameEqualTo(roomName);
        Classroom classroom = classroomMapper.selectOneByExample(query);
        if (classroom!=null){
            return ServerResponse.createByErrorMessage("教室已经存在");
        }
        Classroom c = new Classroom();
        c.setRoomName(roomName);
        c.setRoomSpace(roomSpace);
        c.setRoomLayer(roomLayer);
        int count = classroomMapper.insertSelective(c);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        return ServerResponse.createByErrorMessage("新建失败");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = classroomMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<Classroom> find(Integer id) {
        return ServerResponse.createBySuccess(classroomMapper.selectByPrimaryKey(id));    }

    @Override
    public ServerResponse<PageInfo<Classroom>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Classroom> list = classroomMapper.selectByExample(new ClassroomQuery());
        PageInfo<Classroom> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<String> update(Integer id, String roomName, Integer roomSpace, Integer roomLaye) {
        Classroom classroom = classroomMapper.selectByPrimaryKey(id);
        classroom.setRoomName(roomName);
        classroom.setRoomSpace(roomSpace);
        classroom.setRoomLayer(roomLaye);
        System.out.println(roomLaye);
        int count = classroomMapper.updateByPrimaryKeySelective(classroom);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }
}
