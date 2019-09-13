package cn.liaozhonghao.www.mq;

import cn.liaozhonghao.www.dto.MoodDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;


@Component
public class MoodProducer {

    @Resource
    private JmsTemplate jmsTemplate;

    private static final Logger logger= LoggerFactory.getLogger(MoodProducer.class);

    /**
     * 发送消息到队列
     * @param destination 队列名称
     * @param mood 消息对象
     */
    public void sendMessage(Destination destination, final MoodDTO mood) {
        // 记录日志
        logger.info("生成者--->>>用户id: {} 给说说id: {} 点赞", mood.getUserId(), mood.getId());
        // mood 实体需要实现Serializable序列号接口
        jmsTemplate.convertAndSend(destination, mood);
    }

}
