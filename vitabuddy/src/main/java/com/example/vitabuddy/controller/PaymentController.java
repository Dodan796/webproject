package com.example.vitabuddy.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @GetMapping("/success")
    public ResponseEntity<String> successPayment(
            @RequestParam("paymentKey") String paymentKey,
            @RequestParam("orderId") String orderId,
            @RequestParam("amount") Long amount
    ) {
        // 승인 API 호출
        String result = approvePayment(paymentKey, orderId, amount);
        return ResponseEntity.ok(result);
    }

    private String approvePayment(String paymentKey, String orderId, Long amount) {
        // 토스 페이먼츠 Secret Key
        final String SECRET_KEY = "test_sk_yZqmkKeP8gBqDKNdWNAdrbQRxB9l";

        // 승인 API URL
        String url = "https://api.tosspayments.com/v1/payments/confirm";

        // 요청 바디 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("paymentKey", paymentKey);
        requestBody.put("orderId", orderId);
        requestBody.put("amount", amount);

        // RestTemplate을 이용해 API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(SECRET_KEY, ""); // Basic 인증

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );
            return response.getBody(); // 성공 응답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "결제 승인 실패: " + e.getMessage(); // 실패 시 에러 메시지 반환
        }
    }

    @GetMapping("/fail")
    public String failPayment() {
        // 결제 실패 처리
        return "fail"; // fail.html 또는 fail.jsp 뷰를 반환
    }
}


