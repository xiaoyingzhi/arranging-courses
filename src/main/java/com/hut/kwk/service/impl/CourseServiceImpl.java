package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Course;
import com.hut.kwk.model.entity.CourseQuery;
import com.hut.kwk.model.mapper.CourseMapper;
import com.hut.kwk.service.ICourseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by kwk on 2019-04-17
 *
 * @author kwk
 */
@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ServerResponse<String> add(String courseName, Integer courseTime,Integer sord) {
        CourseQuery query = new CourseQuery();
        query.createCriteria().andCourseNameEqualTo(courseName);
        Course course = courseMapper.selectOneByExample(query);
        if (course != null) {
            return ServerResponse.createByErrorMessage("课程已经存在");
        }
        Course c = new Course();
        c.setCourseName(courseName);
        c.setCourseTime(courseTime);
        c.setStatu(sord);
        int count = courseMapper.insertSelective(c);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        return ServerResponse.createByErrorMessage("新建失败");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = courseMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(Integer id, String courseName, Integer courseTime,Integer sord) {
        Course course = courseMapper.selectByPrimaryKey(id);
        course.setCourseName(courseName);
        course.setCourseTime(courseTime);
        course.setStatu(sord);
        int count = courseMapper.updateByPrimaryKeySelective(course);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Course> find(Integer id) {
        return ServerResponse.createBySuccess(courseMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Course>> findAll(Integer pageNum, Integer pageSize) {
        CourseQuery query = new CourseQuery();
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseMapper.selectByExampleWithRowbounds(query,new RowBounds((pageNum-1)*10,pageSize));
        PageInfo<Course> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(courseMapper.countByExample(query));
        return ServerResponse.createBySuccess(pageInfo);
    }
}
