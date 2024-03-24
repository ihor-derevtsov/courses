package org.smoketea.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
