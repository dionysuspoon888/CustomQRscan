package com.example.customqrscan;

/**
 * Created by D on 4/13/2018.
 */

public class JsonItem2 {
    private String quota;
    private String location;
    private String DrID;
    private String voucher;
    public JsonItem2(String voucher,String DrID,String location,String quota) {

        this.quota= quota;
        this.location = location;
        this.DrID = DrID;
        this.voucher = voucher;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDrID() {
        return DrID;
    }

    public void setDrID(String drID) {
        DrID = drID;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
