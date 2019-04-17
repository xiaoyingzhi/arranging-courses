package com.hut.kwk.service;

import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.User;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
public interface IUserService {
    ServerResponse<String> add(String username, String password, String role);

    ServerResponse<User> login(String username, String password, String role);

    ServerResponse<String> del(Integer id);

    ServerResponse<List<User>> findAll(String role);

    ServerResponse<User> findById(Integer id);

    ServerResponse<String> update(Integer id, String username, String password);
}
