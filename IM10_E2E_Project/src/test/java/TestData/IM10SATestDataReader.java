package TestData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class IM10SATestDataReader {

    // HashMap to store test data
    static HashMap<String, String> dataMap;

    // Method to read test data from Excel file
    public static HashMap<String, String> readTestData(String testDataFilePath, int sheetNumber) throws IOException {
        dataMap = new HashMap<>();
        File file = new File(testDataFilePath);
        FileInputStream inputStream = new FileInputStream(file);

        // Load the Excel workbook
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        // Get the specified sheet
        XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

        // Iterator to traverse rows in the sheet
        Iterator<Row> rowIterator = sheet.iterator();
        // Get the header row
        Row headerRow = rowIterator.next();
        // Get the number of columns
        int columnCount = headerRow.getPhysicalNumberOfCells();
        // Array to store column names
        String[] columns = new String[columnCount];

        // Extract column names from the header row
        for (int i = 0; i < columnCount; i++) {
            Cell cell = headerRow.getCell(i);
            columns[i] = cell.getStringCellValue();
        }

        // Check if there is data available
        if (rowIterator.hasNext()) {
            // Get the data row
            Row dataRow = rowIterator.next();
            // Array to store row data
            String[] rowData = new String[columnCount];

            // Extract data from the data row
            for (int i = 0; i < columnCount; i++) {
                Cell cell = dataRow.getCell(i);
                rowData[i] = cell.toString();
            }

            // Map column names to corresponding data values
            for (int i = 0; i < columns.length; i++) {
                dataMap.put(columns[i], rowData[i]);
            }

            // Print the HashMap containing test data
            System.out.println("Data in HashMap: " + dataMap);
        }

        // Adjust column size for each column in the sheet
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
        return dataMap;
    }
}
