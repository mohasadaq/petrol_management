package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emId;
    @Column(length = 55)
    private String empName;
    @Column(length = 20)
    private String empTell;
    @Column(length = 20)
    private String empAddress;
    @Column(length = 20)
    private String empGender;
    @Column(length = 20)
    private String empUsername;
    @Column(length = 20)
    private String empPassword;
    @Column(length = 15)
    private String usertype;
    @ManyToOne
    @JoinColumn(name = "branchId", insertable = false , updatable = false)
    private BranchModel branch;
    private int branchId;


    public EmployeeModel() {
    }

    public EmployeeModel(int emId, String empName, String empTell, String empAddress, String empGender, String empUsername, String empPassword, String usertype, BranchModel branch, int branchId) {
        this.emId = emId;
        this.empName = empName;
        this.empTell = empTell;
        this.empAddress = empAddress;
        this.empGender = empGender;
        this.empUsername = empUsername;
        this.empPassword = empPassword;
        this.usertype = usertype;
        this.branch = branch;
        this.branchId = branchId;
    }

    public int getEmId() {
        return emId;
    }

    public void setEmId(int emId) {
        this.emId = emId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpTell() {
        return empTell;
    }

    public void setEmpTell(String empTell) {
        this.empTell = empTell;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpUsername() {
        return empUsername;
    }

    public void setEmpUsername(String empUsername) {
        this.empUsername = empUsername;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public BranchModel getBranch() {
        return branch;
    }

    public void setBranch(BranchModel branch) {
        this.branch = branch;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}


