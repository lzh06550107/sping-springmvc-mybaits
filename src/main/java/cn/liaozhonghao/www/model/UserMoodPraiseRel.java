package cn.liaozhonghao.www.model;

import java.io.Serializable;

public class UserMoodPraiseRel implements Serializable {

    private String id; // 主键

    private String userId; // 用户id

    private String moodId; // 说说id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }
}
