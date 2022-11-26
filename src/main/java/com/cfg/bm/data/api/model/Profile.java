package com.cfg.bm.data.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGeneratorToUserId")
    private long id;

    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @Column
    private String areaCode;
    
    @Column
    private String cellPhoneNumber;
}
