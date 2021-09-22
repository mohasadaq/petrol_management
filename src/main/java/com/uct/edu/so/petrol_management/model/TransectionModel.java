package com.uct.edu.so.petrol_management.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transection")
public class TransectionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trnsId;
    private double numofliter;
    private double priceperLiter;
    private int status;
    private double amountpaid;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",  nullable = true)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "petroltyid",updatable = false,insertable = false)
    private PetrolTypeModel petrolTypeModel;
    private int petroltyid;

    @ManyToOne
    @JoinColumn(name = "custmrid",updatable = false,insertable = false)
    private CustomerModel customerModel;
    private int custmrid;

    @ManyToOne
    @JoinColumn(name = "employeeid",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int employeeid;


    public TransectionModel() {
    }

    public TransectionModel(int trnsId, double numofliter, double priceperLiter, int status, double amountpaid, Date date, PetrolTypeModel petrolTypeModel, int petroltyid, CustomerModel customerModel, int custmrid, EmployeeModel employeeModel, int employeeid) {
        this.trnsId = trnsId;
        this.numofliter = numofliter;
        this.priceperLiter = priceperLiter;
        this.status = status;
        this.amountpaid = amountpaid;
        this.date = date;
        this.petrolTypeModel = petrolTypeModel;
        this.petroltyid = petroltyid;
        this.customerModel = customerModel;
        this.custmrid = custmrid;
        this.employeeModel = employeeModel;
        this.employeeid = employeeid;
    }

    public int getTrnsId() {
        return trnsId;
    }

    public void setTrnsId(int trnsId) {
        this.trnsId = trnsId;
    }

    public double getNumofliter() {
        return numofliter;
    }

    public void setNumofliter(double numofliter) {
        this.numofliter = numofliter;
    }

    public double getPriceperLiter() {
        return priceperLiter;
    }

    public void setPriceperLiter(double priceperLiter) {
        this.priceperLiter = priceperLiter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getAmountpaid() {
        return amountpaid;
    }

    public void setAmountpaid(double amountpaid) {
        this.amountpaid = amountpaid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PetrolTypeModel getPetrolTypeModel() {
        return petrolTypeModel;
    }

    public void setPetrolTypeModel(PetrolTypeModel petrolTypeModel) {
        this.petrolTypeModel = petrolTypeModel;
    }

    public int getPetroltyid() {
        return petroltyid;
    }

    public void setPetroltyid(int petroltyid) {
        this.petroltyid = petroltyid;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public int getCustmrid() {
        return custmrid;
    }

    public void setCustmrid(int custmrid) {
        this.custmrid = custmrid;
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
