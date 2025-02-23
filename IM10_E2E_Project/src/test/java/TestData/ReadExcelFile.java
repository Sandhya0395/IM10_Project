package TestData;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcelFile {
    public static void main(String[] args) throws IOException {
        // Path to your Excel file
        String filePath = "D:\\Automation Testing\\PageObjectModel\\IM10ExcelReport.xls";

        // Create a FileInputStream to read from the Excel file
        FileInputStream fis = new FileInputStream(filePath);

        // Create a workbook instance for the Excel file
        Workbook workbook = WorkbookFactory.create(fis);

        // Get the first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through each row of the sheet
        for (Row row : sheet) {
            // Iterate through each cell of the row
            for (Cell cell : row) {
                // Get the value of the cell and print it
                //System.out.print(cell.toString() + "\t");

                // Get the value of the cell
                String cellValue = cell.toString();
                // Adjust the spacing to align the data
                System.out.print(String.format("%-20s", cellValue)); // Adjust spacing as needed
            }
            // Move to the next line after printing all cells of the row
            System.out.println();
        }

        // Close the workbook and file input stream
        workbook.close();
        fis.close();
    }
}
