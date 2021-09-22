package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.MeterReadingRepo;
import com.uct.edu.so.petrol_management.model.MeterReading;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class MeterReadingController {

    @Autowired
    private MeterReadingRepo meterReadingRepo;
        @GetMapping("meterReading/list")
    public List<MeterReading> list(){
        return meterReadingRepo.getList();
    }

    //get list by date
    @PostMapping("meterReading/listbydate")
    public List<MeterReading> listbydate(@RequestBody List<Map<String,String>> list) throws JSONException {
        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        System.out.println("=======");
        System.out.println(jsonObject.toString());
        return meterReadingRepo.getListbydate(jsonObject.getString("datefrom"),jsonObject.getString("dateto"));
    }


    @PostMapping("meterReading/save")
    public MeterReading save(@RequestBody MeterReading meterReading){
        return meterReadingRepo.save(meterReading);
    }
    @DeleteMapping("meterReading/delete/{id}")
    public  String delete(@PathVariable int id){
        meterReadingRepo.deleteById(id);
        return "Branch Successfully Delete"+id;
    }
    @GetMapping("meterReading/getbyId/{id}")
    public Optional<MeterReading> getOneBranch(@PathVariable int id){
        return meterReadingRepo.findById(id);
    }

    @GetMapping("meterReading/lastreading")
    public List<Map<String,String>> getOneBranch(){
        return meterReadingRepo.lastReading();
    }
}
