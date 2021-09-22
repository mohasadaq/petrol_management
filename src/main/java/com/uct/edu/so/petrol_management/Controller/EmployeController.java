package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.EmployeeRepo;
import com.uct.edu.so.petrol_management.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class EmployeController {
    @Autowired
    private EmployeeRepo employeeRepo;
    @GetMapping("Employee/list")
    public List<EmployeeModel> list(){
        return employeeRepo.findAll();
    }
    @PostMapping("Employee/save")
    public EmployeeModel save (@RequestBody EmployeeModel employeeModel){
        return employeeRepo.save(employeeModel);
    }
    @DeleteMapping("Employee/delete/{emid}")
    public String delete (@PathVariable int emid){
        employeeRepo.deleteById(emid);
        return "Employee Successfully Delete "+emid;
    }
    @GetMapping("Employee/getOnEmployee/{emid}")
    public Optional<EmployeeModel> getOneEmployee(@PathVariable int emid){
        return employeeRepo.findById(emid);
    }
    //check username
    @PostMapping("Employee/checklogin")
    public  EmployeeModel checkLogin(@RequestBody EmployeeModel employeeModel){
        return employeeRepo.checkUser(employeeModel.getEmpUsername(),employeeModel.getEmpPassword());

    }


    // read menue
    @GetMapping("menue/list/{id}")
    public  List<Map<String, String>> menue(@PathVariable int id){
        return employeeRepo.menue(id);

    }

    @GetMapping("menue/All")
    public  List<Map<String, String>> findAllmenue(){
        return employeeRepo.findAllMenue();

    }
    // read menue
    @GetMapping("subMenue/list")
    public  List<Map<String, String>> subMenue(){
        return employeeRepo.subMenue();
    }

    // read menue
    @GetMapping("subMenue/All")
    public  List<Map<String, String>> findAllSubMenue(){
        return employeeRepo.findAllSubMenu();
    }
}
