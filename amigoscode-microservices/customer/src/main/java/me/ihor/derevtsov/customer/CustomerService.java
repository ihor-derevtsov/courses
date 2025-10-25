package me.ihor.derevtsov.customer;

import lombok.AllArgsConstructor;
import me.ihor.derevtsov.clients.fraud.FraudCheckResponse;
import me.ihor.derevtsov.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        // TODO: check if email valid
        // TODO: check if email not taken
        // TODO: check if fraudster
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster!");
        }
        // TODO: send notification
    }
}
