package com.king.projectbackend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AesUtil {

    private final String secretKey256;
    private SecretKeySpec secretKeySpec;
    private IvParameterSpec ivParameterSpec;

    // 생성자에서 초기화 처리
    public AesUtil(@Value("${aes.secret.key}") String secretKey256) {
        this.secretKey256 = secretKey256;
        init();
    }

    // AES 키와 IV 초기화
    public void init() {
        secretKeySpec = new SecretKeySpec(secretKey256.getBytes(StandardCharsets.UTF_8), "AES");
        ivParameterSpec = new IvParameterSpec(generateIv());
    }

    // 16바이트 IV 생성 (무작위 방식)
    private byte[] generateIv() {
        byte[] iv = new byte[16]; // AES block size는 16
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    // AES 암호화(CBC 모드)
    public String aesCbcEncode(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // 결과를 Base64로 반환
        String encryptedData = Base64.getEncoder().encodeToString(encrypted);
        String ivData = Base64.getEncoder().encodeToString(ivParameterSpec.getIV());

        return ivData + ":" + encryptedData; // :로 구분하여 저장 (암호화된 데이터와 IV)
    }

    // AES 복호화(CBC 모드)
    public String aesCbcDecode(String encryptedDataWithIv) throws Exception {
        String[] parts = encryptedDataWithIv.split(":");
        String ivData = parts[0];
        String encryptedData = parts[1];

        byte[] iv = Base64.getDecoder().decode(ivData);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);

        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}