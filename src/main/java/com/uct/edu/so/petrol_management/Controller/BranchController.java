package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.BranchRepo;
import com.uct.edu.so.petrol_management.model.BranchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class BranchController {
    @Autowired
    private BranchRepo branchRepo;
    @GetMapping("branch/list")
    public List<BranchModel> list(){
        return branchRepo.findAll();
    }
    @PostMapping("branch/save")
    public BranchModel save(@RequestBody BranchModel branchModel){
        return branchRepo.save(branchModel);
    }
    @DeleteMapping("branch/delete/{bid}")
    public  String delete(@PathVariable int bid){
        branchRepo.deleteById(bid);
        return "Branch Successfully Delete"+bid;
    }
    @GetMapping("branch/getOneBranch/{id}")
    public Optional<BranchModel> getOneBranch(@PathVariable int id){
        return branchRepo.findById(id);
    }
}
