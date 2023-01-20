package kg.megacom.beauty_salon.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.util.Date;

public interface EmailSenderService {

    void sendEmail(String email,String name, Date appointmentDate, int confirmCode) throws IOException, MessagingException;

}

