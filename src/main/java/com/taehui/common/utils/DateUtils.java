package com.taehui.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 날짜/시간 관련 유틸리티 클래스
 */
public class DateUtils {
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 날짜를 주어진 포맷으로 변환
     * @param date 변환할 날짜 객체
     * @param pattern 변환할 포맷 문자열 (예: "yyyy-MM-dd")
     * @return 변환된 날짜 문자열
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 현재 시간을 기본 포맷(yyyy-MM-dd HH:mm:ss)으로 반환
     * @return 현재 시간 문자열
     */
    public static String getCurrentTimestamp() {
        return formatDate(new Date(), DEFAULT_FORMAT);
    }

    /**
     * 두 날짜 간의 차이를 일 단위로 반환
     * @param startDate 시작 날짜
     * @param endDate 종료 날짜
     * @return 날짜 차이 (일 단위)
     */
    public static long getDifferenceInDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) return 0;
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 주어진 날짜가 주말인지 확인
     * @param date 확인할 날짜
     * @return 주말일 경우 true
     */
    public static boolean isWeekend(Date date) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(java.util.Calendar.DAY_OF_WEEK);
        return dayOfWeek == java.util.Calendar.SATURDAY || dayOfWeek == java.util.Calendar.SUNDAY;
    }
}

