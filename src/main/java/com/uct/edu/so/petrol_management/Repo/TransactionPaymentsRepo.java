package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.TransactionPaymentsModdel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface TransactionPaymentsRepo extends JpaRepository<TransactionPaymentsModdel,Integer> {

    @Query(value = "SELECT * FROM `transaction_payments` WHERE `paym_id`=?1",nativeQuery = true)
    List<Map<String,String>> getTransactionPayments(int id);


    //delete transaction payments
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `transaction_payments` WHERE `paym_id`=?1",nativeQuery = true)
    void deleteTransactionPayments(int id);

    // payment details
    @Query(value = "SELECT petroll_type.p_type,transection.numofliter,\n" +
            "    (transection.numofliter*transection.priceper_liter)as amount , \n" +
            "    transaction_payments.paying_amount,\n" +
            "    (transection.numofliter*transection.priceper_liter)-transection.amountpaid as remaining\n" +
            "    FROM transection\n" +
            "    INNER JOIN petroll_type  on transection.petroltyid=petroll_type.pt_id\n" +
            "    INNER JOIN transaction_payments on transaction_payments.transc_id = transection.trns_id\n" +
            "    WHERE transaction_payments.paym_id=?1",nativeQuery = true)
    List<Map<String,String>> paymentDetails(int paymentId);
}
