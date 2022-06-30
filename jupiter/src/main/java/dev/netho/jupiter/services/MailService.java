package dev.netho.jupiter.services;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class MailService {

    public void sendNotificationEmail(String psychologistAddress, String fileName, String name) {

        String username = "";
        String password = "";
        String host = "smtp.gmail.com";
        String port = "587";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(properties);

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            Address[] toUser = InternetAddress.parse(psychologistAddress);

            message.setRecipients(Message.RecipientType.TO, toUser);

            message.setSubject("Relatório pacientes");

            message.setText("Seu relatório está pronto," + name + ".");

            message.setFileName(fileName);

            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Notification email sent to:" + psychologistAddress);

        }catch (MessagingException exception) {
            System.out.println("Email não enviado");
        }
    }
}
