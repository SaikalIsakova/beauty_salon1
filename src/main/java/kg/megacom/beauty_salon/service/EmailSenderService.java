package kg.megacom.beauty_salon.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

public interface EmailSenderService {
    void sendEmail(String email) throws IOException, MessagingException;
}
