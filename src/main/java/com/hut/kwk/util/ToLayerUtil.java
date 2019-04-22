package com.hut.kwk.util;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.LayerResponse;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.User;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-21
 *
 * @author Wang Heng
 */
public class ToLayerUtil {
    public static LayerResponse<List<User>> toLayer(ServerResponse<PageInfo<User>> serverResponse){
        return new LayerResponse<>(serverResponse.getStatus(),serverResponse.getMsg(),serverResponse.getData().getTotal(),serverResponse.getData().getList());
    }
}
