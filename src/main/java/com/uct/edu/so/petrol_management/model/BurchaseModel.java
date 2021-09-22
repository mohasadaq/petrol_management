package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "purchasePetrol")
public class BurchaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    @Column(length = 20)
    private double pricePerLiter;
    @Column(length = 10)
    private double quantity;
    @Column(length = 10)
    private double amountPaid;
    @ManyToOne
    @JoinColumn(name = "potroltype_id",insertable = false,updatable = false )
    private PetrolTypeModel petrolTypeModel;
    private int potroltype_id;
    @ManyToOne
    @JoinColumn(name = "vndId",insertable = false,updatable = false )
    private VendorModal vendorModal;
    private int vndId;
    @ManyToOne
    @JoinColumn(name = "employeeid",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int  employeeid;

    public BurchaseModel() {
    }

    public BurchaseModel(int pid, double pricePerLiter, double quantity, double amountPaid, PetrolTypeModel petrolTypeModel, int potroltype_id, VendorModal vendorModal, int vndId, EmployeeModel employeeModel, int employeeid) {
        this.pid = pid;
        this.pricePerLiter = pricePerLiter;
        this.quantity = quantity;
        this.amountPaid = amountPaid;
        this.petrolTypeModel = petrolTypeModel;
        this.potroltype_id = potroltype_id;
        this.vendorModal = vendorModal;
        this.vndId = vndId;
        this.employeeModel = employeeModel;
        this.employeeid = employeeid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public PetrolTypeModel getPetrolTypeModel() {
        return petrolTypeModel;
    }

    public void setPetrolTypeModel(PetrolTypeModel petrolTypeModel) {
        this.petrolTypeModel = petrolTypeModel;
    }

    public int getPotroltype_id() {
        return potroltype_id;
    }

    public void setPotroltype_id(int potroltype_id) {
        this.potroltype_id = potroltype_id;
    }

    public VendorModal getVendorModal() {
        return vendorModal;
    }

    public void setVendorModal(VendorModal vendorModal) {
        this.vendorModal = vendorModal;
    }

    public int getVndId() {
        return vndId;
    }

    public void setVndId(int vndId) {
        this.vndId = vndId;
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
