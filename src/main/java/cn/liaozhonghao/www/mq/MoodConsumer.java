package cn.liaozhonghao.www.mq;

import cn.liaozhonghao.www.dto.MoodDTO;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * 消费者
 */
@Component
public class MoodConsumer implements MessageListener {

    private static final String PRAISE_HASH_KEY="springmv.mybaits.boot.mood.id.list.key";

    private static final Logger logger= LoggerFactory.getLogger(MoodProducer.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message) {
        try {
            // 从message对象中获取说说实体
            MoodDTO moodDTO = (MoodDTO) ((ActiveMQObjectMessage)message).getObject();

            // 1.存放到set中
            redisTemplate.opsForSet().add(PRAISE_HASH_KEY, moodDTO.getId());
            // 2.存放到set中
            redisTemplate.opsForSet().add(moodDTO.getId(), moodDTO.getUserId());

            logger.info("消费者--->>>用户id: {} 给说说id: {} 点赞", moodDTO.getUserId(), moodDTO.getId());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
