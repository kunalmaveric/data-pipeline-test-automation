package com.bankdata.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CustomerFeedBack {
    private Long customerId;
    private int rating;
    private boolean statisfied;
    private String feedback;
}
