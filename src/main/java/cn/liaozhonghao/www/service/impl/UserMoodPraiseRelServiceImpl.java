package cn.liaozhonghao.www.service.impl;

import cn.liaozhonghao.www.dao.UserMoodPraiseRelDao;
import cn.liaozhonghao.www.model.UserMoodPraiseRel;
import cn.liaozhonghao.www.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {

    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Override
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
}
