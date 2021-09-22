package com.uct.edu.so.petrol_management.Controller;
import com.uct.edu.so.petrol_management.Repo.BurchaseRepo;
import com.uct.edu.so.petrol_management.Repo.PurchasePayments;
import com.uct.edu.so.petrol_management.Repo.VendorRepo;
import com.uct.edu.so.petrol_management.model.BurchaseModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*")
public class BurchaseController {

    @Autowired
    private BurchaseRepo burchaseRepo;
    @Autowired
    private VendorRepo vendorRepo;
    @Autowired
    private PurchasePayments purchasePayments;

    @GetMapping("Burchase/list")
    public List<BurchaseModel> list() {
        return burchaseRepo.findAll();
    }

    @PostMapping("Burchase/save")
    public BurchaseModel save(@RequestBody BurchaseModel petrolModel) throws JSONException {
        if(petrolModel.getPid()<1){
           petrolModel.setPid(burchaseRepo.save(petrolModel).getPid());
           //vendor statement
            vendorRepo.vendorStatement(petrolModel.getVndId(),(petrolModel.getPricePerLiter()
                    *petrolModel.getQuantity())-petrolModel.getAmountPaid(),0,"purchase",
                    petrolModel.getPid());
            //increase vendor balance
            vendorRepo.updateVendorBalance((petrolModel.getPricePerLiter()
                    *petrolModel.getQuantity())-petrolModel.getAmountPaid(),petrolModel.getVndId());
        }else {
            List<Map<String,String>> vendorStatement = vendorRepo.getVendorStatement("purchase",petrolModel.getPid());
            JSONArray jsonArray = new JSONArray(vendorStatement);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            //decrease vendor balance
            vendorRepo.decreaseVendorBalance(jsonObject.getString("cashIn"),jsonObject.getString("vId"));
            // delete vendor statement
            vendorRepo.deleteVendorStatement("purchase",Integer.parseInt(jsonObject.getString("dueToId")));
            //-============================================================================end of prev

            //update
            burchaseRepo.save(petrolModel);
            //vendor statement
            vendorRepo.vendorStatement(petrolModel.getVndId(),(petrolModel.getPricePerLiter()
                            *petrolModel.getQuantity())-petrolModel.getAmountPaid(),0,"purchase",
                    petrolModel.getPid());
            //increase vendor balance
            vendorRepo.updateVendorBalance((petrolModel.getPricePerLiter()
                    *petrolModel.getQuantity())-petrolModel.getAmountPaid(),petrolModel.getVndId());
        }
        return null;
    }

    @GetMapping("Burchase/getone/{id}")
    public Optional<BurchaseModel> getone(@PathVariable int id) {
        return burchaseRepo.findById(id);
    }

    @DeleteMapping("Burchase/delete/{pid}")
    public boolean delete(@PathVariable int pid) {
        List<Map<String,String>> purchase = purchasePayments.getPurchasedPayments(pid);
        JSONArray jsonArray = new JSONArray(purchase);
        boolean isDelateAble =false;
        if (jsonArray.length()<1){
            List<Map<String,String>> statement =  vendorRepo.getVendorStatement("purchase",pid);
            JSONArray arr =new JSONArray(statement);
            try {
                JSONObject object = arr.getJSONObject(0);
                System.out.println("===");
                System.out.println(object.getString("cashIn"));
                vendorRepo.decreaseVendorBalance(
                        object.getString("cashIn")
                        ,object.getString("vId"));
                vendorRepo.deleteVendorStatement("purchase",Integer.parseInt(object.getString("dueToId")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            burchaseRepo.deleteById(pid);
        }


        return isDelateAble;
    }


    @GetMapping("Burchase/totalFualCost")
    public Double fuelCost() {
      return burchaseRepo.fuelCost();
    }
}
