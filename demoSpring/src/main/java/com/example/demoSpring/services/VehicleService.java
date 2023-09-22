package com.example.demoSpring.services;

import com.example.demoSpring.dto.VehicleDto;
import com.example.demoSpring.models.VehicleModel;
import com.example.demoSpring.repo.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

        @Autowired
        public VehicleRepository vehicleRepository;


        @Autowired
        private ModelMapper modelMapper;

        public ResponseEntity<Object> saveDetails(VehicleDto dto){
           VehicleModel data = modelMapper.map(dto,VehicleModel.class);
            System.out.println(data.toString());
            VehicleModel response = vehicleRepository.save(data);
            return ResponseEntity.ok(response);
        }
    public ResponseEntity<Object> updateDetails(String id,VehicleDto dto){
        VehicleModel data = modelMapper.map(dto,VehicleModel.class);
        data.setId(id);
        System.out.println(data.toString());

        return ResponseEntity.ok(vehicleRepository.save(data));
//        VehicleModel da = response.get();
//        da.setModel(data.getModel());
//        da.setNumber(data.getNumber());
//        vehicleRepository.save(response.get());
//        return ResponseEntity.ok(response);
    }
        public ResponseEntity<List<VehicleModel>> findDetailsByModel(String model){
//        List<Optional<VehicleModel>> response = vehicleRepository.findBymodelIgnoreCase(model);
        return ResponseEntity.ok(vehicleRepository.findBymodelIgnoreCase(model));
    }

    public ResponseEntity<Optional<VehicleModel>> findVehicleDetailsById(String id){
//        Optional<VehicleModel> response = vehicleRepository.findById(id);
        return ResponseEntity.ok(vehicleRepository.findById(id));
    }

    public ResponseEntity<List<VehicleModel>> findVehicleDetailsByYear(Long year){
//        Optional<VehicleModel> response = vehicleRepository.findById(id);
//        Query query = new Query();
//        query.addCriteria(Criteria.where("year").gte(year));
//        List<User> users = mongoTemplate.find(query,User.class);
        return ResponseEntity.ok(vehicleRepository.findAllByYearGreaterThanEqual(year));
    }


}
