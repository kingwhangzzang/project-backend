package com.king.projectbackend.util;

import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;

public class IVUtil {
    //저장 위치 설정 함
    private static final String IV_FILE_PATH = "src/main/resources/iv.txt";
    public static IvParameterSpec getIv() {
        try {
            File ivFile = new File(IV_FILE_PATH);
            if (ivFile.exists()) {
                return loadIvFromFile(ivFile); //가져옴
            } else {
                return generateAndSaveIv(ivFile); // 생성
            }
        } catch (IOException e) {
            throw new RuntimeException("error", e);
        }
    }

    //IV생성하고 저장하는 메서드
    private static IvParameterSpec generateAndSaveIv(File ivFile) throws IOException {
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // IV를 파일에 저장
        try (FileWriter writer = new FileWriter(ivFile)) {
            writer.write(Base64.getEncoder().encodeToString(iv));
        }

        return new IvParameterSpec(iv);
    }

    // IV 파일에서 불러오기
    private static IvParameterSpec loadIvFromFile(File ivFile) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ivFile))) {
            String ivBase64 = reader.readLine();
            byte[] iv = Base64.getDecoder().decode(ivBase64);
            return new IvParameterSpec(iv);
        }
    }
}
