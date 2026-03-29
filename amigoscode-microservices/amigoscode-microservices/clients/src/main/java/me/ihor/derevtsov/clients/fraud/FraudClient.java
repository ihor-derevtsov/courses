package me.ihor.derevtsov.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud", url = "${clients.fraud.url}")
public interface FraudClient {
    /**
     * Points to api/v1/fraud-check/{customerId} endpoint of FraudController magically
     */
    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
