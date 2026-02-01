package me.ihor.derevtsov.customer;

import lombok.AllArgsConstructor;
import me.ihor.derevtsov.amqp.RabbitMQMessageProducer;
import me.ihor.derevtsov.clients.fraud.FraudCheckResponse;
import me.ihor.derevtsov.clients.fraud.FraudClient;
import me.ihor.derevtsov.clients.notification.NotificationClient;
import me.ihor.derevtsov.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        // TODO: check if email valid
        // TODO: check if email not taken
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster!");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi dear %s, welcome to course", customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
                );
    }
}
