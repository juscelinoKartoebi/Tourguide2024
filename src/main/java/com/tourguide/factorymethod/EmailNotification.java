package com.tourguide.factorymethod;

public class EmailNotification extends Notification implements NotificationInterface {

	@Override
	public String notifyUser() {
		return "Sending an e-mail notification";

	}
}