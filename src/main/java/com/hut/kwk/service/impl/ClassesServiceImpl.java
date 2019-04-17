package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Classes;
import com.hut.kwk.model.entity.ClassesQuery;
import com.hut.kwk.model.mapper.ClassesMapper;
import com.hut.kwk.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@Service
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public ServerResponse<String> add(String className, String classNum) {
        ClassesQuery query = new ClassesQuery();
        query.createCriteria().andClassNameEqualTo(className);
        Classes classes = classesMapper.selectOneByExample(query);
        if (classes != null) {
            return ServerResponse.createByErrorMessage("班级已经存在");
        }
        Classes c = new Classes();
        c.setClassName(className);
        c.setClassNumber(classNum);
        int count = classesMapper.insertSelective(c);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("新建成功");
        }
        return ServerResponse.createByErrorMessage("新建失败");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = classesMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(Integer id, String className, String classNum) {
        Classes classes = classesMapper.selectByPrimaryKey(id);
        classes.setClassName(className);
        classes.setClassNumber(classNum);
        int count = classesMapper.updateByPrimaryKeySelective(classes);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Classes> find(Integer id) {
         return ServerResponse.createBySuccess(classesMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Classes>> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Classes> list = classesMapper.selectByExample(new ClassesQuery());
        PageInfo<Classes> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
