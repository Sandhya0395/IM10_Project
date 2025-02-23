package pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LighthouseUtility
{
    public static void chromeDebug() throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "chrome-debug --port=9222");
        builder.redirectErrorStream(true);
        Process p = builder.start();
    }

    public static void lighthouseAudit(String URL, String ReportName) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "lighthouse", URL, "--port=9222",
                "--preset=desktop", "--output=html", "--output-path=target/" + ReportName + ".html");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }

}
