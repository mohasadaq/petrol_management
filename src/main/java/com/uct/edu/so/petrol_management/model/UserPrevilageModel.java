package com.uct.edu.so.petrol_management.model;

import javax.persistence.*;

@Entity
@Table(name = "userprevilage")
public class UserPrevilageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  int subMenueId;
    private  int empId;

    public UserPrevilageModel() {
    }

    public UserPrevilageModel(int id, int subMenueId, int empId) {
        this.id = id;
        this.subMenueId = subMenueId;
        this.empId = empId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubMenueId() {
        return subMenueId;
    }

    public void setSubMenueId(int subMenueId) {
        this.subMenueId = subMenueId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }
}
