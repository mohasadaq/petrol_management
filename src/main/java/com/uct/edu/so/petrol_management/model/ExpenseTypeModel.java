package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "expensetype")
public class ExpenseTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expid;
    private String exptype;

    public ExpenseTypeModel() {
    }

    public ExpenseTypeModel(int expid, String exptype) {
        this.expid = expid;
        this.exptype = exptype;
    }

    public int getExpid() {
        return expid;
    }

    public void setExpid(int expid) {
        this.expid = expid;
    }

    public String getExptype() {
        return exptype;
    }

    public void setExptype(String exptype) {
        this.exptype = exptype;
    }
}

