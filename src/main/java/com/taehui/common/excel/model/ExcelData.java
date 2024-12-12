package com.taehui.common.excel.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class ExcelData {
    /* sheet 최상단에 보여진 제목 */
    LinkedHashMap<String, String> title;

    /* 엑셀 데이터 */
    LinkedHashMap<String, List<Map<String, Object>>> excelData;

    /* 헤더 정보 */
    LinkedHashMap<String, List<String>> headerList;

    /* 컬럼 정보 */
    LinkedHashMap<String, List<String>> columnList;
}
