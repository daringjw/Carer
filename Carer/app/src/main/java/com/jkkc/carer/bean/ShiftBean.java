package com.jkkc.carer.bean;

/**
 * Created by Guan on 2018/6/14.
 */

public class ShiftBean {


    /**
     * workerShiftList : {"areaManager":"小三","areaManagerId":"A00001","dutyArea":"一楼","entryTime":"2018-06-14 11:50:53.0","id":25,"remarks":"","shiftState":"未上班","workerId":"A00001","workerName":"张三"}
     * code : success
     * msg : 获取交接班信息成功
     */

    private WorkerShiftListBean workerShiftList;
    private String code;
    private String msg;

    public WorkerShiftListBean getWorkerShiftList() {
        return workerShiftList;
    }

    public void setWorkerShiftList(WorkerShiftListBean workerShiftList) {
        this.workerShiftList = workerShiftList;
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

    public static class WorkerShiftListBean {
        /**
         * areaManager : 小三
         * areaManagerId : A00001
         * dutyArea : 一楼
         * entryTime : 2018-06-14 11:50:53.0
         * id : 25
         * remarks :
         * shiftState : 未上班
         * workerId : A00001
         * workerName : 张三
         */

        private String areaManager;
        private String areaManagerId;
        private String dutyArea;
        private String entryTime;
        private int id;
        private String remarks;
        private String shiftState;
        private String workerId;
        private String workerName;

        public String getAreaManager() {
            return areaManager;
        }

        public void setAreaManager(String areaManager) {
            this.areaManager = areaManager;
        }

        public String getAreaManagerId() {
            return areaManagerId;
        }

        public void setAreaManagerId(String areaManagerId) {
            this.areaManagerId = areaManagerId;
        }

        public String getDutyArea() {
            return dutyArea;
        }

        public void setDutyArea(String dutyArea) {
            this.dutyArea = dutyArea;
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

        public String getShiftState() {
            return shiftState;
        }

        public void setShiftState(String shiftState) {
            this.shiftState = shiftState;
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
