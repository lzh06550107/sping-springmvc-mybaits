package cn.liaozhonghao.www.service.impl;

import cn.liaozhonghao.www.dao.UserDao;
import cn.liaozhonghao.www.dto.UserDTO;
import cn.liaozhonghao.www.model.User;
import cn.liaozhonghao.www.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public UserDTO find(String id) {
        User user = userDao.find(id);
        return converModel2DTO(user);
    }

    private UserDTO converModel2DTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setAccount(user.getAccount());
        userDTO.setName(user.getName());
        return userDTO;
    }


}
