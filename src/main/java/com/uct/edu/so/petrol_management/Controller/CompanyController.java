package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.CompanyRepo;
import com.uct.edu.so.petrol_management.model.CompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CompanyController {
    @Autowired
    private CompanyRepo compantRepo;
    @GetMapping("company/list")
    public List<CompanyModel> list(){
        return compantRepo.findAll();
    }

    @PostMapping("company/save")
    public CompanyModel save(@RequestBody CompanyModel companyModel){
        compantRepo.cmpDetail();
        return compantRepo.save(companyModel);

    }
    @DeleteMapping("company/delete/{cmpid}")
    public  String delete(@PathVariable int cmpid){
        compantRepo.deleteById(cmpid);
        return "Company Successfully Delete"+cmpid;
    }
    @GetMapping("company/getOneCompany/{id}")
    public Optional<CompanyModel> getOneCompany(@PathVariable int id){
        return compantRepo.findById(id);
    }

}
