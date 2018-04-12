package com.example.customqrscan;

/**
 * Created by D on 4/9/2018.
 */


public class JsonItem {
    private String voucher;
    private String DrID;
    private String location;
    private String quota;

    public JsonItem(String voucher, String DrID,String location,String quota) {
        this.voucher = voucher;
        this.DrID = DrID;
        this.location = location;
        this.quota= quota;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getDrID() {
        return DrID;
    }

    public void setDrID(String drID) {
        DrID = drID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }
}