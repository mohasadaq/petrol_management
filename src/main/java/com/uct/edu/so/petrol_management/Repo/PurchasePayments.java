package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.PurchasePaymentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface PurchasePayments extends JpaRepository<PurchasePaymentsModel,Integer> {
    @Query(value = "SELECT * FROM purchase_payments WHERE paym_id=?1",nativeQuery = true)
    List<Map<String,String>> getPurchasePayments(int id);


    //delete transaction payments
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM purchase_payments WHERE paym_id=?1",nativeQuery = true)
    void deletePurchasePayments(int id);

    @Query(value = "SELECT * FROM purchase_payments WHERE purchase_id=?1",nativeQuery = true)
    List<Map<String,String>> getPurchasedPayments(int purchaseId);
    //
}
