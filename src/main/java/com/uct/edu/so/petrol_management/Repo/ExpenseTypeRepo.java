package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.ExpenseModel;
import com.uct.edu.so.petrol_management.model.ExpenseTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ExpenseTypeRepo extends JpaRepository<ExpenseTypeModel , Integer> {

    @Query(value = "SELECT * FROM `expense` WHERE `exp_type`=?",nativeQuery = true)
    List<Map<String,String>> checkIfExists(int expencetyId);
}
