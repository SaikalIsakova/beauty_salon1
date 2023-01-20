package kg.megacom.beauty_salon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeautySalonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeautySalonApplication.class, args);
	}

}
