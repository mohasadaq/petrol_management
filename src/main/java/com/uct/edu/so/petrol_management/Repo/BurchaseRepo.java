package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.BurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BurchaseRepo extends JpaRepository<BurchaseModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update  purchase_petrol p set p.amount_paid=amount_paid + ?1 where p.pid=?2",nativeQuery = true)
    void payAmount(double amount, int id);


    @Transactional
    @Modifying
    @Query(value = "update  purchase_petrol p set p.amount_paid=amount_paid - ?1 where p.pid=?2",nativeQuery = true)
    void decreaseAmount(double amount, int id);

//    fuel cost


    @Query(value = "SELECT sum(`price_per_liter`*`quantity`)as cost FROM `purchase_petrol`",nativeQuery = true)
    Double fuelCost();
}
