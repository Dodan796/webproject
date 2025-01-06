package com.example.vitabuddy.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RefreshVO{

    private String userEmail;
    private String refreshToken;
    private Timestamp expiration;

}
