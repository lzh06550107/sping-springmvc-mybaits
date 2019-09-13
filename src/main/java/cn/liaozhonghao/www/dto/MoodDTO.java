package cn.liaozhonghao.www.dto;

import cn.liaozhonghao.www.model.Mood;

import java.io.Serializable;

public class MoodDTO extends Mood implements Serializable {

    private String userName; // 用户名称

    private String userAccount; // 用户的账号

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
