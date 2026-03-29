package me.ihor.derevtsov.notification;

import me.ihor.derevtsov.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(scanBasePackages = {
        "me.ihor.derevtsov.notification",
        "me.ihor.derevtsov.amqp",
})
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(
//            RabbitMQMessageProducer rabbitMQMessageProducer,
//            NotificationConfig notificationConfig
//    ) {
//        return args -> {
//            rabbitMQMessageProducer.publish(new Person("Ihor", 28), notificationConfig.getInternalExchange(), notificationConfig.getRoutingKey());
//        };
//    }
//
//    record Person(String name, int age) {}
}
