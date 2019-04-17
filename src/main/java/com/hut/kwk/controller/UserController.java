package com.hut.kwk.controller;

import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.User;
import com.hut.kwk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @RequestMapping("add/{username}/{password}/{role}")
    public ServerResponse<String> add(@PathVariable("username") String username,
                                      @PathVariable("password") String password,
                                      @PathVariable("role") String role) {
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
    @RequestMapping("login/{username}/{password}/{role}")
    public ServerResponse<User> login(@PathVariable("username") String username,
                                      @PathVariable("password") String password,
                                      @PathVariable("role") String role, HttpSession session) {

        ServerResponse<User> login = iUserService.login(username, password, role);
        if (login.isSuccess()) {
            session.setAttribute(session.getId(), login.getData());
            return login;
        }
        return ServerResponse.createByErrorMessage("登录失败");
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("del/{id}")
    public ServerResponse<String> del(@PathVariable("id") Integer id) {
        return iUserService.del(id);
    }

    /**
     * 查找全部用户
     *
     * @param session
     * @return
     */
    @RequestMapping("findAll")
    public ServerResponse<List<User>> findAll(HttpSession session) {
        User user = (User) session.getAttribute(session.getId());
        return iUserService.findAll(user.getRole());
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @RequestMapping("find/{id}")
    public ServerResponse<User> find(@PathVariable("id") Integer id) {
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
    @RequestMapping("update/{id}/{username}/{passeord}")
    public ServerResponse<String> update(@PathVariable("id") Integer id,
                                         @PathVariable("username") String username,
                                         @PathVariable("password") String password) {
        return iUserService.update(id, username, password);
    }
}
