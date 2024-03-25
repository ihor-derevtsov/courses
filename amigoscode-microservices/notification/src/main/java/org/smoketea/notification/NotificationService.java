package org.smoketea.notification;

import lombok.AllArgsConstructor;
import org.smoketea.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

	private final NotificationRepository notificationRepository;

	void send(NotificationRequest notificationRequest) {
		notificationRepository.save(
				Notification.builder()
						.toCustomerId(notificationRequest.toCustomerId())
						.toCustomerEmail(notificationRequest.toCustomerEmail())
						.sender(notificationRequest.sender())
						.message(notificationRequest.message())
						.sentAt(LocalDateTime.now())
						.build()
		);
	}

}
