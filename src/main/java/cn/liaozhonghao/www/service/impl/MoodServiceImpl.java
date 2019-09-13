package cn.liaozhonghao.www.service.impl;

import cn.liaozhonghao.www.dao.MoodDao;
import cn.liaozhonghao.www.dao.UserDao;
import cn.liaozhonghao.www.dao.UserMoodPraiseRelDao;
import cn.liaozhonghao.www.dto.MoodDTO;
import cn.liaozhonghao.www.model.Mood;
import cn.liaozhonghao.www.model.User;
import cn.liaozhonghao.www.model.UserMoodPraiseRel;
import cn.liaozhonghao.www.mq.MoodProducer;
import cn.liaozhonghao.www.service.MoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MoodServiceImpl implements MoodService {

    @Resource
    private MoodDao moodDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Resource
    private RedisTemplate redisTemplate;

    private static Destination destination = new ActiveMQQueue("ay.queue.high.concurrency.praise");

    @Resource
    private MoodProducer moodProducer;

    //key 命名规范 ： 项目名称＋模块名称＋具体内容
    private static final String PRAISE_HASH_KEY="springmv.mybaits.boot.mood.id.list.key";

    @Override
    public List<MoodDTO> findAll() {
        List<Mood> moodList = moodDao.findAll();
        return converModel2DTO(moodList);
    }

    @Override
    public Mood findById(String id) {
        return moodDao.findById(id);
    }

    @Override
    public boolean update(Mood mood) {
        return moodDao.update(mood);
    }

    @Override
    public boolean praiseMood(String userId, String moodId) {
        // 保存关联关系
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
        // 更新说说的点赞数量
        Mood mood = this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        this.update(mood);

        return Boolean.TRUE;
    }




    @Override
    public boolean praiseMoodForRedis(String userId, String moodId) {
        //1.存放到set集合中
        redisTemplate.opsForSet().add(PRAISE_HASH_KEY, moodId);
        //2.存放到set中
        redisTemplate.opsForSet().add(moodId, userId);
        return false;
    }

    @Override
    public boolean praiseMoodForQueue(String userId, String moodId) {
        // 异步处理方式
        MoodDTO moodDTO = new MoodDTO();
        moodDTO.setUserId(userId);
        moodDTO.setId(moodId);
        // 发送消息
        moodProducer.sendMessage(destination, moodDTO);
        return false;
    }

    @Override
    public List<MoodDTO> findAllForRedis() {
        List<Mood> moodList = moodDao.findAll();
        if(CollectionUtils.isEmpty(moodList)) {
            return Collections.EMPTY_LIST;
        }
        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();

        for(Mood mood: moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());
            // 总点赞数量：数据库的点赞数量+redis的点赞数量
            moodDTO.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(mood.getId()).intValue());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());
            // 通过userID查询用户
            User user = userDao.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
            moodDTOList.add(moodDTO);
        }
        return moodDTOList;
    }

    private List<MoodDTO> converModel2DTO(List<Mood> moodList) {

        if(CollectionUtils.isEmpty(moodList)) {
            return Collections.EMPTY_LIST;
        }

        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();
        for(Mood mood:moodList) {
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setContent(mood.getContent());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setUserId(mood.getUserId());
            moodDTOList.add(moodDTO);
            // 设置用户信息
            User user = userDao.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
        }
        return moodDTOList;
    }
}
