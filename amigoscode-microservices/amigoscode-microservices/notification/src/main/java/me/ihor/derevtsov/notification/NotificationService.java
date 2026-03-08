package me.ihor.derevtsov.notification;

import lombok.AllArgsConstructor;
import me.ihor.derevtsov.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .message(notificationRequest.message())
                        .sender("Amigoscode Microservices")
                        .sentAt(LocalDateTime.now())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .toCustomerId(notificationRequest.toCustomerId())
                        .build()
                );
    }
}
