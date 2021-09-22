package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.PayVendorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PayVendorRepo extends JpaRepository<PayVendorModel,Integer> {

    @Query(value = "SELECT petroll_type.p_type,purchase_petrol.quantity,\n" +
            "           (purchase_petrol.quantity*purchase_petrol.price_per_liter)as amount ,\n" +
            "           purchase_payments.paying_amount,\n" +
            "           (purchase_petrol.quantity*purchase_petrol.price_per_liter)-purchase_petrol.amount_paid as remaining\n" +
            "           FROM purchase_petrol\n" +
            "           INNER JOIN petroll_type  on purchase_petrol.potroltype_id=petroll_type.pt_id\n" +
            "           INNER JOIN purchase_payments on purchase_payments.purchase_id = purchase_petrol.pid\n" +
            "           WHERE purchase_payments.paym_id=?1",nativeQuery = true)
    List<Map<String,String>> paymentDetails(int paymentId);
}
