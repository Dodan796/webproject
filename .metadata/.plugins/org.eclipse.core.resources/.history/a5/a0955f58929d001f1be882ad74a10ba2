package com.example.vitabuddy.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;

public class V28__UpdateImages extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        // 서버 환경에 맞는 시작 SupID 및 파일 경로 설정
        String basePath = "/usr/local/project/supplement_images/"; // 서버 이미지 파일 경로
        int startId = 1000;
        int endId = 1200;

        // Flyway가 제공하는 DB 연결을 사용하여 PreparedStatement 생성
        try (PreparedStatement pstmt = context.getConnection().prepareStatement(
                "UPDATE supplement SET SupImg = ? WHERE SupID = ?")) {

            // SupID에 따라 이미지 업데이트 루프 실행
            for (int supId = startId; supId <= endId; supId++) {
                String imagePath = basePath + supId + ".png"; // 예: "/usr/local/project/supplement_images/1000.png"
                File imageFile = new File(imagePath);

                // 이미지 파일이 있는지 확인
                if (imageFile.exists()) {
                    try (FileInputStream fis = new FileInputStream(imageFile)) {
                        pstmt.setBinaryStream(1, fis, (int) imageFile.length());
                        pstmt.setInt(2, supId);

                        // SQL 업데이트 실행
                        int rowsAffected = pstmt.executeUpdate();
                        System.out.println("SupID " + supId + ": Rows affected: " + rowsAffected);
                    }
                } else {
                    System.out.println("SupID " + supId + ": 파일이 없습니다. 경로 확인 -> " + imagePath);
                }
            }
        }
    }
}
