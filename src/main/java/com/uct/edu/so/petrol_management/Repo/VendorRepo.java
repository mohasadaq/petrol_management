package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.VendorModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface VendorRepo extends JpaRepository<VendorModal,Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE `vendor` SET `v_address`=?1\n" +
            ",`v_email`=?2,`v_name`=?3,`v_phone`=?4 WHERE v_id=?5",nativeQuery = true)
    void updateVendor(String address, String email,String name,String phone,int id);

    //UPDATE BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE vendor set v_balance= v_balance + ?1 where vendor.v_id=?2",nativeQuery = true)
    void updateVendorBalance(double amount,int id);

    //UPDATE BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE vendor set v_balance= v_balance - ?1 where vendor.v_id=?2",nativeQuery = true)
    void decreaseVendorBalance(String amount, String id);

    //UPDATE BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE vendor set v_begining_balance= v_begining_balance - ?1 where vendor.v_id=?2",nativeQuery = true)
    void decreaseVendorBeginingBalance(double amount, int id);


    //UPDATE BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE vendor set v_begining_balance= v_begining_balance + ?1 where vendor.v_id=?2",nativeQuery = true)
    void increaseVendorBeginingBalance(double amount, int id);

    // DELETE VENDOR STATEMENT
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `venderstatement` WHERE dueTo=?1 and dueToId=?2",nativeQuery = true)
    void deleteVendorStatement(String dueto,int id);
    //

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `venderstatement`(`vId`, `cashIn`, `cashOut`, `dueTo`,dueToId) \n" +
            "    VALUES (?1,?2,?3,?4,?5 )",nativeQuery = true)
    void vendorStatement(int id,double cashIn,double cashOut,String dueto,int dueToId);

    //GET VENDER SATEMENT BY PURCHASE
    @Query(value = "SELECT * FROM `venderstatement` WHERE dueTo=?1 and dueToId = ?2",nativeQuery = true)
    List<Map<String,String>> getVendorStatement(String dueto, int dueToId);

    @Query(value = "SELECT v_id, vendor.v_name,ifnull(petroll_type.p_type,0)as p_type, ifnull(purchase_petrol.pid,0)as pid,ifnull(round(((quantity*purchase_petrol.price_per_liter)- purchase_petrol.amount_paid),2),0) as amountRemaining,IFnull(purchase_petrol.quantity*purchase_petrol.price_per_liter,0)as totalAmount,ifnull(purchase_petrol.amount_paid,0)as  amountpaid,ifnull(purchase_petrol.quantity,0) as numofliter,vendor.v_begining_balance\n" +
            "FROM vendor LEFT JOIN purchase_petrol ON purchase_petrol.vnd_id=vendor.v_id\n" +
            "LEFT JOIN petroll_type on petroll_type.pt_id=purchase_petrol.potroltype_id\n" +
            "WHERE v_id IN (SELECT vnd_id FROM purchase_petrol WHERE amount_paid<\n" +
            "(quantity*price_per_liter)) AND purchase_petrol.amount_paid<(purchase_petrol.quantity*price_per_liter)\n" +
            "OR vendor.v_begining_balance>0",nativeQuery = true)
    List<Map<String,String>> vendorInLoan();



    //vendor that purchased from
    @Query(value = "SELECT * FROM `venderstatement` WHERE vId=?1",nativeQuery = true)
    List<Map<String,String>> getVendorWhoPurchasedFrom(int vendorId);






}
