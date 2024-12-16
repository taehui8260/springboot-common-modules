package com.taehui.common.utils;

/**
 * 데이터 유효성 검사 유틸리티 클래스
 */
public class ValidationUtils {
    /**
     * 이메일 주소 형식 검증
     * @param email 검증할 이메일 주소
     * @return 유효한 이메일 형식일 경우 true
     */
    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && email.matches(regex);
    }

    /**
     * 전화번호 형식 검증
     * @param phoneNumber 검증할 전화번호
     * @return 유효한 전화번호 형식일 경우 true
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+?[0-9]{10,15}$";
        return phoneNumber != null && phoneNumber.matches(regex);
    }

    /**
     * 비밀번호 형식 검증 (최소 8자, 하나 이상의 문자 및 숫자 포함)
     * @param password 검증할 비밀번호
     * @return 유효한 비밀번호 형식일 경우 true
     */
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        return password != null && password.matches(regex);
    }
}

