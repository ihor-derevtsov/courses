package org.smoketea.clients.notification;

public record NotificationRequest(
		Integer toCustomerId,
		String toCustomerEmail,
		String sender,
		String message
) {
}
