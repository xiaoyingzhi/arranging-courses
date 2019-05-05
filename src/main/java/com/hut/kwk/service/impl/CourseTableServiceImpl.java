package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.CourseTable;
import com.hut.kwk.model.entity.CourseTableQuery;
import com.hut.kwk.model.mapper.CourseTableMapper;
import com.hut.kwk.service.ICourseTableService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by kwk on 2019-04-24
 *
 * @author kwk
 */
@Service
public class CourseTableServiceImpl implements ICourseTableService {

    @Autowired
    private CourseTableMapper courseTableMapper;

    /**
     * 排课算法
     * @return
     */
    @Override
    public ServerResponse<String> arr() {

        return null;
    }

    @Override
    public ServerResponse<List<CourseTable>> findBy(String className, String teacherName,Integer week) {
        CourseTableQuery query =new CourseTableQuery();
        CourseTableQuery.Criteria criteria = query.createCriteria();
        if (className!=null&&!"".equals(className)){
            criteria.andClassNameLike("%"+className+"%");
        }
        if (teacherName!=null&&!"".equals(teacherName)){
            criteria.andTecherNameEqualTo(teacherName);
        }
        criteria.andStatuEqualTo(week);
        List<CourseTable> courseTables = courseTableMapper.selectByExample(query);
        return ServerResponse.createBySuccess(courseTables);
    }

    @Override
    public ServerResponse<PageInfo<CourseTable>> findAllBy(Integer pageNum, Integer pageSize, Integer week, String courseName, String className, String teacherName, String roomName) {
        CourseTableQuery query = new CourseTableQuery();
        query.createCriteria().andStatuEqualTo(week)
                .andCourseNameLike("%"+courseName+"%")
                .andClassNameLike("%"+className+"%")
                .andTecherNameLike("%"+teacherName+"%")
                .andRoomNameLike("%"+roomName+"%");
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTable> list = courseTableMapper.selectByExampleWithRowbounds(query,new RowBounds((pageNum-1)*10,pageSize));
        PageInfo<CourseTable> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(courseTableMapper.countByExample(query));
        return ServerResponse.createBySuccess(pageInfo);

    }

    @Override
    public ServerResponse<String> adjust(Integer courseTableId, Integer classroomId, Integer week, String day, Integer number, String classroomName) {
        CourseTable courseTable = courseTableMapper.selectByPrimaryKey(courseTableId);
        courseTable.setStatu(week);
        courseTable.setRoomId(classroomId);
        courseTable.setRoomName(classroomName);
        courseTable.setTimeName(day);
        courseTable.setTimeId(number);
        int count = courseTableMapper.updateByPrimaryKey(courseTable);
        if (count >0){
            return ServerResponse.createBySuccessMessage("调整成功");
        }
        return ServerResponse.createByErrorMessage("调整失败");
    }

    @Override
    public ServerResponse<String> add(CourseTable courseTable) {
        //todo
        return null;
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = courseTableMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(CourseTable courseTable) {
        //todo
        return null;
    }

    @Override
    public ServerResponse<CourseTable> find(Integer id) {
        return ServerResponse.createBySuccess(courseTableMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<CourseTable>> findAll(Integer pageNum, Integer pageSize) {
        CourseTableQuery query = new CourseTableQuery();
        PageHelper.startPage(pageNum, pageSize);
        List<CourseTable> list = courseTableMapper.selectByExampleWithRowbounds(query,new RowBounds((pageNum-1)*10,pageSize));
        PageInfo<CourseTable> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(courseTableMapper.countByExample(query));
        return ServerResponse.createBySuccess(pageInfo);
    }

}
