package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<PaymentModel,Integer> {
}
