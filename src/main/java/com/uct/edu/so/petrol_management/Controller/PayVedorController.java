package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.BurchaseRepo;
import com.uct.edu.so.petrol_management.Repo.PayVendorRepo;
import com.uct.edu.so.petrol_management.Repo.PurchasePayments;
import com.uct.edu.so.petrol_management.Repo.VendorRepo;
import com.uct.edu.so.petrol_management.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayVedorController {
    @Autowired
    PayVendorRepo  payVendorRepo;
    @Autowired
    VendorRepo vendorRepo;
    @Autowired
    PurchasePayments purchasePayments;
    @Autowired
    BurchaseRepo burchaseRepo;

    private PayVendorModel payVendorModel;
    private PurchasePaymentsModel purchasePaymentsModel;

    @GetMapping("payVedor/list")
    public List<PayVendorModel> list(){
        return  payVendorRepo.findAll();
    }
    @PostMapping("payVedor/save")
    public   List<Map<String,List<Map<String, String>>>> save(@RequestBody List<Map<String,List<Map<String,String>>>> payVendor) throws JSONException {

        JSONArray jsonArray = new JSONArray(payVendor);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        //payments data
        JSONArray js = (JSONArray) jsonObject.get("Payments");
        JSONObject paymentsObject = (JSONObject) js.get(0);
        System.out.println(paymentsObject.toString());
        //save payments
        payVendorModel = new PayVendorModel();
        payVendorModel.setAmount(Double.parseDouble(paymentsObject.getString("amount")));
        payVendorModel.setVendorId(Integer.parseInt(paymentsObject.getString("vendorId")));

        payVendorModel.setId(payVendorRepo.save(payVendorModel).getId());
//        //decrease Vendor balance
        vendorRepo.decreaseVendorBalance(paymentsObject.getString("amount"),
                paymentsObject.getString("vendorId"));
//        //vendor statement
        vendorRepo.vendorStatement(Integer.parseInt(paymentsObject.getString("vendorId")),
                0,Double.parseDouble(paymentsObject.getString("amount")),"payment",payVendorModel.getId());

//        //transaction payments data
        JSONArray purchasePayment = (JSONArray) jsonObject.get("purchasePayments");
        double transactionPayingAmount,vendorbegingBalance =0;
        for (int i=0;i<purchasePayment.length();i++){
            JSONObject transactionPaymentObject = (JSONObject) purchasePayment.get(i);

            //update customer begining balance
            if(i==0){
                if(Double.parseDouble(transactionPaymentObject.getString("payingAmount"))>
                        Double.parseDouble(transactionPaymentObject.getString("purchaseAmountRemainig"))){
                    vendorbegingBalance = Double.parseDouble(transactionPaymentObject.getString("payingAmount"))-
                            Double.parseDouble(transactionPaymentObject.getString("purchaseAmountRemainig"));
                    vendorRepo.decreaseVendorBeginingBalance(vendorbegingBalance,Integer.parseInt(transactionPaymentObject.getString("vendorId")));
                }
            }

//            //check transaction amount if les than paying amount
            if(Double.parseDouble(transactionPaymentObject.getString("purchaseId")) !=0){
                if (Double.parseDouble(transactionPaymentObject.getString("payingAmount"))>
                        Double.parseDouble(transactionPaymentObject.getString("purchaseAmountRemainig"))){
                    transactionPayingAmount = Double.parseDouble(transactionPaymentObject.getString("purchaseAmountRemainig"));
                }else {
                    transactionPayingAmount = Double.parseDouble(transactionPaymentObject.getString("payingAmount"));
                }


                purchasePaymentsModel = new PurchasePaymentsModel();
                purchasePaymentsModel.setVendorId(Integer.parseInt(transactionPaymentObject.getString("vendorId")));
                purchasePaymentsModel.setPurchaseId(Integer.parseInt(transactionPaymentObject.getString("purchaseId")));
                purchasePaymentsModel.setPayingAmount(transactionPayingAmount);
                purchasePaymentsModel.setPaymId(payVendorModel.getId());
                purchasePayments.save(purchasePaymentsModel);
                //update transaction amount
                burchaseRepo.payAmount(transactionPayingAmount,Integer.parseInt(transactionPaymentObject.getString("purchaseId")));
            }
        }
        return payVendor;
    }


    @DeleteMapping("payVedor/delete/{id}")
    public List<Map<String,String>> delete (@PathVariable int id) throws JSONException {
        List<Map<String,String>> purchasePayment = purchasePayments.getPurchasePayments(id);
        JSONArray jsonArray =new JSONArray(purchasePayment);
        //decrease amount
        double transactionAmount=0;

        for (int i=0;i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            if(Integer.parseInt(object.getString("purchase_id"))!=0){
                burchaseRepo.decreaseAmount(Double.parseDouble(object.getString("paying_amount")),
                        Integer.parseInt(object.getString("purchase_id")));
                transactionAmount += Double.parseDouble(object.getString("paying_amount"));
            }

        }

        //delete transaction payment
        purchasePayments.deletePurchasePayments(id);

        //get customer statement
        List<Map<String,String>> statement = vendorRepo.getVendorStatement("payment",id);
        JSONArray statementArray =new JSONArray(statement);
        JSONObject object = statementArray.getJSONObject(0);

        vendorRepo.updateVendorBalance(Double.parseDouble(object.getString("cashOut")),
                Integer.parseInt(object.getString("vId")));
        vendorRepo.increaseVendorBeginingBalance(Double.parseDouble(object.getString("cashOut"))
                        -transactionAmount,
                Integer.parseInt(object.getString("vId")));

        vendorRepo.deleteVendorStatement("payment",id);
        payVendorRepo.deleteById(id);
        return purchasePayment;
    }


    @GetMapping("payVedor/details/{id}")
    public List<Map<String,String>> getPaymentDetails(@PathVariable int id){
        return payVendorRepo.paymentDetails(id);
    }

}
