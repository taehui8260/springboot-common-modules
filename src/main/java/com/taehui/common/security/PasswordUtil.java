package com.taehui.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 패스워드 암호화
     * @param password 원본 패스워드
     * @return 암호화된 패스워드
     */
    public static String encodePassword(String password) {
        return encoder.encode(password);
    }

    /**
     * 패스워드 매칭
     * @param rawPassword 원본 패스워드
     * @param encodedPassword 암호화된 패스워드
     * @return 매칭 여부
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
