package com.uct.edu.so.petrol_management.Controller;

import com.uct.edu.so.petrol_management.Repo.PetrolTypeRepo;
import com.uct.edu.so.petrol_management.model.PetrolTypeModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PetrolTypeController {
    final
    PetrolTypeRepo petrolTypeRepo;

    public PetrolTypeController(PetrolTypeRepo petrolTypeRepo) {
        this.petrolTypeRepo = petrolTypeRepo;
    }

    @GetMapping("petroltype/list")
    public List<PetrolTypeModel> list(){
        return  petrolTypeRepo.findAll();
    }
    @PostMapping("petroltype/save")
    public PetrolTypeModel save (@RequestBody PetrolTypeModel petrolTypeModel){
        return petrolTypeRepo.save(petrolTypeModel);
    }
    @DeleteMapping("petroltype/delete/{id}")
    public boolean delete (@PathVariable int id){
        boolean isDataTable = false;
        if(petrolTypeRepo.checkIfExists(id).size()<1){
            isDataTable = true;
        }
        if (isDataTable) petrolTypeRepo.deleteById(id);
        return isDataTable;
    }
    @GetMapping("petroltype/getOnepetroltype/{id}")
    public Optional<PetrolTypeModel> getOne(@PathVariable int id){
        return petrolTypeRepo.findById(id);
    }
}
