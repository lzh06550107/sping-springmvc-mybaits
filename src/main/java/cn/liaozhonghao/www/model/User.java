package cn.liaozhonghao.www.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String name;
    private String account;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
