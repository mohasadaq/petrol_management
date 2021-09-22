package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.CustomerRepo;
import com.uct.edu.so.petrol_management.model.CustomerModel;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CustomerController {
    @Autowired
    CustomerRepo customerRepo;

    @GetMapping("customer/list")
    public List<CustomerModel> list(){
        return customerRepo.findAll();
    }
    
    @PostMapping("customer/save")
    public CustomerModel save (@RequestBody CustomerModel customerModel){
        //set balance
        CustomerModel customer = null;
        if(customerModel.getcId()<1){
            customerModel.setBalance(customerModel.getBeginingBalance());
             customer =  customerRepo.save(customerModel);
        }else{
            customerRepo.updateCustomer(customerModel.getCaddress(),customerModel.getCname(),customerModel.getCphone(),customerModel.getcId());
        }
        return customer;
    }

    @DeleteMapping("customer/delete/{id}")
    public boolean delete (@PathVariable int id){
        boolean isDelateAble=false;
        List<Map<String,String>> customer = customerRepo.getCustomerInTransaction(id);
        JSONArray jsonArray = new JSONArray(customer);
        if(id!=1){
            if (jsonArray.length()<1){
                customerRepo.deleteById(id);
                isDelateAble = true;
            }
        }
        return isDelateAble;
    }


    @GetMapping("customer/getOne/{id}")
    public Optional<CustomerModel> getOneEmployee(@PathVariable int id){
        return customerRepo.findById(id);
    }

    @GetMapping("customer/customerInLoan")
    public List<Map<String,Integer>> customerInLoan(){
        return customerRepo.customerInLoan();
    }

}
