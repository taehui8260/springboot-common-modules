package com.taehui.common.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.SecureRandom;

public class AESUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding"; // AES 알고리즘 + CBC 모드 + PKCS5Padding
    private static final int IV_SIZE = 16; // IV(Initialization Vector)의 크기 (16바이트 = 128비트)

    /**
     * 문자열을 AES 알고리즘으로 암호화
     *
     * @param data      암호화할 원본 문자열
     * @param secretKey AES 암호화 키 (16바이트, 128비트 또는 32바이트, 256비트)
     * @return Base64로 인코딩된 IV + 암호화된 데이터
     * @throws Exception 암호화 중 발생하는 모든 예외
     */
    public static String encrypt(String data, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // IV 생성 (암호화마다 고유하게 생성됨)
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); // 랜덤하게 IV 생성
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 키 설정 (AES 키는 반드시 16바이트 또는 32바이트여야 함)
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // 암호화 모드 초기화
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 데이터를 암호화
        byte[] encrypted = cipher.doFinal(data.getBytes());

        // IV + 암호화된 데이터를 하나로 합침
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length); // 앞부분에 IV 복사
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length); // 그 뒤에 암호화된 데이터 복사

        // Base64로 인코딩하여 반환 (전송 및 저장에 적합한 형태로 변환)
        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    /**
     * AES 암호화된 문자열을 복호화
     *
     * @param encryptedData Base64로 인코딩된 IV + 암호화된 데이터
     * @param secretKey     AES 암호화 키 (16바이트, 128비트 또는 32바이트, 256비트)
     * @return 복호화된 원본 문자열
     * @throws Exception 복호화 중 발생하는 모든 예외
     */
    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // Base64로 디코딩하여 바이트 배열로 변환
        byte[] ivAndEncrypted = Base64.getDecoder().decode(encryptedData);

        // IV와 암호화된 데이터 분리
        byte[] iv = new byte[IV_SIZE];
        byte[] encrypted = new byte[ivAndEncrypted.length - IV_SIZE];
        System.arraycopy(ivAndEncrypted, 0, iv, 0, IV_SIZE); // 앞부분은 IV
        System.arraycopy(ivAndEncrypted, IV_SIZE, encrypted, 0, encrypted.length); // 나머지는 암호화된 데이터

        // IV 설정
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 키 설정 (AES 키는 반드시 16바이트 또는 32바이트여야 함)
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // 복호화 모드 초기화
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 데이터를 복호화
        return new String(cipher.doFinal(encrypted));
    }
}
