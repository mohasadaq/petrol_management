package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "petrollType")
public class PetrolTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int ptId;
    private String p_type;

    public PetrolTypeModel() {
    }

    public PetrolTypeModel(int ptId, String p_type) {
        this.ptId = ptId;
        this.p_type = p_type;
    }

    public int getPtId() {
        return ptId;
    }

    public void setPtId(int ptId) {
        this.ptId = ptId;
    }

    public String getP_type() {
        return p_type;
    }

    public void setP_type(String p_type) {
        this.p_type = p_type;
    }
}
