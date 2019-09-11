package cn.liaozhonghao.www.service.impl;

import cn.liaozhonghao.www.dao.UserDao;
import cn.liaozhonghao.www.model.User;
import cn.liaozhonghao.www.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> finaAll() {
        return userDao.findAll();
    }
}
