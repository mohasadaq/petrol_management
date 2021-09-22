package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.UserPrevilageModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserPrevilageRepo extends CrudRepository<UserPrevilageModel,Integer> {
    //delete userprevilage
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `userprevilage` WHERE emp_id=?1",nativeQuery = true)
    void deletesavePrivilage(int empId);
}
