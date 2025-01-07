package com.example.vitabuddy.dto;

public class GoogleDTO {

    private String socialId;
    private String userName;
    private String userEmail;

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getSocialId() {
        return socialId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAuthType(String google) {
    }
}
