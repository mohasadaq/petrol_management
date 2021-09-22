package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "payVendor")
public class PayVendorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "vendorId",updatable = false,insertable = false)
    private VendorModal vendorModal;
    private int vendorId;
    @Column(name="amount",length = 10)
    private double amount;
    @Column(name = "date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = true)
    private LocalDate date;

    public PayVendorModel() {
    }

    public PayVendorModel(int id, VendorModal vendorModal, int vendorId, double amount, LocalDate date) {
        this.id = id;
        this.vendorModal = vendorModal;
        this.vendorId = vendorId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VendorModal getVendorModal() {
        return vendorModal;
    }

    public void setVendorModal(VendorModal vendorModal) {
        this.vendorModal = vendorModal;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
