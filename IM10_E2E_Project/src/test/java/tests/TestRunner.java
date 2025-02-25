//package tests;
//import org.testng.TestNG;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestRunner
//{
//    public static void main(String[] args)
//    {
//        TestNG testng = new TestNG();
//        List<String> suites = new ArrayList<>();
//        suites.add("testng.xml"); // Path to your TestNG XML file
//        testng.setTestSuites(suites);
//        testng.run();
//    }
//}

package tests;

import org.testng.TestNG;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for testng.xml path
        System.out.print("Enter the path to your testng.xml file: ");
        String testngXmlPath = scanner.nextLine();
        scanner.close();

        // Run TestNG with user-provided XML path
        TestNG testng = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(testngXmlPath);
        testng.setTestSuites(suites);
        testng.run();
    }
}
