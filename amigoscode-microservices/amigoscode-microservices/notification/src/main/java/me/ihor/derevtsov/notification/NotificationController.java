package me.ihor.derevtsov.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ihor.derevtsov.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("New notification: {}", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
