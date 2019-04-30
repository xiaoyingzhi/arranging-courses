package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.*;
import com.hut.kwk.model.mapper.ClassroomMapper;
import com.hut.kwk.model.mapper.CourseTableMapper;
import com.hut.kwk.service.IClassroomService;
import com.hut.kwk.util.DayUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@Service
public class ClassroomServiceImpl implements IClassroomService {

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private CourseTableMapper courseTableMapper;

    @Override
    public ServerResponse<String> add(String roomName, Integer roomSpace, Integer roomLayer) {
        ClassroomQuery query = new ClassroomQuery();
        query.createCriteria().andRoomNameEqualTo(roomName);
        Classroom classroom = classroomMapper.selectOneByExample(query);
        if (classroom != null) {
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
        return ServerResponse.createBySuccess(classroomMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Classroom>> findAll(Integer pageNum, Integer pageSize) {
        ClassroomQuery query = new ClassroomQuery();
        PageHelper.startPage(pageNum, pageSize);
        List<Classroom> list = classroomMapper.selectByExampleWithRowbounds(query, new RowBounds((pageNum - 1) * 10, pageSize));
        PageInfo<Classroom> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(classroomMapper.countByExample(query));
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

    @Override
    public List<ClassroomFree> findByWeek(Integer week, String roomName) {
        ClassroomQuery query1 = new ClassroomQuery();
        ClassroomQuery.Criteria criteria1 = query1.createCriteria();
        if (roomName != null && !"".equals(roomName)){
            criteria1.andRoomNameLike("%" + roomName + "%");
        }
        List<Classroom> classrooms = classroomMapper.selectByExample(query1);

        List<ClassroomFree> classroomFrees = new ArrayList<>();

        for (Classroom classroom : classrooms) {
            int[][] arr = new int[5][4];
            Integer classroomId = classroom.getId();

            CourseTableQuery query = new CourseTableQuery();
            CourseTableQuery.Criteria criteria = query.createCriteria();
            criteria.andStatuEqualTo(week)
                    .andRoomIdEqualTo(classroomId);
            List<CourseTable> courseTables = courseTableMapper.selectByExample(query);

            for (CourseTable courseTable : courseTables) {
                Integer number = courseTable.getTimeId();
                String timeName = courseTable.getTimeName();
                arr[DayUtil.toDayNum(timeName) - 1][number - 1] = 1;
            }

            for (int x = 1; x <= 5; x++) {
                for (int y = 1; y <= 4; y++) {
                    if (arr[x - 1][y - 1] == 0) {
                        classroomFrees.add(toFree(classroom, DayUtil.toDay(x), y,week));
                    }
                }
            }
        }
        return classroomFrees;
    }

    private ClassroomFree toFree(Classroom classroom, String day, Integer number,Integer week) {
        ClassroomFree classroomFree = new ClassroomFree();
        classroomFree.setClassroomId(classroom.getId());
        classroomFree.setClassroomName(classroom.getRoomName());
        classroomFree.setDay(day);
        classroomFree.setWeek(week);
        classroomFree.setNumber(number);
        return classroomFree;
    }
}
