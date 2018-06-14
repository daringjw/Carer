package com.jkkc.carer.Common;

/**
 * Created by Guan on 2018/5/25.
 */

public class Constants {

    //  http://123.56.65.127:28088

    public static final String IP = "123.56.65.127";
    public static final String PORT = ":28088";


    //baseUrl
    public static final String BASE_URL = "http://" + IP + PORT + "/igim";
    //
    public static final String base_url1 = "http://" + IP + PORT;

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
    //6.添加护理信息
    public static final String addDailyNursingInfo = BASE_URL + "/careController/addDailyNursingInfo.do";

    //7.获取排班信息
    public static final String workScheduleList = BASE_URL + "/careController/workScheduleList.do";

    //8.获取交接班信息(只返回个人或值班区域一样的集合)
    public static final String workShiftList = BASE_URL + "/careController/workShiftList.do";

    //A.交班操作：
    public static final String addWorkerShiftRecord = BASE_URL + "/careController/addWorkerShiftRecord.do";

    //B．接班操作：
    public static final String jieban = BASE_URL + "/careController/jieban.do";

    //10 10.在线培训
    // A．获取视频类信息
    public static final String onlineVideo = BASE_URL + "/careController/onlineVideo.do";
    //B．获取文件类信息
    public static final String onlineFile = BASE_URL + "/careController/onlineFile.do";
    //C．更新视频、图片、文档点击次数
    public static final String fileNumber = BASE_URL + "/careController/fileNumber.do";


}
