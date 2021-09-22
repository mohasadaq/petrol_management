package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;
    @Column(length = 55)
    private String cname;
    @Column(length = 20)
    private String caddress;
    @Column(length = 20)
    private String cphone;
    private double balance;
    private double beginingBalance;
    @ManyToOne
    @JoinColumn(name = "employeeid",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int employeeid;



    public CustomerModel() {
    }

    public CustomerModel(int cId, String cname, String caddress, String cphone, double balance, double beginingBalance, EmployeeModel employeeModel, int resemployeeid) {
        this.cId = cId;
        this.cname = cname;
        this.caddress = caddress;
        this.cphone = cphone;
        this.balance = balance;
        this.beginingBalance = beginingBalance;
        this.employeeModel = employeeModel;
        this.employeeid = resemployeeid;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBeginingBalance() {
        return beginingBalance;
    }

    public void setBeginingBalance(double beginingBalance) {
        this.beginingBalance = beginingBalance;
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

    public void setEmployeeid(int resemployeeid) {
        this.employeeid = resemployeeid;
    }
}
