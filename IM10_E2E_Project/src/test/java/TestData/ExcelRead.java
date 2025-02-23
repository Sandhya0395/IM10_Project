package TestData;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
    public static StringBuilder readExcel(String excelFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
        Sheet sheet = workbook.getSheetAt(0); // Assuming first sheet

        // Create HTML table
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body><table border='1' style='border-collapse: collapse; text-align: center;'>");
        htmlContent.append("<thead><tr>");

        // Header row with grey background color for specific columns
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            String columnName = sheet.getRow(0).getCell(i).getStringCellValue();
            if ("Admin_Test_Suite".equals(columnName) || "Result".equals(columnName) || "Failed_Test".equals(columnName)) {
                htmlContent.append("<th style='background-color: #D3D3D3;'>").append(columnName).append("</th>"); // Grey color code
            } else {
                htmlContent.append("<th>").append(columnName).append("</th>");
            }
        }

        htmlContent.append("</tr></thead><tbody>");

        // Data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            htmlContent.append("<tr>");

            // Iterate over cells in the row
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);

                // Ensure cell is not null before accessing
                if (cell != null) {
                    // Check if the cell is in the "Result" column
                    if ("Result".equals(sheet.getRow(0).getCell(j).getStringCellValue())) {
                        String cellValue = cell.getStringCellValue().trim(); // Trim to remove any leading/trailing whitespace
                        if ("Pass".equalsIgnoreCase(cellValue)) {
                            htmlContent.append("<td style='background-color: #90EE90;'>").append(cellValue).append("</td>"); // Light green color code
                        } else if ("Fail".equalsIgnoreCase(cellValue)) {
                            htmlContent.append("<td style='background-color: #FC1401;'>").append(cellValue).append("</td>"); // Red color code
                        } else {
                            htmlContent.append("<td>").append(cellValue).append("</td>");
                        }
                    } else {
                        // For other columns
                        if (cell.getStringCellValue() == "Admin_Test_Suite") {
                            htmlContent.append("<td>").append(cell.getStringCellValue()).append("</td>");
                        } else if (cell.getStringCellValue() == "Failed_Test") {
                            htmlContent.append("<td>").append(cell.getNumericCellValue()).append("</td>");
                        } else {
                            htmlContent.append("<td>").append(cell).append("</td>");
                        }
                    }
                } else {
                    htmlContent.append("<td></td>");
                }
            }
            htmlContent.append("</tr>");
        }

        htmlContent.append("</tbody></table></body></html>");
        workbook.close();
        return htmlContent;
    }
}
