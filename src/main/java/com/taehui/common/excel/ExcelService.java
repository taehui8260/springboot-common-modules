package com.taehui.common.excel;

import com.taehui.common.excel.model.ExcelData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelService {
    /**
     * ExcelData 객체 추가
     *
     * rawData를 기반으로 headerList와 columnList를 할당함으로 순서가 보장되는 LinkedHashMap 컬렉션을 사용한다.
     * @param excelData
     * @param sheet
     * @param rawData
     */
    public void addExcelData(ExcelData excelData, String sheet, List<LinkedHashMap<String, Object>> rawData){
        List<String> headerList = new ArrayList<>(rawData.get(0).keySet());

    }

    /**
     * sheet에 해당하는 제목, 헤더, 컬럼, 엑셀데이터를 할당한다.
     *
     * @param excelData
     * @param sheet
     * @param title
     * @param headerList
     * @param columnList
     * @param rawData
     */
    public void addExcelData(ExcelData excelData, String sheet, String title, List<String> headerList, List<String> columnList, List<LinkedHashMap<String, Object>> rawData){

    }
}
