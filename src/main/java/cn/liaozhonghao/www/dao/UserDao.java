package cn.liaozhonghao.www.dao;

import cn.liaozhonghao.www.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> findAll();
}
