package com.jkkc.carer.bean;

import java.util.List;

/**
 * Created by Guan on 2018/6/14.
 */

public class SchedulingInfomation {


    /**
     * workSchedule : [{"areaManager":"李倩","classes":"白班","deptName":"服务部","dutyArea":"一楼","dutyPosition":"护士部","dutyString":"2018-06-14 00:00:00.0","entryTime":"2018-06-14 11:49:50.0","id":113,"remarks":"","workerId":"A00001","workerName":"小三"}]
     * code : success
     * msg : 获取排班信息成功
     */

    private String code;
    private String msg;
    private List<WorkScheduleBean> workSchedule;

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

    public List<WorkScheduleBean> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<WorkScheduleBean> workSchedule) {
        this.workSchedule = workSchedule;
    }

    public static class WorkScheduleBean {
        /**
         * areaManager : 李倩
         * classes : 白班
         * deptName : 服务部
         * dutyArea : 一楼
         * dutyPosition : 护士部
         * dutyString : 2018-06-14 00:00:00.0
         * entryTime : 2018-06-14 11:49:50.0
         * id : 113
         * remarks :
         * workerId : A00001
         * workerName : 小三
         */

        private String areaManager;
        private String classes;
        private String deptName;
        private String dutyArea;
        private String dutyPosition;
        private String dutyString;
        private String entryTime;
        private int id;
        private String remarks;
        private String workerId;
        private String workerName;

        public String getAreaManager() {
            return areaManager;
        }

        public void setAreaManager(String areaManager) {
            this.areaManager = areaManager;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDutyArea() {
            return dutyArea;
        }

        public void setDutyArea(String dutyArea) {
            this.dutyArea = dutyArea;
        }

        public String getDutyPosition() {
            return dutyPosition;
        }

        public void setDutyPosition(String dutyPosition) {
            this.dutyPosition = dutyPosition;
        }

        public String getDutyString() {
            return dutyString;
        }

        public void setDutyString(String dutyString) {
            this.dutyString = dutyString;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getWorkerId() {
            return workerId;
        }

        public void setWorkerId(String workerId) {
            this.workerId = workerId;
        }

        public String getWorkerName() {
            return workerName;
        }

        public void setWorkerName(String workerName) {
            this.workerName = workerName;
        }
    }
}
