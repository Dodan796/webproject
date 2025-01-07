package com.example.vitabuddy.dto;

public class NaverDTO {

    private String socialId; // 네이버 사용자 고유 ID
    private String userName; // 사용자 이름
    private String userEmail; // 이메일 주소
    private String gender; // 성별
    private String birthYear; // 출생 연도

    // Getter & Setter
    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
}
