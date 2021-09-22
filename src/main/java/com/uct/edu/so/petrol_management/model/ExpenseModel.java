package com.uct.edu.so.petrol_management.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expense")
public class ExpenseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expId;
    @Column(length = 20)
    private double expAmount;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "expType",updatable = false,insertable = false)
    private ExpenseTypeModel expenseTypeModel;
    private int  expType;
    @ManyToOne
    @JoinColumn(name = "employeeid",updatable = false,insertable = false)
    private EmployeeModel employeeModel;
    private int  employeeid;

    public ExpenseModel() {
    }

    public ExpenseModel(int expId, double expAmount, Date date, ExpenseTypeModel expenseTypeModel, int expType, EmployeeModel employeeModel, int employeeid) {
        this.expId = expId;
        this.expAmount = expAmount;
        this.date = date;
        this.expenseTypeModel = expenseTypeModel;
        this.expType = expType;
        this.employeeModel = employeeModel;
        this.employeeid = employeeid;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public double getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(double expAmount) {
        this.expAmount = expAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ExpenseTypeModel getExpenseTypeModel() {
        return expenseTypeModel;
    }

    public void setExpenseTypeModel(ExpenseTypeModel expenseTypeModel) {
        this.expenseTypeModel = expenseTypeModel;
    }

    public int getExpType() {
        return expType;
    }

    public void setExpType(int expType) {
        this.expType = expType;
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
