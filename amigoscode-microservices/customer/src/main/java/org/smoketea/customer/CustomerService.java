package org.smoketea.customer;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.smoketea.clients.fraud.FraudCheckResponse;
import org.smoketea.clients.fraud.FraudClient;
import org.smoketea.clients.notification.NotificationClient;
import org.smoketea.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

	private final FraudClient fraudClient;
	private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        customerRepository.saveAndFlush(customer);

		//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
		//                "http://FRAUD/api/v1/fraud-check/{customerId}",
		//                FraudCheckResponse.class,
		//                customer.getId()
		//        );
		// replaced with
		FraudCheckResponse fraudCheckResponse =
				fraudClient.isFraudster(customer.getId());

		notificationClient.sendNotification(
				new NotificationRequest(
						customer.getId(),
						customer.getEmail(),
						customer.getFullName(),
						"Hi, it's message from " + customer.getFirstName()
				)
		);

		if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster!");
        }

    }
}
