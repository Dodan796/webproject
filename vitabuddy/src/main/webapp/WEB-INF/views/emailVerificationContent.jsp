<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Email Verification</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .email-container {
            max-width: 600px;
            margin: 30px auto;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }
        .email-header {
            background-color: #4caf50;
            color: #ffffff;
            padding: 20px;
            text-align: center;
        }
        .email-header h1 {
            margin: 0;
            font-size: 26px;
            font-weight: bold;
        }
        .email-body {
            padding: 30px;
            font-size: 16px;
            color: #444444;
            line-height: 1.8;
            text-align: center;
        }
        .email-body p {
            margin: 15px 0;
        }
        .verification-code {
            display: inline-block;
            font-size: 28px;
            font-weight: bold;
            color: #ffffff;
            background-color: #ff9800;
            padding: 15px 30px;
            border-radius: 8px;
            margin: 20px 0;
            text-decoration: none;
            letter-spacing: 3px;
        }
        .email-footer {
            background-color: #f1f1f1;
            color: #666666;
            padding: 15px;
            text-align: center;
            font-size: 14px;
        }
        .email-footer a {
            color: #4caf50;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="email-container">
    <div class="email-header">
        <h1>VitaBuddy인증메일</h1>
    </div>
    <div class="email-body">
        <p>VitaBuddy에 가입해주셔서 감사합니다</p>
        <p>아래의 인증코드를 유효시간이 지나기 전에 입력해 주세요</p>
        <div class="verification-code">123456</div>
        <p>If you did not sign up, please disregard this email.</p>
    </div>
    <div class="email-footer">
        <p>&copy; 2025 <a href="#">Your Company</a>. All rights reserved.</p>
    </div>
</div>
</body>
</html>
