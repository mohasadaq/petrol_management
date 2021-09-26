package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {

    @Query(value = "SELECT c_id, cname,ifnull(petroll_type.p_type,0)as p_type, ifnull(transection.trns_id,0)as trns_id,ifnull(round(((numofliter*priceper_liter)- transection.amountpaid),2),0) as amountRemaining,\n" +
            "    IFnull(transection.numofliter*transection.priceper_liter,0)as totalAmount,ifnull(transection.amountpaid,0)as  amountpaid,ifnull(transection.numofliter,0) as numofliter,customer.begining_balance\n" +
            "            FROM customer LEFT JOIN transection ON transection.custmrid=customer.c_id\n" +
            "            LEFT JOIN petroll_type on petroll_type.pt_id=transection.petroltyid\n" +
            "            WHERE c_id IN (SELECT custmrid FROM transection WHERE amountpaid<\n" +
            "            (numofliter*priceper_liter)) AND transection.amountpaid< (numofliter*priceper_liter)\n" +
            "            OR customer.begining_balance>0",nativeQuery = true)
          List<Map<String,Integer>> customerInLoan ();

    // REGISTOR CUSTOMER STATEMENT
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO customerstatement(cstId, cashIn, cashOut, dueTo, dueToId) \n" +
            "VALUES (?1,?2,?3,?4,?5)",nativeQuery = true)
    void customerStatement(int id,double cashIn,double cashOut,String dueto,int dueToId);

    // INCREASE CUSTOMER BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer SET balance=balance + ?1 WHERE c_id=?2",nativeQuery = true)
    void increaseCustomerBalance(double cashIn,int id);
    //

    // DECREASE CUSTOMER BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer SET balance=balance - ?1 WHERE c_id=?2",nativeQuery = true)
    void decreaseCustomerBalance(double cashIn, int id);

    // DECREASE CUSTOMER beginig BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer SET begining_balance=begining_balance - ?1 WHERE c_id=?2",nativeQuery = true)
    void decreaseCustomerBeginigBalance(double cashout, int id);

    // Increase CUSTOMER beginig BALANCE
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer SET begining_balance=begining_balance + ?1 WHERE c_id=?2",nativeQuery = true)
    void increaseCustomerBeginigBalance(double cashout, int id);

    //GET CUSTOMER STATEMENT
    @Query(value = "SELECT * FROM customerstatement WHERE dueTo=?1 and dueToId = ?2",nativeQuery = true)
    List<Map<String,String>> getCustomerStatement(String dueto, int dueToId);

    // DELETE CUSTOMER STATEMENT
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM customerstatement WHERE dueTo=?1 and dueToId=?2",nativeQuery = true)
    void deleteCustomerStatement(String dueto,int id);



    //UPDATE CUSTOMER
    @Transactional
    @Modifying
    @Query(value = "UPDATE customer SET caddress=?1,cname=?2,cphone=?3 WHERE c_id=?4",nativeQuery = true)
    void updateCustomer(String address, String name,String phone, int id);
    //


    //GET CUSTOMER IN TRANSACTION
    @Query(value = "SELECT * FROM transection WHERE custmrid=?1",nativeQuery = true)
    List<Map<String,String>> getCustomerInTransaction(int customerId);
    //



}
