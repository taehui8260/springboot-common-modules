package com.taehui.common.excel;


import com.taehui.common.excel.model.ExcelData;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelServiceTest {

    private ExcelService excelService;
    private ExcelUtil excelUtil;

    //각 테스트 메서드 실행 전에 초기화
    @BeforeEach
    void setUp() {
        excelService = new ExcelService();
        excelUtil = new ExcelUtil();
    }
    @Test
    void addExcelData(){
        ExcelData excel = new ExcelData();
        List<Map<String, Object>> data = Arrays.asList(
                Map.of("ID", 1, "Name", "Name_1", "Age", 22, "Country", "Germany", "Salary", 52199),
                Map.of("ID", 2, "Name", "Name_2", "Age", 38, "Country", "Canada", "Salary", 46658),
                Map.of("ID", 3, "Name", "Name_3", "Age", 34, "Country", "Australia", "Salary", 41041),
                Map.of("ID", 4, "Name", "Name_4", "Age", 37, "Country", "UK", "Salary", 96505),
                Map.of("ID", 5, "Name", "Name_5", "Age", 41, "Country", "Germany", "Salary", 84518),
                Map.of("ID", 6, "Name", "Name_6", "Age", 31, "Country", "USA", "Salary", 57101),
                Map.of("ID", 7, "Name", "Name_7", "Age", 26, "Country", "USA", "Salary", 43227),
                Map.of("ID", 8, "Name", "Name_8", "Age", 41, "Country", "USA", "Salary", 83053),
                Map.of("ID", 9, "Name", "Name_9", "Age", 27, "Country", "Germany", "Salary", 54043),
                Map.of("ID", 10, "Name", "Name_10", "Age", 27, "Country", "USA", "Salary", 71164)
        );

        List<String> headerList = new ArrayList<>(List.of(new String[]{"ID", "Name", "Age", "Country", "Salary"}));
        List<String> columnList = new ArrayList<>(List.of(new String[]{"ID", "Name", "Age", "Country", "Salary"}));

        excelService.addExcelData(excel, "sheet", "title", data, headerList, columnList);
    }

    @Test
    void testExcelDownload() throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();
        ExcelData excel = new ExcelData();

        List<Map<String, Object>> data = Arrays.asList(
                Map.of("ID", 1, "Name", "Name_1", "Age", 22, "Country", "Germany", "Salary", 52199),
                Map.of("ID", 2, "Name", "Name_2", "Age", 38, "Country", "Canada", "Salary", 46658),
                Map.of("ID", 3, "Name", "Name_3", "Age", 34, "Country", "Australia", "Salary", 41041),
                Map.of("ID", 4, "Name", "Name_4", "Age", 37, "Country", "UK", "Salary", 96505),
                Map.of("ID", 5, "Name", "Name_5", "Age", 41, "Country", "Germany", "Salary", 84518),
                Map.of("ID", 6, "Name", "Name_6", "Age", 31, "Country", "USA", "Salary", 57101),
                Map.of("ID", 7, "Name", "Name_7", "Age", 26, "Country", "USA", "Salary", 43227),
                Map.of("ID", 8, "Name", "Name_8", "Age", 41, "Country", "USA", "Salary", 83053),
                Map.of("ID", 9, "Name", "Name_9", "Age", 27, "Country", "Germany", "Salary", 54043),
                Map.of("ID", 10, "Name", "Name_10", "Age", 27, "Country", "USA", "Salary", 71164)
        );

        List<String> headerList = List.of("ID", "Name", "Age", "Country", "Salary");
        List<String> columnList = List.of("ID", "Name", "Age", "Country", "Salary");

        excelService.addExcelData(excel, "sheet", "", data, headerList, columnList);


        // Act
        MockHttpServletResponse response = new MockHttpServletResponse();
        try (Workbook workbook = excelUtil.createExcel(excel.getTitle(), excel.getExcelData(), excel.getHeaderList(), excel.getColumnList())) {
            //컬럼 색 변경
            for(String titleKey: excel.getTitle().keySet()){
                int columnIndex = 0;
                for(String column: excel.getColumnList().get(titleKey)){
                    if(column.contains("Salary")){
                        excelUtil.setColumnColor(workbook.getSheet(titleKey), columnIndex, IndexedColors.YELLOW);
                    }
                    columnIndex++;
                }
            }

            excelService.excelDownload(response, workbook, "TestExcel");

            // Assert
            assertEquals("attachment; filename=TestExcel.xlsx", response.getHeader("Content-Disposition"));
            assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", response.getContentType());

            byte[] excelData = response.getContentAsByteArray();
            assertTrue(excelData.length > 0);

            // 데이터를 파일로 저장 (선택)
            try (FileOutputStream fos = new FileOutputStream("TestExcel.xlsx")) {
                fos.write(excelData);
            }

            // 엑셀 파일 검증
            try (Workbook downloadedWorkbook = new XSSFWorkbook(new ByteArrayInputStream(excelData))) {
                assertNotNull(downloadedWorkbook.getSheet("sheet"));
                assertEquals("sheet", downloadedWorkbook.getSheetAt(0).getSheetName());
            }
        }
    }
}
