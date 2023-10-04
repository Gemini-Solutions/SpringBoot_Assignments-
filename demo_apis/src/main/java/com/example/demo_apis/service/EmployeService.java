package com.example.demo_apis.service;

import com.example.demo_apis.dto.EmployeDto;
import com.example.demo_apis.exceptionHandler.EmployeNotFoundException;
import com.example.demo_apis.model.EmployeModel;
import com.example.demo_apis.repo.EmployeRepo;
import com.example.demo_apis.security.JwtUtils;
import com.example.demo_apis.sequence.SequenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmployeRepo employeRepo;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    SequenceService sequenceService;

    public ResponseEntity<Object> createEmploye(EmployeDto data){
        data.setEmpId(sequenceService.generateSequence(EmployeModel.SEQUENCE_NAME));
        EmployeModel employeData = modelMapper.map(data, EmployeModel.class);
        return new ResponseEntity<>(employeRepo.save(employeData),  new HttpHeaders(), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateEmploye(EmployeDto data){
        EmployeModel employeData = modelMapper.map(data, EmployeModel.class);
        return new  ResponseEntity<>(employeRepo.save(employeData),  new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<List<EmployeModel>> getEmployeDetails(){
        return ResponseEntity.ok(employeRepo.findAll());
    }

    public ResponseEntity<Optional<EmployeModel>> getEmployeDetail(Long id){
        Optional<EmployeModel> data = Optional.ofNullable(employeRepo.findByEmpId(id));
        if (data==null){
            throw new EmployeNotFoundException("Employe not found with the given ID.");
        }
        return ResponseEntity.ok(data);
    }

    public ResponseEntity<List<EmployeModel>> getEmployefromLocationAndName(String name,String id){
        List<EmployeModel> data = employeRepo.findAllByNameIgnoreCaseAndLocationIgnoreCase(name, id);
        if(data.size()==0){
            throw new EmployeNotFoundException("Employe not found with the given location and name");
        }
        return ResponseEntity.ok(employeRepo.findAllByNameIgnoreCaseAndLocationIgnoreCase(name, id));
    }

    public ResponseEntity<List<EmployeModel>> getEmployefromLessAndGreater(Long less,Long greater){
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("salary").lte(less),Criteria.where("salary").gte(greater));
        Query query = new Query(criteria);
        return ResponseEntity.ok(mongoTemplate.find(query, EmployeModel.class));
    }

    public ResponseEntity<List<EmployeModel>> getEmployeSalaryBetween(Long less,Long greater){
        Query query = new Query();
        query.addCriteria(Criteria.where("salary").lte(greater).andOperator(Criteria.where("salary").gte(less)));
        return ResponseEntity.ok(mongoTemplate.find(query,EmployeModel.class));
    }

    public ResponseEntity<List<EmployeModel>> getEmployefromLessAndGreaterMongoRepo(Long less,Long greater){
        return ResponseEntity.ok(employeRepo.findAllBySalaryLessThanOrGreaterThan(greater,less));

    }

    public ResponseEntity<List<EmployeModel>> getEmployeSalaryBetweenMongoRepo(Long less,Long greater){
        return ResponseEntity.ok(employeRepo.findAllBySalaryBetween(greater,less));

    }

    public ResponseEntity deleteEmpById(Long id){
        EmployeModel data = employeRepo.deleteByEmpId(id);
        if (data==null){
            throw new EmployeNotFoundException("Employe not found with the given ID.");
        }
        return ResponseEntity.ok("deleted");
    }

    public ResponseEntity<EmployeModel> getEmployeDetailbyNameInitials(String ini){
        String regexPattern = "^"+ ini;
        return ResponseEntity.ok(employeRepo.findByNameRegex(regexPattern));
    }

    public ResponseEntity<String> generateToken(String name){

        if (employeRepo.existsByName(name)){
        return new ResponseEntity<>(jwtUtils.generateTokenFromUsername(name),new HttpHeaders(), HttpStatus.OK);
    }else {
            return new ResponseEntity<>("User Not Found",new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }
}
