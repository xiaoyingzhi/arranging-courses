package com.hut.kwk.controller;

import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.LayerResponse;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.User;
import com.hut.kwk.service.IUserService;
import com.hut.kwk.util.ToLayerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param role
     * @return
     */
    @RequestMapping("add")
    public ServerResponse<String> add(String username, String password, String role) {
        return iUserService.add(username, password, role);

    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param role
     * @param session
     * @return
     */
    @RequestMapping("login")
    public ServerResponse<User> login(String username, String password, String role, HttpSession session) {

        ServerResponse<User> login = iUserService.login(username, password, role);
        if (login.isSuccess()) {
            session.setAttribute(session.getId(), login.getData());

        }
        return login;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ServerResponse<String> del(Integer id) {
        return iUserService.del(id);
    }

    /**
     * 查找全部用户
     *
     * @param session
     * @return
     */
    @RequestMapping("findAll")
    public LayerResponse<List<User>> findAll(HttpSession session, Integer pageNum, Integer pageSize) {
        User user = (User) session.getAttribute(session.getId());
        ServerResponse<PageInfo<User>> all = iUserService.findAll("超级管理员", pageNum, pageSize);
        return ToLayerUtil.toLayer(all);
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @RequestMapping("find")
    public ServerResponse<User> find(Integer id) {
        return iUserService.findById(id);
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("update")
    public ServerResponse<String> update(Integer id, String username, String password,String role) {
        return iUserService.update(id, username, password,role);
    }
}
