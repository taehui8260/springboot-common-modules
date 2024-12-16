package com.taehui.common.utils;

/**
 * 문자열 처리 유틸리티 클래스
 */
public class StringUtils {
    /**
     * 문자열이 null 또는 빈 문자열인지 확인
     * @param str 확인할 문자열
     * @return null 또는 빈 문자열일 경우 true
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 문자열이 null 또는 빈 문자열이 아닌지 확인
     * @param str 확인할 문자열
     * @return null 또는 빈 문자열이 아닐 경우 true
     */
    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    /**
     * 문자열을 Camel Case로 변환
     * @param str 입력 문자열 (예: "hello_world")
     * @return 변환된 Camel Case 문자열 (예: "helloWorld")
     */
    public static String toCamelCase(String str) {
        if (isNullOrEmpty(str)) return str;
        String[] parts = str.split("_");
        StringBuilder camelCaseString = new StringBuilder(parts[0].toLowerCase());
        for (int i = 1; i < parts.length; i++) {
            camelCaseString.append(parts[i].substring(0, 1).toUpperCase())
                    .append(parts[i].substring(1).toLowerCase());
        }
        return camelCaseString.toString();
    }

    /**
     * 문자열을 Snake Case로 변환
     * @param str 입력 문자열 (예: "helloWorld")
     * @return 변환된 Snake Case 문자열 (예: "hello_world")
     */
    public static String toSnakeCase(String str) {
        if (isNullOrEmpty(str)) return str;
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }

    /**
     * 문자열의 첫 글자를 대문자로 변환
     * @param str 입력 문자열
     * @return 변환된 문자열 (첫 글자가 대문자)
     */
    public static String capitalize(String str) {
        if (isNullOrEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    /**
     * 문자열을 뒤집음
     * @param str 입력 문자열
     * @return 뒤집힌 문자열
     */
    public static String reverse(String str) {
        if (isNullOrEmpty(str)) return str;
        return new StringBuilder(str).reverse().toString();
    }
}

