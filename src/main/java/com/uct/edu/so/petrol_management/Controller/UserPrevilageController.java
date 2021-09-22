package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.UserPrevilageRepo;
import com.uct.edu.so.petrol_management.model.UserPrevilageModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UserPrevilageController {
    @Autowired
    private UserPrevilageRepo userPrevilageRepo;
    UserPrevilageModel userPrevilageModel1;
    @GetMapping("userPrevilage/list")
    public List<UserPrevilageModel> list(){
        return (List<UserPrevilageModel>) userPrevilageRepo.findAll();
    }

    //save

    @PostMapping("userPrevilage/save")
    public void save(@RequestBody List<Map<String,Integer>> userPrevilageModel) throws JSONException {

        JSONArray jsonArray = new JSONArray(userPrevilageModel);
//        if (jsonArray.length()>0) {
            JSONObject json =  jsonArray.getJSONObject(0);
            userPrevilageRepo.deletesavePrivilage(Integer.parseInt(json.getString("empId")));
//        }

        for (int i=0; i< jsonArray.length();i++){
            try {
                System.out.println("----");
                JSONObject jsonObject =  jsonArray.getJSONObject(i);
                System.out.println(jsonObject.toString());
                userPrevilageModel1 = new UserPrevilageModel();
                userPrevilageModel1.setSubMenueId(Integer.parseInt(jsonObject.getString("subMenueId")));
                userPrevilageModel1.setEmpId(Integer.parseInt(jsonObject.getString("empId")));
                System.out.println(userPrevilageModel1.getEmpId());

                userPrevilageRepo.save(userPrevilageModel1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

}
