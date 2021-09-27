package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.PetrolTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PetrolTypeRepo  extends JpaRepository<PetrolTypeModel,Integer> {
//    SELECT * FROM `purchase_petrol` WHERE `potroltype_id` =?

    @Query(value = "SELECT * FROM purchase_petrol WHERE potroltype_id =?",nativeQuery = true)
    List<Map<String,String>> checkIfExists(int purchaseyId);
}
