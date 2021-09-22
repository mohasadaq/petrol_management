package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.ExpenseTypeRepo;
import com.uct.edu.so.petrol_management.model.ExpenseTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class ExpenseTypeController {
    @Autowired
    private ExpenseTypeRepo expenseRepo;
    @GetMapping("expenseType/list")
    public List<ExpenseTypeModel> list(){
        return expenseRepo.findAll();
    }
    @PostMapping("expenseType/save")
    public ExpenseTypeModel save(@RequestBody ExpenseTypeModel expenseTypeModel){
        return expenseRepo.save(expenseTypeModel);
    }
    @DeleteMapping("expenseType/delete/{expid}")
    public  boolean delete(@PathVariable int expid){
        boolean isDelateable = false;

        if(expenseRepo.checkIfExists(expid).size()<1){
            isDelateable=true;
        }

        if (isDelateable) expenseRepo.deleteById(expid);

        return isDelateable;
    }
    @GetMapping("expenseType/getOneExpense/{id}")
    public Optional<ExpenseTypeModel> getOneExpense(@PathVariable int id){

        return expenseRepo.findById(id);
    }
}
