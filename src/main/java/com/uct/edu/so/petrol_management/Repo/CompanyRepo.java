package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CompanyRepo extends JpaRepository<CompanyModel , Integer> {
//    query
@Transactional
@Modifying
@Query(value = "TRUNCATE TABLE company_details",nativeQuery = true)
void cmpDetail();
}
