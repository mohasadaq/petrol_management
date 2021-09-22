package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendor")
public class VendorModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vId;
    private String vName;
    private String vAddress;
    private String vEmail;
    private String vPhone;
    private double vBalance;
    private double vBeginingBalance;
    private Date date = new Date(System.currentTimeMillis());
    @ManyToOne
    @JoinColumn(name = "employeeid",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int  employeeid;

    public VendorModal() {
    }
    public VendorModal(int vId, String vName, String vAddress, String vEmail, String vPhone, double vBalance, double vBeginingBalance, Date date, EmployeeModel employeeModel, int employeeid) {
        this.vId = vId;
        this.vName = vName;
        this.vAddress = vAddress;
        this.vEmail = vEmail;
        this.vPhone = vPhone;
        this.vBalance = vBalance;
        this.vBeginingBalance = vBeginingBalance;
        this.date = date;
        this.employeeModel = employeeModel;
        this.employeeid = employeeid;
    }

    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getvAddress() {
        return vAddress;
    }

    public void setvAddress(String vAddress) {
        this.vAddress = vAddress;
    }

    public String getvEmail() {
        return vEmail;
    }

    public void setvEmail(String vEmail) {
        this.vEmail = vEmail;
    }

    public String getvPhone() {
        return vPhone;
    }

    public void setvPhone(String vPhone) {
        this.vPhone = vPhone;
    }

    public double getvBalance() {
        return vBalance;
    }

    public void setvBalance(double vBalance) {
        this.vBalance = vBalance;
    }

    public double getvBeginingBalance() {
        return vBeginingBalance;
    }

    public void setvBeginingBalance(double vBeginingBalance) {
        this.vBeginingBalance = vBeginingBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
}
