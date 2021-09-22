package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "branch")
public class BranchModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int bId;
    @Column(length = 40)
    private  String branchName;
    @Column(length = 40)
    private  String brancLocation;

    public BranchModel() {
    }

    public BranchModel(int bId, String branchName, String brancLocation) {
        this.bId = bId;
        this.branchName = branchName;
        this.brancLocation = brancLocation;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBrancLocation() {
        return brancLocation;
    }

    public void setBrancLocation(String brancLocation) {
        this.brancLocation = brancLocation;
    }
}
