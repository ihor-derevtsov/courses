package me.ihor.derevtsov.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
