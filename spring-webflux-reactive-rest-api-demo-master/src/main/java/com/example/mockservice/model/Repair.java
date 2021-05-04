package com.example.mockservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;



@Document(collection = "repairs")
@Data
public class Repair {
    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String repairStatusCode;

    @NotNull
    private Date repairCreateDate = new Date();
    
    private String purchaseOrderNumber;
    
    private String repairTypeDescription;

   
}
