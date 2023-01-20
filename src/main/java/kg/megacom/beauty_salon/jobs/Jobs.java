package kg.megacom.beauty_salon.jobs;

import kg.megacom.beauty_salon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Jobs {
    @Autowired
    private OrderService orderService;

    //every hour
    @Scheduled(cron = "0 * * * * ")
    private void setInProcessOrders(){
        System.out.println(LocalDateTime.now());
     orderService.checkSuspendOrders();
    }
}
