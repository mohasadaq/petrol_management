package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.MeterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MeterReadingRepo extends JpaRepository <MeterReading,Integer>{

    //Get LAST READING
    @Query(value = "SELECT max(end_reading)as end_reading,employee.em_id,employee.branch_id FROM `meter_reading` \n" +
            "JOIN employee on employee.em_id=meter_reading.emp_id\n" +
            "JOIN branch on branch.b_id=employee.branch_id\n" +
            "GROUP by branch.b_id",nativeQuery = true)
    List<Map<String,String>> lastReading();

    //GET meter reading
    @Query(value = "SELECT * FROM `meter_reading` WHERE date(date)=CURRENT_DATE",nativeQuery = true)
    List<MeterReading> getList();
    //GET meter reading
    @Query(value = "SELECT * FROM `meter_reading` WHERE date(date) BETWEEN ?1 and ?2",nativeQuery = true)
    List<MeterReading> getListbydate(String datefrom , String dateto);
    //

}
