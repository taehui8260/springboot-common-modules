package com.taehui.common.excel;

import com.taehui.common.excel.model.ExcelData;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * ExcelData 객체 추가
     *
     * sheet에 해당하는 제목, 헤더, 컬럼, 엑셀데이터를 할당한다.
     *
     * @param excelData
     * @param sheet
     * @param title
     * @param headerList
     * @param columnList
     * @param rawData
     */
    public void addExcelData(ExcelData excelData, String sheet, String title, List<Map<String, Object>> rawData, List<String> headerList, List<String> columnList){
        excelData.getTitle().put(sheet, title);
        excelData.getExcelData().put(sheet, rawData);
        excelData.getHeaderList().put(sheet, headerList);
        excelData.getColumnList().put(sheet, columnList);
    }

    /**
     * 엑셀 다운로드
     *
     * @param response
     * @param workbook
     * @param orgFileName
     * @throws IOException
     */
    public void excelDownload(final HttpServletResponse response, final Workbook workbook, final String orgFileName)
            throws IOException {
        //final String userAgent = request.getHeader("User-Agent");
        String downloadFileName = "";
        List<String> headerList = new ArrayList<>(List.of(new String[]{"1", "2"}));

        //띄어쓰기, ',' 문자 변환
        downloadFileName = URLEncoder.encode(orgFileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20").replaceAll("%2C", "");
        //downloadFileName = orgFileName;
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName + ".xlsx");
        final OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
    }
}
