package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.CustomerRepo;
import com.uct.edu.so.petrol_management.Repo.PaymentRepo;
import com.uct.edu.so.petrol_management.Repo.TransactionPaymentsRepo;
import com.uct.edu.so.petrol_management.Repo.TransectionRepo;
import com.uct.edu.so.petrol_management.model.CustomerModel;
import com.uct.edu.so.petrol_management.model.PaymentModel;
import com.uct.edu.so.petrol_management.model.TransactionPaymentsModdel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class PaymentController {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private TransectionRepo transectionRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private TransactionPaymentsRepo transactionPaymentsRepo;

    private PaymentModel paymentModel;
    private TransactionPaymentsModdel transactionPaymentsModdel;
    private CustomerModel customerModel;


    @GetMapping("payment/list")
    public List<PaymentModel> list(){
        return paymentRepo.findAll();
    }


    @PostMapping("payment/save")
    public List<Map<String,List<Map<String, String>>>> save(@RequestBody List<Map<String,List<Map<String,String>>>>    transaction) throws JSONException {
        JSONArray jsonArray = new JSONArray(transaction);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        //payments data
        JSONArray js = (JSONArray) jsonObject.get("Payments");
        JSONObject paymentsObject = (JSONObject) js.get(0);
        System.out.println(paymentsObject.toString());
        //save payments
        paymentModel = new PaymentModel();
        paymentModel.setAmount(Double.parseDouble(paymentsObject.getString("amount")));
        paymentModel.setCusId(Integer.parseInt(paymentsObject.getString("cusId")));

        //
        paymentModel.setpId(paymentRepo.save(paymentModel).getpId());

        //decrease customer balance
        customerRepo.decreaseCustomerBalance(Double.parseDouble(paymentsObject.getString("amount")),
                Integer.parseInt(paymentsObject.getString("cusId")));

        //customer statement
        customerRepo.customerStatement(Integer.parseInt(paymentsObject.getString("cusId")),
               0,Double.parseDouble(paymentsObject.getString("amount")),"payment",paymentModel.getpId());

        //transaction payments data
        JSONArray transactionPaymentArray = (JSONArray) jsonObject.get("transactionPayments");
        double transactionPayingAmount,customerbegingBalance =0;
        for (int i=0;i<transactionPaymentArray.length();i++){
            JSONObject transactionPaymentObject = (JSONObject) transactionPaymentArray.get(i);

            //update customer begining balance
            if(i==0){
                if(Double.parseDouble(transactionPaymentObject.getString("payingAmount"))>
                        Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig"))
                && Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig"))!=0){
                    customerbegingBalance = Double.parseDouble(transactionPaymentObject.getString("payingAmount"))-
                            Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig"));
                }else
                    customerbegingBalance = Double.parseDouble(transactionPaymentObject.getString("payingAmount"));
                customerRepo.decreaseCustomerBeginigBalance(customerbegingBalance,Integer.parseInt(transactionPaymentObject.getString("cusId")));

            }

            //check transaction amount if les than paying amount
            if(Double.parseDouble(transactionPaymentObject.getString("payingAmount"))>
                    Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig")) &&
                    Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig"))!=0){
                transactionPayingAmount = Double.parseDouble(transactionPaymentObject.getString("transactionAmountRemainig"));


                transactionPaymentsModdel = new TransactionPaymentsModdel();
                transactionPaymentsModdel.setCusId(Integer.parseInt(transactionPaymentObject.getString("cusId")));
                transactionPaymentsModdel.setTranscId(Integer.parseInt(transactionPaymentObject.getString("transcId")));
                transactionPaymentsModdel.setPayingAmount(transactionPayingAmount);
                transactionPaymentsModdel.setPaymId(paymentModel.getpId());

                transactionPaymentsRepo.save(transactionPaymentsModdel);
                //update transaction amount
                transectionRepo.payAount(transactionPayingAmount,Integer.parseInt(transactionPaymentObject.getString("transcId")));
            }else {
                transactionPayingAmount = Double.parseDouble(transactionPaymentObject.getString("payingAmount"));
                System.out.println("========= test ");

                transactionPaymentsModdel = new TransactionPaymentsModdel();
                transactionPaymentsModdel.setCusId(Integer.parseInt(transactionPaymentObject.getString("cusId")));
                transactionPaymentsModdel.setTranscId(Integer.parseInt(transactionPaymentObject.getString("transcId")));
                transactionPaymentsModdel.setPayingAmount(transactionPayingAmount);
                transactionPaymentsModdel.setPaymId(paymentModel.getpId());
                transactionPaymentsRepo.save(transactionPaymentsModdel);
                //update transaction amount
                transectionRepo.payAount(transactionPayingAmount,Integer.parseInt(transactionPaymentObject.getString("transcId")));
            }

        }

        return transaction;
    }


    @DeleteMapping("payment/delete/{id}")
    public  void delete(@PathVariable int id) throws JSONException {
        List<Map<String,String>> transaction = transactionPaymentsRepo.getTransactionPayments(id);
        JSONArray jsonArray =new JSONArray(transaction);
        //decrease amount
        double transactionAmount=0;

        for (int i=0;i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
            transectionRepo.decreaseAmount(Double.parseDouble(object.getString("paying_amount")),
                   Integer.parseInt(object.getString("transc_id")));
                transactionAmount += Double.parseDouble(object.getString("paying_amount"));

        }

        //delete transaction payment
        transactionPaymentsRepo.deleteTransactionPayments(id);
        //get customer statement
        List<Map<String,String>> statement = customerRepo.getCustomerStatement("payment",id);
        JSONArray statementArray =new JSONArray(statement);
        JSONObject object = statementArray.getJSONObject(0);

        customerRepo.increaseCustomerBalance(Double.parseDouble(object.getString("cashOut")),
               Integer.parseInt(object.getString("cstId")));


        customerRepo.increaseCustomerBeginigBalance((Double.parseDouble(object.getString("cashOut"))-transactionAmount),
                    Integer.parseInt(object.getString("cstId")));

        customerRepo.deleteCustomerStatement("payment",Integer.parseInt(object.getString("dueToId")));
        paymentRepo.deleteById(id);
    }

    @GetMapping("payment/getOne/{id}")
    public Optional<PaymentModel> getOneBranch(@PathVariable int id){
        return paymentRepo.findById(id);
    }

    @GetMapping("payment/details/{id}")
    public List<Map<String,String>> getPaymentDetails(@PathVariable int id){
        return transactionPaymentsRepo.paymentDetails(id);
    }
}
