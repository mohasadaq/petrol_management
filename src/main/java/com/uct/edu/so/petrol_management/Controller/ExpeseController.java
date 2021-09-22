package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.ExpenseRepo;
import com.uct.edu.so.petrol_management.model.ExpenseModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ExpeseController {
    @Autowired
    private ExpenseRepo expenseRepo;
    @GetMapping("expense/list")
    public List<ExpenseModel> list(){
        return expenseRepo.findAll();
    }
    @PostMapping("expense/save")
    public ExpenseModel save(@RequestBody ExpenseModel expenseModel){
        return expenseRepo.save(expenseModel);
    }
    @DeleteMapping("expense/delete/{expid}")
    public  String delete(@PathVariable int expid){
        expenseRepo.deleteById(expid);
        return "Expense Successfully Delete"+expid;
    }
    @GetMapping("expense/getOneExpense/{id}")
    public Optional<ExpenseModel> getOneExpense(@PathVariable int id){
        return expenseRepo.findById(id);
    }

    @GetMapping("expense/totalExpense")
    public List<Map<String,String>> totalExpense(){
        return expenseRepo.totalExpense();
    }
}
