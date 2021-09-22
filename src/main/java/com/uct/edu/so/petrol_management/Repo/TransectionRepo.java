package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.MeterReading;
import com.uct.edu.so.petrol_management.model.TransectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface TransectionRepo extends JpaRepository<TransectionModel,Integer> {
    @Query(value = "SELECT branch.branch_name, SUM(`numofliter`*`priceper_liter`) Total_Amount \n" +
            "FROM `transection`\n" +
            "join employee on employee.em_id=transection.employeeid\n" +
            "join branch on branch.b_id=employee.branch_id\n" +
            "GROUP by branch.b_id;",nativeQuery = true)
    List<Map<String,String>> total_Amount ();
    @Query(value = "SELECT branch.branch_name, SUM(`numofliter`) numOfLiterSold\n" +
            "            FROM `transection`\n" +
            "            join employee on employee.em_id=transection.employeeid\n" +
            "            join branch on branch.b_id=employee.branch_id\n" +
            "            GROUP by branch.b_id",nativeQuery = true)
    List<Map<String,String>> N_of_l_sold ();

    //how manay liters are reaming
    @Query(value = "SELECT pt.pt_id, pt.p_type,(ifnull(br.quantity,0)- ifnull(tr.numofliter,0)) as CURRENTliters\n" +
            "            FROM petroll_type pt \n" +
            "            LEFT JOIN(\n" +
            "            SELECT petroltyid,SUM(numofliter) as numofliter from transection GROUP by 1 \n" +
            "            )tr on tr.petroltyid=pt.pt_id\n" +
            "            left join (\n" +
            "            SELECT potroltype_id,SUM(quantity)as quantity from purchase_petrol \n" +
            "             GROUP by 1\n" +
            "            )br on br.potroltype_id=pt.pt_id\n" +
            "            ORDER by CURRENTliters DESC",nativeQuery = true)
    List<Map<String,String>> numOfLiterRemaining ();


    //how manay liters are reaming
    @Query(value = "SELECT pt.pt_id, pt.p_type,(ifnull(br.quantity,0)- ifnull(tr.numofliter,0)) as CURRENTliters\n" +
            "                FROM petroll_type pt \n" +
            "                  LEFT JOIN(\n" +
            "              SELECT petroltyid,SUM(numofliter) as numofliter from transection\n" +
            "               )tr on tr.petroltyid=pt.pt_id\n" +
            "              left join (\n" +
            "             SELECT potroltype_id,SUM(quantity)as quantity from purchase_petrol \n" +
            "                    GROUP by 1\n" +
            "              )br on br.potroltype_id=pt.pt_id\n" +
            "               ORDER by CURRENTliters DESC\n" +
            "               WHERE petroll_type.pt_id=?1 ",nativeQuery = true)
    List<Map<String,String>> numOfLiterRemainingByid (int petrolTypeId);


    @Transactional
    @Modifying
    @Query(value = "update  transection t set t.amountpaid= (t.amountpaid + ?1) where t.trns_id=?2",nativeQuery = true)
    void payAount(double amount, int id);


    //decrease payed amount
    @Transactional
    @Modifying
    @Query(value = "update  transection t set t.amountpaid= (t.amountpaid - ?1) where t.trns_id=?2",nativeQuery = true)
    void decreaseAmount(double amount, int id);

    @Transactional
    @Modifying
    @Query(value = "update  transection t set t.amountpaid= ?1 where t.l_id=?2",nativeQuery = true)
    void payAount(double amount, int id,int pymID);


    // sales report
    @Query(value = "SELECT * FROM `salesreport`",nativeQuery = true)
   List<Map<String,Integer>> salesReport();

    //GET TRANSACTION THAT PAYED
    @Query(value = "SELECT * FROM `transaction_payments` WHERE transc_id=?1",nativeQuery = true)
    List<Map<String,Integer>> getTransactionThatPayed(int transactionId);

    //GET meter reading
    @Query(value = "SELECT * FROM `transection` WHERE date(date)=CURRENT_DATE",nativeQuery = true)
    List<TransectionModel> getList();
    //GET meter transection
    @Query(value = "SELECT * FROM `transection` WHERE date(date) BETWEEN ?1 and ?2",nativeQuery = true)
    List<TransectionModel> getListbydate(String datefrom , String dateto);
    //

}
