package ExcelResults;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class ExcelReportGenerator {

    static Object[] objArr;

    public static void generateExcelReport(String sheetName, String fileName, String directory, HashMap<Integer, Object[]> map) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(); // Create a new workbook
        Sheet sheet = workbook.createSheet(sheetName); // Create a new sheet

        Set<Integer> keyset = map.keySet();

        int rownum = 0;

        // Create cell style for borders
        XSSFCellStyle borderStyle = workbook.createCellStyle();
        borderStyle.setBorderTop(BorderStyle.THIN);
        borderStyle.setBorderBottom(BorderStyle.THIN);
        borderStyle.setBorderLeft(BorderStyle.THIN);
        borderStyle.setBorderRight(BorderStyle.THIN);

        // Create cell style for green background with centered alignment
        XSSFCellStyle greenBackgroundStyle = workbook.createCellStyle();
        greenBackgroundStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 255, 0), null));
        greenBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        greenBackgroundStyle.setAlignment(HorizontalAlignment.CENTER);
        greenBackgroundStyle.setBorderTop(BorderStyle.THIN);
        greenBackgroundStyle.setBorderBottom(BorderStyle.THIN);
        greenBackgroundStyle.setBorderLeft(BorderStyle.THIN);
        greenBackgroundStyle.setBorderRight(BorderStyle.THIN);

        // Create cell style for red background with centered alignment
        XSSFCellStyle redBackgroundStyle = workbook.createCellStyle();
        redBackgroundStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 0, 0), null));
        redBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        redBackgroundStyle.setAlignment(HorizontalAlignment.CENTER);
        redBackgroundStyle.setBorderTop(BorderStyle.THIN);
        redBackgroundStyle.setBorderBottom(BorderStyle.THIN);
        redBackgroundStyle.setBorderLeft(BorderStyle.THIN);
        redBackgroundStyle.setBorderRight(BorderStyle.THIN);

        // Create cell style for grey background with centered alignment
        XSSFCellStyle greyBackgroundStyle = workbook.createCellStyle();
        greyBackgroundStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(192, 192, 192), null));
        greyBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        greyBackgroundStyle.setAlignment(HorizontalAlignment.CENTER);
        greyBackgroundStyle.setBorderTop(BorderStyle.THIN);
        greyBackgroundStyle.setBorderBottom(BorderStyle.THIN);
        greyBackgroundStyle.setBorderLeft(BorderStyle.THIN);
        greyBackgroundStyle.setBorderRight(BorderStyle.THIN);

        // Create cell style for centered text with borders
        XSSFCellStyle centeredStyle = workbook.createCellStyle();
        centeredStyle.setAlignment(HorizontalAlignment.CENTER);
        centeredStyle.setBorderTop(BorderStyle.THIN);
        centeredStyle.setBorderBottom(BorderStyle.THIN);
        centeredStyle.setBorderLeft(BorderStyle.THIN);
        centeredStyle.setBorderRight(BorderStyle.THIN);

        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);

            objArr = map.get(key);

            int cellnum = 0;

            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);

                if (obj instanceof String) {
                    cell.setCellValue((String) obj);

                    // Apply green background if the value is "Pass" and red if "Fail" in the Result column
                    if (cellnum == objArr.length)
                    {
                        if ("Pass".equals(obj))
                        {
                            cell.setCellStyle(greenBackgroundStyle);
                        }
                        else if ("Fail".equals(obj))
                        {
                            cell.setCellStyle(redBackgroundStyle);
                        }
                        else
                        {
                            cell.setCellStyle(borderStyle);
                        }
                    }
                    else
                    {
                        cell.setCellStyle(borderStyle);
                    }
                }
                else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                    cell.setCellStyle(borderStyle);
                }

                // Apply grey background style to the first row (column names)
                if (rownum == 1) {
                    cell.setCellStyle(greyBackgroundStyle);
                }

                // Apply centered style to the serial number column
                if (cellnum == 1 && rownum != 1) {
                    cell.setCellStyle(centeredStyle);
                }
            }
        }

        // Adjust column size for each column in the sheet
        for (int i = 0; i < objArr.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(directory + "\\" + fileName)) {
            workbook.write(fileOut);
        }

        // Closing the workbook
        workbook.close();
    }
}
