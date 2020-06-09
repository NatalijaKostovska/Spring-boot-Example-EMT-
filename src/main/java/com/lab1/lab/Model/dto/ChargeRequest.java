package com.lab1.lab.Model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeRequest {
    private String description;
    private int amount;
    private String  currency;
    private String stripeToken;
}
