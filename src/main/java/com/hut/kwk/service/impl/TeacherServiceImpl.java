package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teacher;
import com.hut.kwk.model.entity.TeacherQuery;
import com.hut.kwk.model.mapper.TeacherMapper;
import com.hut.kwk.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@Service
public class TeacherServiceImpl implements ITeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public ServerResponse<String> add(String name, String phone,Integer countLimit) {
        TeacherQuery query = new TeacherQuery();
        query.createCriteria().andTeacherNameEqualTo(name);
        Teacher techer = teacherMapper.selectOneByExample(query);
        if (techer != null) {
            return ServerResponse.createByErrorMessage("教师已经存在");
        }
        Teacher t = new Teacher();
        t.setTeacherName(name);
        t.setTeacherPhone(phone);
        t.setCountLimit(countLimit);
        int count = teacherMapper.insertSelective(t);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        return ServerResponse.createByErrorMessage("新建失败");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = teacherMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(Integer id, String name, String phone,Integer countLimit) {
        Teacher techer = teacherMapper.selectByPrimaryKey(id);
        techer.setTeacherName(name);
        techer.setTeacherPhone(phone);
        techer.setCountLimit(countLimit);
        int count = teacherMapper.updateByPrimaryKeySelective(techer);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Teacher> find(Integer id) {
        return ServerResponse.createBySuccess(teacherMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Teacher>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.selectByExample(new TeacherQuery());
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
