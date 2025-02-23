package TestData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static HashMap<String, HashMap<String, String>> readExcelIntoHashMap(String filePath) throws IOException {
        HashMap<String, HashMap<String, String>> dataMap = new HashMap<>();

        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

        // Assuming the first row contains the headers, so we skip it
        int rowCount = sheet.getLastRowNum();
        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String testOrder = getStringValue(row.getCell(0));
                HashMap<String, String> rowData = new HashMap<>();

                // Starting from cell index 1, as we skip the first cell which contains the test order
                for (int j = 1; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // Assuming you want to skip empty cells
                        String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
                        String cellValue = getStringValue(cell);
                        if (!cellValue.isEmpty()) {
                            rowData.put(columnName, cellValue);
                        }
                    }
                }

                dataMap.put(testOrder, rowData);
            }
        }

        workbook.close();
        fis.close();

        return dataMap;
    }

    private static String getStringValue(Cell cell) {
        if (cell == null)
            return "null";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        try {
            HashMap<String, HashMap<String, String>> data = readExcelIntoHashMap("D:\\Automation Testing\\PageObjectModel\\IM10_API_Test_Automation.xlsx");

            // Exclude ={} entries and Test_Order entry
            data.entrySet().removeIf(entry -> entry.getKey().contains("={}") || entry.getKey().equals("Test_Order"));

            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
