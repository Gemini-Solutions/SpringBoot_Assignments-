package com.example.demo_apis.repo;

import com.example.demo_apis.model.EmployeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepo extends MongoRepository<EmployeModel, String> {
    List<EmployeModel> findAllByNameIgnoreCaseAndLocationIgnoreCase(String name , String location);

    @Query(value = "{$or: [ { salary: {$lt: ?1} }, { salary: {$gt: ?0} } ]}")
    List<EmployeModel> findAllBySalaryLessThanOrGreaterThan(Long greater, Long less);

    @Query(value = "{$and: [ { salary: {$lt: ?0} }, { salary: {$gt: ?1} } ]}")
    List<EmployeModel> findAllBySalaryBetween(Long greater, Long less);
    @Query(value="{name:{$regex:?0}}")
    EmployeModel findByNameRegex(String regex);

    @Query(value="{id:?0,name:?1}")
    EmployeModel findByIdIgnoreCaseAndNameIgnoreCase(String id, String name);

    EmployeModel deleteByEmpId(Long id);

    Boolean existsByName(String name);

    @Query(value="{name:?0}")
    EmployeModel findByName(String name);

    EmployeModel findByEmpId(Long id);
}
