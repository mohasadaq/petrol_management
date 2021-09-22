package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.CustomerRepo;
import com.uct.edu.so.petrol_management.Repo.TransectionRepo;
import com.uct.edu.so.petrol_management.model.MeterReading;
import com.uct.edu.so.petrol_management.model.TransectionModel;
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
public class TransectionController {

    @Autowired
    private TransectionRepo transactionRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("transection/list")
    public List<TransectionModel> list() {
        return transactionRepo.getList();
    }

    @PostMapping("transection/listbydate")
    public List<TransectionModel> listbydate(@RequestBody List<Map<String,String>> list) throws JSONException {
        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        System.out.println("=======");
        System.out.println(list.toString());
        return transactionRepo.getListbydate(jsonObject.getString("datefrom"),jsonObject.getString("dateto"));
    }

    @PostMapping("transection/save")
    public TransectionModel save(@RequestBody TransectionModel transectionModel) throws JSONException {

            if(transectionModel.getTrnsId()<1){
                transectionModel.setTrnsId(transactionRepo.save(transectionModel).getTrnsId());
                //customer statement
                customerRepo.customerStatement(transectionModel.getCustmrid(),
                        (transectionModel.getNumofliter()*transectionModel.getPriceperLiter())
                                -transectionModel.getAmountpaid(),0,"transaction",
                        transectionModel.getTrnsId()
                );

                //INCREASE CUSTOMER BALANCE
                customerRepo.increaseCustomerBalance(
                        (transectionModel.getNumofliter()*transectionModel.getPriceperLiter())
                                -transectionModel.getAmountpaid(),
                        transectionModel.getCustmrid()
                );
            }else{
                List<Map<String,String>> customerStatement = customerRepo.getCustomerStatement("transaction",transectionModel.getTrnsId());
                JSONArray jsonArray = new JSONArray(customerStatement);
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                //decrease customer balance
                customerRepo.decreaseCustomerBalance(Double.parseDouble(jsonObject.getString("cashIn")),transectionModel.getCustmrid());
                //delete customer statement
                customerRepo.deleteCustomerStatement("transaction",transectionModel.getTrnsId());

                // ===================================================================================== end prev process
                //update
                 transactionRepo.save(transectionModel).getTrnsId();
                //customer statement
                customerRepo.customerStatement(transectionModel.getCustmrid(),
                        (transectionModel.getNumofliter()*transectionModel.getPriceperLiter())
                                -transectionModel.getAmountpaid(),0,"transaction",
                        transectionModel.getTrnsId()
                );

                //INCREASE CUSTOMER BALANCE
                customerRepo.increaseCustomerBalance(
                        (transectionModel.getNumofliter()*transectionModel.getPriceperLiter())
                                -transectionModel.getAmountpaid(),
                        transectionModel.getCustmrid()
                );

            }


        return transectionModel;
    }

    @GetMapping("transection/getone/{id}")
    public Optional<TransectionModel> getone(@PathVariable int id) {
        return transactionRepo.findById(id);
    }

    @DeleteMapping("transection/delete/{pid}")
    public boolean delete(@PathVariable int pid) throws JSONException {
         boolean isDelateAble=false;
        List<Map<String,Integer>> transactions =  transactionRepo.getTransactionThatPayed(pid);
        JSONArray jsonArray= new JSONArray(transactions);
        if (jsonArray.length()<1){
            isDelateAble=true;
            List<Map<String,String>> statement = customerRepo.getCustomerStatement("transaction",pid);
            JSONArray arr = new JSONArray(statement);
            JSONObject jsonObject=arr.getJSONObject(0);

            customerRepo.decreaseCustomerBalance(Double.parseDouble(jsonObject.getString("cashIn")),
                    Integer.parseInt(jsonObject.getString("cstId")));

            customerRepo.deleteCustomerStatement("transaction",Integer.parseInt(jsonObject.getString("dueToId")));
            transactionRepo.deleteById(pid);
        }


        return isDelateAble;
    }

    @GetMapping("transection/total")
    public List<Map<String,String>> money() {
        return transactionRepo.total_Amount();
    }
    @GetMapping("transection/n_of_l_sold")
    public List<Map<String,String>> number_of_literes_sold() {
        return transactionRepo.N_of_l_sold();
    }

    // how manay liters are remaining
    @GetMapping("transection/ramaining")
    public List<Map<String,String>> numoflierremaing() {
        return transactionRepo.numOfLiterRemaining();
    }

    @GetMapping("transection/ramaining/{id}")
    public List<Map<String,String>> numoflierremaingByid(@PathVariable int id) {
        return transactionRepo.numOfLiterRemainingByid(id);
    }

    //SALES REPORT COMPARISON BETWEEN METER READING AND TRANSACTION
    @GetMapping("transection/salesReport")
    public List<Map<String,Integer>> salesReport() {
        return transactionRepo.salesReport();
    }
}
