package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<BranchModel , Integer> {
}
