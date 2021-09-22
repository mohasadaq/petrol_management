package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "transactionPayments")
public class TransactionPaymentsModdel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int transcId;
    private double payingAmount;
    private int cusId;
    private int paymId;

    public TransactionPaymentsModdel() {
    }

    public TransactionPaymentsModdel(int id, int transcId, double payingAmount, int cusId, int paymId) {
        this.id = id;
        this.transcId = transcId;
        this.payingAmount = payingAmount;
        this.cusId = cusId;
        this.paymId = paymId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTranscId() {
        return transcId;
    }

    public void setTranscId(int transcId) {
        this.transcId = transcId;
    }

    public double getPayingAmount() {
        return payingAmount;
    }

    public void setPayingAmount(double payingAmount) {
        this.payingAmount = payingAmount;
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getPaymId() {
        return paymId;
    }

    public void setPaymId(int paymId) {
        this.paymId = paymId;
    }
}
