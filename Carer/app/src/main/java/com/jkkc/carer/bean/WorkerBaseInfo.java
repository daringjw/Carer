package com.jkkc.carer.bean;

/**
 * Created by Guan on 2018/5/31.
 */

public class WorkerBaseInfo {


    /**
     * code : success
     * msg : 更新成功
     * workerBaseInfo : {"activeStatus":"在职","addressCity":"北京市","addressCounty":"海淀区","addressDetail":"彩和坊路8号","addressProvince":"北京","birthPlace":"北京市","birthday":"1991-05-03","bloodType":"B","classes":"","contactsName":"朱之用","contactsPhone":"15656566565","duty":"中队长","education":"本科","email":"","entryTime":"2018-05-28 10:17:42.0","firstDept":"服务部","gender":"男","gpsId":"","homePhone":"","id":243,"identificationNum":"110101199105031112","joinTime":"2018-05-28","nationality":" 汉族","otherNum1":"","otherNum2":"","peopleId":"A00001","peopleImage":"","peopleName":"张三","phoneCardId":"","phoneNum":"15999999999","remarks":"","secondDept":"","signType":"签约","startTime":"","wifiId":""}
     */

    private String code;
    private String msg;
    private WorkerBaseInfoBean workerBaseInfo;

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

    public WorkerBaseInfoBean getWorkerBaseInfo() {
        return workerBaseInfo;
    }

    public void setWorkerBaseInfo(WorkerBaseInfoBean workerBaseInfo) {
        this.workerBaseInfo = workerBaseInfo;
    }

    public static class WorkerBaseInfoBean {
        /**
         * activeStatus : 在职
         * addressCity : 北京市
         * addressCounty : 海淀区
         * addressDetail : 彩和坊路8号
         * addressProvince : 北京
         * birthPlace : 北京市
         * birthday : 1991-05-03
         * bloodType : B
         * classes :
         * contactsName : 朱之用
         * contactsPhone : 15656566565
         * duty : 中队长
         * education : 本科
         * email :
         * entryTime : 2018-05-28 10:17:42.0
         * firstDept : 服务部
         * gender : 男
         * gpsId :
         * homePhone :
         * id : 243
         * identificationNum : 110101199105031112
         * joinTime : 2018-05-28
         * nationality :  汉族
         * otherNum1 :
         * otherNum2 :
         * peopleId : A00001
         * peopleImage :
         * peopleName : 张三
         * phoneCardId :
         * phoneNum : 15999999999
         * remarks :
         * secondDept :
         * signType : 签约
         * startTime :
         * wifiId :
         */

        private String activeStatus;
        private String addressCity;
        private String addressCounty;
        private String addressDetail;
        private String addressProvince;
        private String birthPlace;
        private String birthday;
        private String bloodType;
        private String classes;
        private String contactsName;
        private String contactsPhone;
        private String duty;
        private String education;
        private String email;
        private String entryTime;
        private String firstDept;
        private String gender;
        private String gpsId;
        private String homePhone;
        private int id;
        private String identificationNum;
        private String joinTime;
        private String nationality;
        private String otherNum1;
        private String otherNum2;
        private String peopleId;
        private String peopleImage;
        private String peopleName;
        private String phoneCardId;
        private String phoneNum;
        private String remarks;
        private String secondDept;
        private String signType;
        private String startTime;
        private String wifiId;

        public String getActiveStatus() {
            return activeStatus;
        }

        public void setActiveStatus(String activeStatus) {
            this.activeStatus = activeStatus;
        }

        public String getAddressCity() {
            return addressCity;
        }

        public void setAddressCity(String addressCity) {
            this.addressCity = addressCity;
        }

        public String getAddressCounty() {
            return addressCounty;
        }

        public void setAddressCounty(String addressCounty) {
            this.addressCounty = addressCounty;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }

        public String getAddressProvince() {
            return addressProvince;
        }

        public void setAddressProvince(String addressProvince) {
            this.addressProvince = addressProvince;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getClasses() {
            return classes;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getContactsName() {
            return contactsName;
        }

        public void setContactsName(String contactsName) {
            this.contactsName = contactsName;
        }

        public String getContactsPhone() {
            return contactsPhone;
        }

        public void setContactsPhone(String contactsPhone) {
            this.contactsPhone = contactsPhone;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEntryTime() {
            return entryTime;
        }

        public void setEntryTime(String entryTime) {
            this.entryTime = entryTime;
        }

        public String getFirstDept() {
            return firstDept;
        }

        public void setFirstDept(String firstDept) {
            this.firstDept = firstDept;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGpsId() {
            return gpsId;
        }

        public void setGpsId(String gpsId) {
            this.gpsId = gpsId;
        }

        public String getHomePhone() {
            return homePhone;
        }

        public void setHomePhone(String homePhone) {
            this.homePhone = homePhone;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdentificationNum() {
            return identificationNum;
        }

        public void setIdentificationNum(String identificationNum) {
            this.identificationNum = identificationNum;
        }

        public String getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(String joinTime) {
            this.joinTime = joinTime;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getOtherNum1() {
            return otherNum1;
        }

        public void setOtherNum1(String otherNum1) {
            this.otherNum1 = otherNum1;
        }

        public String getOtherNum2() {
            return otherNum2;
        }

        public void setOtherNum2(String otherNum2) {
            this.otherNum2 = otherNum2;
        }

        public String getPeopleId() {
            return peopleId;
        }

        public void setPeopleId(String peopleId) {
            this.peopleId = peopleId;
        }

        public String getPeopleImage() {
            return peopleImage;
        }

        public void setPeopleImage(String peopleImage) {
            this.peopleImage = peopleImage;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public String getPhoneCardId() {
            return phoneCardId;
        }

        public void setPhoneCardId(String phoneCardId) {
            this.phoneCardId = phoneCardId;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSecondDept() {
            return secondDept;
        }

        public void setSecondDept(String secondDept) {
            this.secondDept = secondDept;
        }

        public String getSignType() {
            return signType;
        }

        public void setSignType(String signType) {
            this.signType = signType;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getWifiId() {
            return wifiId;
        }

        public void setWifiId(String wifiId) {
            this.wifiId = wifiId;
        }
    }
}
