package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meterReading")
public class MeterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mtrrdId;
    @Column(length = 20)
    private double startReading;
    @Column(length = 20)
    private double endReading;
    private double pricePerltrId;
    @Temporal(TemporalType.DATE)
    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",  nullable =true)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "petroltypeId",updatable = false,insertable = false)
    private PetrolTypeModel petrolTypeModel;
    private int petroltypeId;

    @ManyToOne
    @JoinColumn(name = "empId",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int empId;

    public MeterReading() {
    }

    public MeterReading(int mtrrdId, double startReading, double endReading, double pricePerltrId, Date date, PetrolTypeModel petrolTypeModel, int petroltypeId, EmployeeModel employeeModel, int empId) {
        this.mtrrdId = mtrrdId;
        this.startReading = startReading;
        this.endReading = endReading;
        this.pricePerltrId = pricePerltrId;
        this.date = date;
        this.petrolTypeModel = petrolTypeModel;
        this.petroltypeId = petroltypeId;
        this.employeeModel = employeeModel;
        this.empId = empId;
    }

    public int getMtrrdId() {
        return mtrrdId;
    }

    public void setMtrrdId(int mtrrdId) {
        this.mtrrdId = mtrrdId;
    }

    public double getStartReading() {
        return startReading;
    }

    public void setStartReading(double startReading) {
        this.startReading = startReading;
    }

    public double getEndReading() {
        return endReading;
    }

    public void setEndReading(double endReading) {
        this.endReading = endReading;
    }

    public double getPricePerltrId() {
        return pricePerltrId;
    }

    public void setPricePerltrId(double pricePerltrId) {
        this.pricePerltrId = pricePerltrId;
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

    public int getPetroltypeId() {
        return petroltypeId;
    }

    public void setPetroltypeId(int petroltypeId) {
        this.petroltypeId = petroltypeId;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
