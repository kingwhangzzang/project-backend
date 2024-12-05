package com.king.projectbackend.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class AesUtil {

    private final String secretKey256; // AES Key
    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivParameterSpec;

    // 생성자에서 키와 IV 초기화
    public AesUtil(@Value("${aes.secret.key}") String secretKey256) {
        this.secretKey256 = secretKey256;
        this.secretKeySpec = new SecretKeySpec(secretKey256.getBytes(StandardCharsets.UTF_8), "AES");
        this.ivParameterSpec = IVUtil.getIv(); // IVUtil에서 IV 가져오기
    }

    //AES 암호화(CBC 모드)
    public String aesCbcEncode(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        // 암호화모드,시크릿키,IV값 을가지고 암호화
        byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // 결과를 Base64로 반환
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // AES 복호화(CBC 모드)
    public String aesCbcDecode(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);

        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, StandardCharsets.UTF_8);
    }
}