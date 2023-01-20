package kg.megacom.beauty_salon.controller;

import io.swagger.annotations.Api;
import kg.megacom.beauty_salon.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@Api(tags = "Email")
public class EmailController {
    @Autowired
    EmailSenderService emailSenderService;

//    @PostMapping("/send-email")
//    public ResponseEntity sendEmail(@RequestBody String email ) throws IOException, MessagingException {
//        this.emailSenderService.sendEmail(email);
//        return ResponseEntity.ok("Success");
//    }
}
