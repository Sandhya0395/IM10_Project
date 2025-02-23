package pages;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    public static void main(String[] args) throws IOException {
        // Read Excel file
        FileInputStream file = new FileInputStream("D:\\Automation Testing\\PageObjectModel\\report\\summaryReport");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Convert sheet data to HTML table
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<html><body><table border='1'>");

        for (Row row : sheet) {
            emailContent.append("<tr>");
            for (Cell cell : row) {
                emailContent.append("<td>").append(cell.toString()).append("</td>");
            }
            emailContent.append("</tr>");
        }

        emailContent.append("</table></body></html>");
        workbook.close();

        // Send email
        sendEmail(emailContent.toString());
    }

    public static void sendEmail(String emailContent) {

        // Assuming you are sending email from through outlook
        String host = "smtpout.secureserver.net";
        final String username = "info@im10.in"; // change to your email
        final String password = "5q8le5rT$"; // change to your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set Subject: header field
            message.setSubject("Admin Test Suite Results");

            // Send the actual HTML message, as big as you like
            message.setContent(emailContent, "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

