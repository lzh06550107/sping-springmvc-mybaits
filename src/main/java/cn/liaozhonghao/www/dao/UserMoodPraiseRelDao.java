package cn.liaozhonghao.www.dao;

import cn.liaozhonghao.www.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMoodPraiseRelDao {

    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);
}
