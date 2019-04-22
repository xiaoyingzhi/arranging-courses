package com.hut.kwk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hut.kwk.constant.Constant;
import com.hut.kwk.constant.ServerResponse;
import com.hut.kwk.model.entity.User;
import com.hut.kwk.model.entity.UserQuery;
import com.hut.kwk.model.mapper.UserMapper;
import com.hut.kwk.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by Wang Heng on 2019-04-17
 *
 * @author Wang Heng
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<String> add(String username, String password, String role) {
        UserQuery query = new UserQuery();
        query.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(query);
        if (users.size() > 0) {
            return ServerResponse.createByErrorMessage("用户已经存在");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        int count = userMapper.insertSelective(user);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("用户创建成功");
        }
        return ServerResponse.createByErrorMessage("用户创建失败");
    }

    @Override
    public ServerResponse<User> login(String username, String password, String role) {
        UserQuery query = new UserQuery();
        query.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password).andRoleEqualTo(role);
        User user = userMapper.selectOneByExample(query);
        if (user != null) {
            user.setPassword("");
            return ServerResponse.createBySuccess("登录成功", user);
        }
        return ServerResponse.createByErrorMessage("账号信息错误");
    }

    @Override
    public ServerResponse<String> del(Integer id) {
        int count = userMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<PageInfo<User>> findAll(String role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        UserQuery query = new UserQuery();
        List<User> users ;
        if (role.equals(Constant.ADMIN)) {
            users=  userMapper.selectByExample(query);
        }else {
            query.createCriteria().andRoleEqualTo(Constant.NOT_ADMIN);
            users = userMapper.selectByExample(query);
        }
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return ServerResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServerResponse<User> findById(Integer id) {
        return ServerResponse.createBySuccess(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public ServerResponse<String> update(Integer id, String username, String password,String role) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count>0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }
}
