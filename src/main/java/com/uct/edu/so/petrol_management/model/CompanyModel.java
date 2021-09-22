package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CompanyDetails")
public class CompanyModel {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int cmpid;
    private String cmpname;
    private String cmplocation;
    private String cmpcity;
    private String cmpfooter;
    private Date cmpdate;
    private String cmpphone;


    public CompanyModel() {
    }

    public CompanyModel(int cmpid, String cmpname, String cmplocation, String cmpcity, String cmpfooter, Date cmpdate, String cmpphone) {
        this.cmpid = cmpid;
        this.cmpname = cmpname;
        this.cmplocation = cmplocation;
        this.cmpcity = cmpcity;
        this.cmpfooter = cmpfooter;
        this.cmpdate = cmpdate;
        this.cmpphone = cmpphone;
    }

    public int getCmpid() {
        return cmpid;
    }

    public void setCmpid(int cmpid) {
        this.cmpid = cmpid;
    }

    public String getCmpname() {
        return cmpname;
    }

    public void setCmpname(String cmpname) {
        this.cmpname = cmpname;
    }

    public String getCmplocation() {
        return cmplocation;
    }

    public void setCmplocation(String cmplocation) {
        this.cmplocation = cmplocation;
    }

    public String getCmpcity() {
        return cmpcity;
    }

    public void setCmpcity(String cmpcity) {
        this.cmpcity = cmpcity;
    }

    public String getCmpfooter() {
        return cmpfooter;
    }

    public void setCmpfooter(String cmpfooter) {
        this.cmpfooter = cmpfooter;
    }

    public Date getCmpdate() {
        return cmpdate;
    }

    public void setCmpdate(Date cmpdate) {
        this.cmpdate = cmpdate;
    }

    public String getCmpphone() {
        return cmpphone;
    }

    public void setCmppone(String cmpphone) {
        this.cmpphone = cmpphone;
    }
}
