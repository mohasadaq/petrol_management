package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.VendorRepo;
import com.uct.edu.so.petrol_management.model.VendorModal;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class VendorController {
    @Autowired
    private VendorRepo vendorRepo;
    @GetMapping("vendor/list")
    public List<VendorModal> list(){
        return vendorRepo.findAll();
    }
    @PostMapping("vendor/save")
    public VendorModal save(@RequestBody VendorModal vendorModal){
        if(vendorModal.getvId()<1){
            vendorModal.setvBalance(vendorModal.getvBeginingBalance());
             vendorRepo.save(vendorModal);
        }else{
            vendorRepo.updateVendor(vendorModal.getvAddress(), vendorModal.getvEmail(),vendorModal.getvName()
                    ,vendorModal.getvPhone(),vendorModal.getvId());
        }
        return  null;
    }


    @DeleteMapping("vendor/delete/{id}")
    public  boolean delete(@PathVariable int id){
        boolean isDelateAble=false;
        List<Map<String,String>> vendorStatement =  vendorRepo.getVendorWhoPurchasedFrom(id);
        JSONArray jsonArray = new JSONArray(vendorStatement);
        if (jsonArray.length()<1){
            isDelateAble=true;
            vendorRepo.deleteById(id);
        }
        return isDelateAble;
    }
    @GetMapping("vendor/getOne/{id}")
    public Optional<VendorModal> getOneBranch(@PathVariable int id){
        return vendorRepo.findById(id);
    }

    @GetMapping("vendor/vendorInLoan")
    public List<Map<String,String>> vendorInLoan(){
        return vendorRepo.vendorInLoan();
    }
}
