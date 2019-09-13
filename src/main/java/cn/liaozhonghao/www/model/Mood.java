package cn.liaozhonghao.www.model;

import java.io.Serializable;
import java.util.Date;

public class Mood implements Serializable {


    private String id; // 主键

    private String content; // 说说内容

    private Integer praiseNum; // 点赞数量

    private String userId; // 用户id

    private Date publishTime; // 发表时间


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
