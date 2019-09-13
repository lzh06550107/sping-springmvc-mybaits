package cn.liaozhonghao.www.job;

import cn.liaozhonghao.www.model.Mood;
import cn.liaozhonghao.www.model.UserMoodPraiseRel;
import cn.liaozhonghao.www.service.MoodService;
import cn.liaozhonghao.www.service.UserMoodPraiseRelService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;

@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBJob {

    @Resource
    private RedisTemplate redisTemplate;

    private static final String PRAISE_HASH_KEY="springmv.mybaits.boot.mood.id.list.key";

    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;

    @Resource
    private MoodService moodService;

    @Scheduled(cron="*/10 * * * * *")
    public void savePraiseDataToDB2() {
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if(CollectionUtils.isEmpty(moods)) {
            return;
        }
        for(String moodId: moods) {
            if(redisTemplate.opsForSet().members(moodId) == null) {
                continue;
            }else {
                Set<String> userIds = redisTemplate.opsForSet().members(moodId);
                if(CollectionUtils.isEmpty(userIds)) {
                    continue;
                }else {
                    for(String userId: userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.findById(moodId);
                    mood.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(moodId).intValue());
                    moodService.update(mood);
                    // 清除Redis缓存中的数据
                    redisTemplate.delete(moodId);
                }
            }
        }
        // 清除Redis缓存中的数据
        redisTemplate.delete(PRAISE_HASH_KEY);
    }
}
