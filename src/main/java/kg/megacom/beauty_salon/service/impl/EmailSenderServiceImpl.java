package kg.megacom.beauty_salon.service.impl;

import kg.megacom.beauty_salon.network.EmailSender;
import kg.megacom.beauty_salon.service.EmailSenderService;
import lombok.Setter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Override
    public void sendEmail(String email) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.load(EmailSender.class.getClassLoader().getResourceAsStream("application.properties"));

        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("saikal.isakova2308@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Hello from Java Project)");
        message.setText("you have successfully registered");

        Transport transport = mailSession.getTransport();
        transport.connect(null, "tnwnlklrlcvxiytg");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

}
