package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "purchasePayments")
public class PurchasePaymentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int purchaseId;
    private double payingAmount;
    private int vendorId;
    private int paymId;

    public PurchasePaymentsModel() {
    }

    public PurchasePaymentsModel(int id, int purchaseId, double payingAmount, int vendorId, int paymId) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.payingAmount = payingAmount;
        this.vendorId = vendorId;
        this.paymId = paymId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public double getPayingAmount() {
        return payingAmount;
    }

    public void setPayingAmount(double payingAmount) {
        this.payingAmount = payingAmount;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getPaymId() {
        return paymId;
    }

    public void setPaymId(int paymId) {
        this.paymId = paymId;
    }
}
