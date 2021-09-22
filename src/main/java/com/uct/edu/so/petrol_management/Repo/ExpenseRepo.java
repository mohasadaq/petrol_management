package com.uct.edu.so.petrol_management.Repo;

import com.uct.edu.so.petrol_management.model.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ExpenseRepo extends JpaRepository<ExpenseModel,Integer>  {


    @Query(value = " SELECT branch.branch_name, SUM(exp_amount) totalExpense\n" +
            "    FROM expense\n" +
            "    join employee on employee.em_id=expense.employeeid\n" +
            "    join branch on branch.b_id=employee.branch_id\n" +
            "    GROUP by branch.b_id",nativeQuery = true)
    List<Map<String,String>> totalExpense ();
}
