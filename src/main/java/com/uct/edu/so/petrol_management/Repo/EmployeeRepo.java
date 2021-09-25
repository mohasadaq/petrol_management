package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel ,Integer> {
    @Query(value = "select  *from employee e where e.emp_username=:username and e.emp_password=:password",nativeQuery = true)
    EmployeeModel checkUser(@Param("username") String username, @Param("password") String password );


    //menues
    @Query(value = "SELECT * FROM menue \n" +
            "WHERE menue.id in (SELECT DISTINCT sumenue.menueId FROM sumenue\n" +
            "INNER JOIN userprevilage on userprevilage.sub_menue_id=sumenue.id \n" +
            "WHERE sumenue.id in (SELECT userprevilage.sub_menue_id FROM userprevilage WHERE userprevilage.emp_id=?1))",nativeQuery = true)
    List<Map<String,String>> menue(int id);

    @Query(value = "SELECT * FROM menue",nativeQuery = true)
    List<Map<String,String>> findAllMenue();

    //

    @Query(value = "SELECT DISTINCT sumenue.id,sumenue.text,sumenue.link,sumenue.menueId,sumenue.icon,userprevilage.emp_id FROM sumenue\n" +
            "            INNER JOIN userprevilage on userprevilage.sub_menue_id=sumenue.id\n" +
            "            WHERE sumenue.id in (SELECT userprevilage.sub_menue_id FROM userprevilage) order by sumenue.menueId asc",nativeQuery = true)
    List<Map<String,String>> subMenue();

    @Query(value = "SELECT * FROM sumenue",nativeQuery = true)
    List<Map<String,String>> findAllSubMenu();
    //

}
