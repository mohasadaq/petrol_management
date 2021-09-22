package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "payment")
public class PaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int pId;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "cusId",updatable = false,insertable = false)
    private CustomerModel customerModel;
    private int cusId;
    @Temporal(TemporalType.DATE)
    private Date date = new Date(System.currentTimeMillis());
    private  int trId;
    public PaymentModel() {
    }

    public PaymentModel(int pId, double amount, CustomerModel customerModel, int cusId, Date date, int trId) {
        this.pId = pId;
        this.amount = amount;
        this.customerModel = customerModel;
        this.cusId = cusId;
        this.date = date;
        this.trId = trId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTrId() {
        return trId;
    }

    public void setTrId(int trId) {
        this.trId = trId;
    }
}
