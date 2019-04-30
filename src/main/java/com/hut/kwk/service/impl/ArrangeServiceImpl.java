package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Arrange;
import com.hut.kwk.model.entity.ArrangeQuery;
import com.hut.kwk.model.entity.ClassesQuery;
import com.hut.kwk.model.entity.CourseQuery;
import com.hut.kwk.model.mapper.ArrangeMapper;
import com.hut.kwk.model.mapper.ClassesMapper;
import com.hut.kwk.model.mapper.CourseMapper;
import com.hut.kwk.service.IArrangeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by kwk on 2019-04-23
 *
 * @author kwk
 */
@Service
public class ArrangeServiceImpl implements IArrangeService {
    @Autowired
    private ArrangeMapper arrangeMapper;

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ServerResponse<String> add(Arrange arrange) {
        String classNames = arrange.getClassName();
        String[] split = classNames.split("_");
        ArrangeQuery query = new ArrangeQuery();
        ArrangeQuery.Criteria criteria = query.createCriteria();
        criteria.andCourseIdEqualTo(arrange.getCourseId())
                .andSemeIdEqualTo(arrange.getSemeId());
        int number = 0;
        for (String s : split){
            criteria.andClassNameLike("%"+s+"%");
            number+=Integer.valueOf(classesMapper.selectOneByExample(new ClassesQuery()).getClassNumber());
        }

        query.setDistinct(true);
        Arrange arrange2 = arrangeMapper.selectOneByExample(query);

        if (arrange2 !=  null) {
           return ServerResponse.createByErrorMessage("班级："+arrange2.getClassName()+" 课程："+arrange2.getCourseName()+" 已经存在分配");
        }
        arrange.setStatu(number);
        arrange.setSrd(courseMapper.selectOneByExample(new CourseQuery()).getStatu());
        int count = arrangeMapper.insertSelective(arrange);
        if (count == 0) {
            return ServerResponse.createByErrorMessage("分配失败");
        }
        return ServerResponse.createBySuccessMessage("分配成功");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = arrangeMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> update(Arrange arrange) {
        Arrange a = arrangeMapper.selectByPrimaryKey(arrange.getId());
        //todo  到底怎么修改？ 目前不让他修改

        int count = arrangeMapper.updateByPrimaryKeySelective(a);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

    @Override
    public ServerResponse<Arrange> find(Integer id) {
        return ServerResponse.createBySuccess(arrangeMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<PageInfo<Arrange>> findAll(Integer pageNum, Integer pageSize) {
        ArrangeQuery query = new ArrangeQuery();
        PageHelper.startPage(pageNum, pageSize);
        List<Arrange> list = arrangeMapper.selectByExampleWithRowbounds(query, new RowBounds((pageNum - 1) * 10, pageSize));
        PageInfo<Arrange> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(arrangeMapper.countByExample(query));
        return ServerResponse.createBySuccess(pageInfo);
    }
}
