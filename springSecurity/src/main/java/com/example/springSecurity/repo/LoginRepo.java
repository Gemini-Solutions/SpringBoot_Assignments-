package com.example.springSecurity.repo;

import com.example.springSecurity.dto.LoginDto;
import com.example.springSecurity.models.UserModel;
import com.example.springSecurity.services.LoginServices;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface LoginRepo extends MongoRepository<UserModel, String> {

    Boolean existsByUsername(String name);
//
//    @Query(value="{email:?0}")
    Boolean existsByEmail(String email);

    UserModel findByUsername(String name);

//    boolean existsByUserame(String username);
}
