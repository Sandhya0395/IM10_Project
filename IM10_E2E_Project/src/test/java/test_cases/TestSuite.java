package test_cases;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSuite {

    public static void main(String[] args) {
        List<TestResult> testResults = new ArrayList<>();

        // Example of executing test cases and storing results
        testResults.add(new TestResult("Login Suite", "Pass", "Login successful"));
        testResults.add(new TestResult("Search Suite", "Fail", "Search functionality not working"));

        // Write the summary report
        generateSummaryReport(testResults, "test-results.xlsx");
    }

    private static void generateSummaryReport(List<TestResult> testResults, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test Suite");
        headerRow.createCell(1).setCellValue("Result");
        headerRow.createCell(2).setCellValue("Comment");

        // Create data rows
        int rowNum = 1;
        for (TestResult result : testResults) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(result.getTestSuite());
            row.createCell(1).setCellValue(result.getResult());
            row.createCell(2).setCellValue(result.getComment());
        }

        // Write the output to a file
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Test results written successfully to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define TestResult class (assuming it's similar to what you need)
    static class TestResult {
        private String testSuite;
        private String result;
        private String comment;

        public TestResult(String testSuite, String result, String comment) {
            this.testSuite = testSuite;
            this.result = result;
            this.comment = comment;
        }

        public String getTestSuite() {
            return testSuite;
        }


        public String getResult() {
            return result;
        }

        public String getComment() {
            return comment;
        }
    }
}
