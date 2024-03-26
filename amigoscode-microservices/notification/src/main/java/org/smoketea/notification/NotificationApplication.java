package org.smoketea.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
		scanBasePackages = {
				"org.smoketea.notification",
				"org.smoketea.amqp"
		}
)
@EnableEurekaClient
public class NotificationApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

// Just check if RabbitMQ working
//	@Bean
//	CommandLineRunner commandLineRunner(
//			RabbitMQMessageProducer rabbitMQMessageProducer,
//			NotificationConfig notificationConfig
//	) {
//		return args -> {
//			rabbitMQMessageProducer.publish(
//					new Person("Ali", 27),
//					notificationConfig.getInternalExchange(),
//					notificationConfig.getInternalNotificationRoutingKey());
//		};
//	}
//
//	record Person(String name, int age) {
//	}

}
