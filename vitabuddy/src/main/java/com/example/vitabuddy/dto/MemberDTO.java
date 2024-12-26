package com.example.vitabuddy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private String userId;
    private String userName;
    private String userPwd;
    private String userRole = "USER";
    private String userEmail;
    private String userPh;
    private String userZipcode;
    private String userAddress1;
    private String userAddress2;

}
