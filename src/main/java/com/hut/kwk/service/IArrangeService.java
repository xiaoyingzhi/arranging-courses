package com.hut.kwk.service;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.Arrange;

/**
 * Create by Wang Heng on 2019-04-23
 *
 * @author Wang Heng
 */
public interface IArrangeService {
    ServerResponse<String> add(Arrange arrange);

    ServerResponse<String> del(Integer id);

    ServerResponse<String> update(Arrange arrange);

    ServerResponse<Arrange> find(Integer id);

    ServerResponse<PageInfo<Arrange>> findAll(Integer pageNum, Integer pageSize);
}
