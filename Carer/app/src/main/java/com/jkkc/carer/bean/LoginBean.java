package com.jkkc.carer.bean;

/**
 * Created by Guan on 2018/5/30.
 */

public class LoginBean {


    /**
     * token : 3effbb19-1fb8-42d3-8cf1-1cc38a5f382a
     * id : 37
     * peopleId : A00001
     * phoneNum : 15999999999
     * code : success
     * msg : 登录成功
     */

    private String token;
    private int id;
    private String peopleId;
    private String phoneNum;
    private String code;
    private String msg;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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
