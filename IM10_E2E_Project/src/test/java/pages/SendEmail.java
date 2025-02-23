package pages;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

    public static void sendEmail(String to, String Cc, String subject, String body, String attachmentPath) {
        // Assuming you are sending email from through outlook
        String host = "smtpout.secureserver.net";
        final String username = "info@im10.in"; // change to your email
        final String password = "5q8le5rT$"; // change to your password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(Cc));
            message.setSubject(subject);


            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            //messageBodyPart.setContent(summaryReportContent.toString(),"text/html");

            // Replace newlines with <br> tags
            String formattedBody = body.replace("\n", "<br>");
            messageBodyPart.setContent(formattedBody + "<br><br>", "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attachment
            if (attachmentPath != null && !attachmentPath.isEmpty()) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                attachmentBodyPart.attachFile(attachmentPath);
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Set the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void sendSummaryReport(String attachmentPath) throws IOException, MessagingException {

        // Convert sheet data to HTML table
        StringBuilder emailContent = new StringBuilder();
        emailContent.append("<html><body><table border='1'>");

        // Attachment
        if (attachmentPath != null && !attachmentPath.isEmpty()) {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(attachmentPath);
            Multipart multipart = null;
            multipart.addBodyPart(attachmentBodyPart);
        }

    }*/
}
