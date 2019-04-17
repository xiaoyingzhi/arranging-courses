package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Teaching;
import com.hut.kwk.model.entity.TeachingQuery;
import com.hut.kwk.model.mapper.TeachingMapper;
import com.hut.kwk.service.ITeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@Service
public class TeachingServiceImpl implements ITeachingService {
    @Autowired
    private TeachingMapper teachingMapper;

    @Override
    public ServerResponse<String> add(Integer classId, Integer teacherId, Integer semId, String className, String techerName) {
        TeachingQuery query = new TeachingQuery();
        query.createCriteria().andClassIdEqualTo(classId).andTeacherIdEqualTo(teacherId).andSemIdEqualTo(semId);
        Teaching teaching = teachingMapper.selectOneByExample(query);
        if (teaching != null) {
            return ServerResponse.createByErrorMessage("已经存在分配");
        }
        Teaching t = new Teaching();
        t.setClassId(classId);
        t.setTeacherId(teacherId);
        t.setSemId(semId);
        t.setClassName(className);
        t.setTecherName(techerName);
        int count = teachingMapper.insertSelective(t);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        return ServerResponse.createByErrorMessage("新建失败");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = teachingMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");

    }

    @Override
    public ServerResponse<String> update(Integer id, Integer classId, Integer teacherId, Integer semId, String className, String techerName) {
        Teaching t = teachingMapper.selectByPrimaryKey(id);
        t.setClassId(classId);
        t.setTeacherId(teacherId);
        t.setSemId(semId);
        t.setClassName(className);
        t.setTecherName(techerName);
        int count = teachingMapper.updateByPrimaryKeySelective(t);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Teaching> find(Integer id) {
        return ServerResponse.createBySuccess(teachingMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Teaching>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teaching> list = teachingMapper.selectByExample(new TeachingQuery());
        PageInfo<Teaching> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
