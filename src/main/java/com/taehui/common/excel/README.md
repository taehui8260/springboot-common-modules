# 엑셀 모듈 사용법

## 엑셀 다운로드 예제
아래와 같은 방식으로 `ExcelUtil`, `ExcelService`를 사용하여 엑셀 다운로드 API를 구현할 수 있습니다:

```java

@Autowired
ExcelService excelService;

 @GetMapping("/excel-download")
    public void excelDownload(
        final HttpServletRequest request,
        final HttpServletResponse response        
    ) throws IOException {
        ExcelUtil excelUtil = new ExcelUtil();

        ExcelData excel = new EacelData();
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
        Workbook workbook = excelUtil.createExcel(excel.getTitle(), excel.getExcelData(), excel.getHeaderList(), excel.getColumnList());
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
        excelService.excelDownload(request, response, workbook, "엑셀 데이터");
    }
```
