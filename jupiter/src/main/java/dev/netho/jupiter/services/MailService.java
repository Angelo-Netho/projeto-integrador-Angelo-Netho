package dev.netho.jupiter.services;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class MailService {

    private String generateTempPassword() {

        Random random = new Random();
        int number = random.nextInt(999999);

        return String.format("%06d", number);
    }

    public void sendRecoveryMail(String recoveryAddress) {

        String username = "jupiter.recovery@gmail.com";
        String password = "Salada123!";
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

            Address[] toUser = InternetAddress.parse(recoveryAddress);

            message.setRecipients(Message.RecipientType.TO, toUser);

            message.setSubject("Recuperação de conta");

            String tempPassword = generateTempPassword();

            message.setText("Sua senha temporária é: " + tempPassword +
                    "\nEstá senha é valida durante 10 minutos.");

            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Recovery password sent to " + recoveryAddress);

        }catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }
}
