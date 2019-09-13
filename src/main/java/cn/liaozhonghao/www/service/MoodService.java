package cn.liaozhonghao.www.service;

import cn.liaozhonghao.www.dto.MoodDTO;
import cn.liaozhonghao.www.model.Mood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoodService {

    List<MoodDTO> findAll();

    Mood findById(String id);

    boolean update(@Param("mood") Mood mood);

    boolean praiseMood(String userId, String moodId);

    boolean praiseMoodForRedis(String userId, String moodId);

    boolean praiseMoodForQueue(String userId, String moodId);

    List<MoodDTO> findAllForRedis();

}
