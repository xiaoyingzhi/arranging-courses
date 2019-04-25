package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Arrange;
import com.hut.kwk.model.entity.ArrangeQuery;
import com.hut.kwk.model.mapper.ArrangeMapper;
import com.hut.kwk.service.IArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-23
 *
 * @author Wang Heng
 */
@Service
public class ArrangeServiceImpl implements IArrangeService {
    @Autowired
    private ArrangeMapper arrangeMapper;

    @Override
    public ServerResponse<String> add(Arrange arrange) {
        ArrangeQuery query = new ArrangeQuery();
        query.createCriteria().andClassIdEqualTo(arrange.getClassId())
                .andTecherIdEqualTo(arrange.getTecherId())
                .andCourseIdEqualTo(arrange.getCourseId())
                .andSemeIdEqualTo(arrange.getSemeId());
        query.setDistinct(true);
        Arrange byExample = arrangeMapper.selectOneByExample(query);
        if (byExample!=null){
            return ServerResponse.createByErrorMessage("已经存在此分配");
        }
        int count = arrangeMapper.insertSelective(arrange);
        if (count ==0){
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
        PageHelper.startPage(pageNum, pageSize);
        List<Arrange> list = arrangeMapper.selectByExample(new ArrangeQuery());
        PageInfo<Arrange> pageInfo = new PageInfo<>(list);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
