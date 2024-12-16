package com.taehui.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 비밀 키
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간 (밀리초)

    /**
     * JWT 토큰 생성
     * @param subject 사용자 ID 또는 이메일
     * @return 생성된 JWT 토큰
     */
    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject) // 토큰 주체
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 만료 시간
                .signWith(SECRET_KEY) // 서명
                .compact();
    }

    /**
     * JWT 토큰 검증 및 클레임 파싱
     * @param token 검증할 JWT 토큰
     * @return 클레임 (Claims)
     * @throws JwtException 토큰이 유효하지 않을 경우 예외 발생
     */
    public static Claims validateToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * JWT 토큰에서 사용자 정보 추출
     * @param token JWT 토큰
     * @return 사용자 ID (subject)
     */
    public static String getSubject(String token) {
        return validateToken(token).getSubject();
    }
}