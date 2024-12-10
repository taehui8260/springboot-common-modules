package com.taehui.common.excel.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExcelData {
    /* sheet 최상단에 보여진 제목 */
    Map<String, String> title;

    /* 엑셀 데이터 */
    Map<String, List<Map<String, Object>>> excelData;

    /* 헤더 정보 */
    Map<String, List<String>> headerList;

    /* 컬럼 정보 */
    Map<String, List<String>> columnList;
}
