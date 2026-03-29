package me.ihor.derevtsov.kafkaexample;

import java.time.LocalDateTime;

public record Message(String message, LocalDateTime createdAt) {}
