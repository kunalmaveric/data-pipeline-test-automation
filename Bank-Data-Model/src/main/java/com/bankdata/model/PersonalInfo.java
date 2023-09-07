package com.bankdata.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@ToString
public class PersonalInfo {
    private Long customerId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String panNumber;
    private ContactInfomation contactInfomation;
    private EmploymentInfo employmentInfo;
    private String status;
    private int dependents;
    private Long account_No;

}
