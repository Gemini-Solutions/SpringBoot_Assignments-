package com.example.demoSpring.repo;

import com.example.demoSpring.models.VehicleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Repository
public interface VehicleRepository extends MongoRepository<VehicleModel, String> {

    List<VehicleModel> findBymodelIgnoreCase(String model);

    Optional<VehicleModel> findById(String id);

    @Query(value = "{year:{$gte:?0}}")
    List<VehicleModel> findAllByYearGreaterThanEqual(Long year);

}
