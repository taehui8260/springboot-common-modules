package com.taehui.common.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

public class ExcelUtil {
    Workbook workbook;
    CellStyle styleHead;
    CellStyle styleSubtitle;
    CellStyle styleCelltitle;
    CellStyle styleBody;
    CellStyle styleCustomColumn;
    Font font;

    public ExcelUtil(){
        this.workbook = new XSSFWorkbook();

        //스타일 객체 생성
        this.styleHead = workbook.createCellStyle();//제목 스타일
        this.styleSubtitle = workbook.createCellStyle();//부제목 스타일
        this.styleCelltitle = workbook.createCellStyle();//부제목 스타일
        this.styleBody = workbook.createCellStyle(); //내용 스타일
        this.styleCustomColumn = workbook.createCellStyle(); //커스텀 컬럼 스타일

        //제목 폰트
        this.font = workbook.createFont();
        this.font.setFontHeightInPoints((short) 20);
        this.font.setBold(true);

        //제목 스타일에 폰트 적용, 정렬
        this.styleHead.setFont(font);
        this.styleHead.setAlignment(HorizontalAlignment.CENTER);
        this.styleHead.setVerticalAlignment(VerticalAlignment.CENTER);

        //부제목 스타일 설정
        this.styleSubtitle.setAlignment(HorizontalAlignment.LEFT);
        this.styleSubtitle.setVerticalAlignment(VerticalAlignment.CENTER);
        this.styleSubtitle.setWrapText(true); // 줄 바꿈

        //부제목 스타일 설정
        this.styleCelltitle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        this.styleCelltitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        this.styleCelltitle.setBorderBottom(BorderStyle.THIN);
        this.styleCelltitle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCelltitle.setBorderLeft(BorderStyle.THIN);//.BORDER_THIN);
        this.styleCelltitle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCelltitle.setBorderRight(BorderStyle.THIN);//.BORDER_THIN);
        this.styleCelltitle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCelltitle.setBorderTop(BorderStyle.THIN);
        this.styleCelltitle.setAlignment(HorizontalAlignment.CENTER);
        this.styleCelltitle.setVerticalAlignment(VerticalAlignment.CENTER);
        this.styleCelltitle.setWrapText(true); // 줄 바꿈

        //내용 스타일 설정. Cell 색깔, 무늬 채우기
        this.styleBody.setBorderBottom(BorderStyle.THIN);
        this.styleBody.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        this.styleBody.setBorderLeft(BorderStyle.THIN);
        this.styleBody.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        this.styleBody.setBorderRight(BorderStyle.THIN);
        this.styleBody.setRightBorderColor(IndexedColors.BLACK.getIndex());
        this.styleBody.setBorderTop(BorderStyle.THIN);
        this.styleBody.setAlignment(HorizontalAlignment.CENTER);
        this.styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
        this.styleBody.setWrapText(true); // 줄 바꿈

        //커스텀 컬럼 스타일 설정. Cell 색깔, 무늬 채우기
        this.styleCustomColumn.setBorderBottom(BorderStyle.THIN);
        this.styleCustomColumn.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCustomColumn.setBorderLeft(BorderStyle.THIN);
        this.styleCustomColumn.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCustomColumn.setBorderRight(BorderStyle.THIN);
        this.styleCustomColumn.setRightBorderColor(IndexedColors.BLACK.getIndex());
        this.styleCustomColumn.setBorderTop(BorderStyle.THIN);
        this.styleCustomColumn.setAlignment(HorizontalAlignment.CENTER);
        this.styleCustomColumn.setVerticalAlignment(VerticalAlignment.CENTER);
        this.styleCustomColumn.setWrapText(true); // 줄 바꿈
    }

    /**
     * 엑셀 만들기
     *
     * @param title
     * @param excelData
     * @param headerList
     * @param columnList
     * @return
     */
    public Workbook createExcel(Map<String, String> title, Map<String, List<Map<String,Object>>> excelData, Map<String, List<String>> headerList, Map<String, List<String>> columnList){
        return this.createExcel(title, excelData, headerList, columnList, "null");
    }

    /**
     * 엑셀 만들기(null 값 대체)
     * @param title
     * @param excelData
     * @param headerList
     * @param columnList
     * @param changeNull
     * @return
     */
    public Workbook createExcel(Map<String, String> title, Map<String,List<Map<String,Object>>> excelData, Map<String, List<String>> headerList, Map<String, List<String>> columnList, String changeNull){

        /**
         * title: sheet 최상단에 보여질 제목
         * headerList: 헤더 정보 (sheet_key에 해당하는 헤더 정보로 cell 헤더에 보여질 정보(headerList.get("key").size()==columnList.get("key").size())
         * columnList: 컬럼 정보 (sheet_key에 해당하는 컬럼정보 정보를 가지고 excelData에서 컬럼 값을 추출.(headerList.get("key").size()==columnList.get("key").size())
         * excelData: 엑셀 데이터(sheet_key와 데이터 List가 매핑되어 저장되어있는 데이터. excelData.get("key").size() 만큼의 sheet 생성)
         * EX) excelData.get("sheet1")에 들어있는 내용을 엑셀로 저장할때 해당 데이터의 헤더는 headerList.get("sheet1")에 들어있고
         *  컬럼 정보는 columnList.get("shee1")의 값들을 각각 컬럼의 키값으로 이용해 excelData.get("sheet1")[0] ~ [excelData.get("sheet1").size[]]의 데이터를 조회한다.
         */

        Row row = null;
        Cell cell = null;

        for (String sheet_key : excelData.keySet()) {

            // Sheet 생성
            Sheet sheet1 = workbook.createSheet(sheet_key);

            List<Map<String, Object>> data = excelData.get(sheet_key);

            //첫 번째 줄에 title
            row = sheet1.createRow(0);
            row.setHeight((short) 650);
            cell = row.createCell(0);
            cell.setCellValue(title.get(sheet_key));
            cell.setCellStyle(styleHead);

            sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, headerList.get(sheet_key).size() - 1));

            row = sheet1.createRow(1);

            // 두 번째 줄에 Cell 설정하기(컬럼명)
            for (int i = 0; i < headerList.get(sheet_key).size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(headerList.get(sheet_key).get(i));
                cell.setCellStyle(styleCelltitle);
            }
            // 세 번째 줄에 Cell 설정하기(내용)
            for (int i = 0; i < data.size(); i++) {
                row = sheet1.createRow(i + 2);
                for (int u = 0; u < columnList.get(sheet_key).size(); u++) {
                    Object value = data.get(i).get(columnList.get(sheet_key).get(u));
                    cell = row.createCell(u);
                    cell.setCellValue(String.valueOf(value == null ? changeNull : value));
                    cell.setCellStyle(styleBody);
                }
            }
            //컬럼 너비 설정
            for (int j = 0; j < headerList.get(sheet_key).size(); j++) {
                sheet1.setColumnWidth(j, 6000);
            }
        }
        return workbook;
    }

    /**
     * 특정 컬럼의 색 수정
     *
     * @param sheet
     * @param columnIndex
     * @param indexedColors
     */
    public void setColumnColor(Sheet sheet, int columnIndex, IndexedColors indexedColors){
        int startRowNum = 2;
        int lastRowNum = sheet.getLastRowNum();
        this.setColumnColor(sheet, columnIndex, indexedColors, startRowNum, lastRowNum);
    }

    /**
     * 특정 컬럼의 색 수정
     *
     * @param sheet
     * @param columnIndex
     * @param indexedColors
     * @param startRowNum
     * @param lastRowNum
     */
    public void setColumnColor(Sheet sheet, int columnIndex, IndexedColors indexedColors, int startRowNum, int lastRowNum){
        this.styleCustomColumn.setFillForegroundColor(indexedColors.getIndex());
        this.styleCustomColumn.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for(int i = startRowNum; i <= Math.min(sheet.getLastRowNum(), lastRowNum); i++){
            Cell cell = sheet.getRow(i).getCell(columnIndex);
            if(cell != null){
                cell.setCellStyle(this.styleCustomColumn);
            }
        }
    }
}
