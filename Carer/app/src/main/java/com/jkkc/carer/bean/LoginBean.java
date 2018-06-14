package com.jkkc.carer.bean;

/**
 * Created by Guan on 2018/5/30.
 */

public class LoginBean {


    /**
     * token : 28508eaf-0d3e-4406-8e40-e10c3400d81e
     * peopleId : A00001
     * userAccount : 15999999999
     * code : success
     * msg : 登录成功
     */

    private String token;
    private String peopleId;
    private String userAccount;
    private String code;
    private String msg;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
