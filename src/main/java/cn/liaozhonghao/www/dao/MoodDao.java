package cn.liaozhonghao.www.dao;

import cn.liaozhonghao.www.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoodDao {

    List<Mood> findAll();

    Mood findById(String id);

    boolean update(@Param("mood") Mood mood);
}
