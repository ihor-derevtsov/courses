package org.smoketea.notification.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smoketea.clients.notification.NotificationRequest;
import org.smoketea.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

	private final NotificationService notificationService;

	@RabbitListener(queues = "${rabbitmq.queues.notification}")
	public void consumer(NotificationRequest notificationRequest) {
		log.info("Consumed {} from queue", notificationRequest);
		notificationService.send(notificationRequest);
	}

}
