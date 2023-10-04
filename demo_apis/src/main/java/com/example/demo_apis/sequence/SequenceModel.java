package com.example.demo_apis.sequence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class SequenceModel {

    @Id
    private String id;

    private long seq;

}
