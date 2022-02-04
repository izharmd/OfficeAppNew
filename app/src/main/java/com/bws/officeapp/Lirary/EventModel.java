package com.bws.officeapp.Lirary;

/**
 * Created by HCL on 02-10-2016.
 */
public class EventModel {

    private String strDate;
    private String strEndDate;
    private String strStartTime;
    private String strEndTime;
    private String strName;
    private String projectStatus;
    private int image = -1;


    public EventModel(String strDate, String strEndDate, String strStartTime, String strEndTime, String strName, String projectStatus) {
        this.strDate = strDate;
        this.strEndDate = strEndDate;
        this.strStartTime = strStartTime;
        this.strEndTime = strEndTime;
        this.strName = strName;
        this.projectStatus = projectStatus;
    }

    public EventModel(String strDate,String strEndDate, String strStartTime, String strEndTime, String strName, int image,String projectStatus) {
        this.strDate = strDate;
        this.strEndDate = strEndDate;
        this.strStartTime = strStartTime;
        this.strEndTime = strEndTime;
        this.strName = strName;
        this.projectStatus = projectStatus;
        this.image = image;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }



    public String getStrStartTime() {
        return strStartTime;
    }

    public void setStrStartTime(String strStartTime) {
        this.strStartTime = strStartTime;
    }

    public String getStrEndTime() {
        return strEndTime;
    }

    public void setStrEndTime(String strEndTime) {
        this.strEndTime = strEndTime;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

}
