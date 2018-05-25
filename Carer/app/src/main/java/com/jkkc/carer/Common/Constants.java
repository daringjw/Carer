package com.jkkc.carer.Common;

/**
 * Created by Guan on 2018/5/25.
 */

public class Constants {

    public static final String IP = "182.92.3.109";
    public static final String PORT = ":10100";


    //baseUrl
    public static final String BASE_URL = "http://" + IP + PORT;
    //登录
    public static final String login = BASE_URL + "/mobileLogin/login.do";
    //修改密码
    public static final String backPass = BASE_URL + "/careManage/backPass.do";
    //上传头像
    public static final String headImgUrl = BASE_URL + "/careManage/headImgUrl.do";
    //个人修改
    public static final String updCare = BASE_URL + "/careManage/updCare.do";
    //获取个人信息
    public static final String getCare = BASE_URL + "/careManage/getCare.do";




}
