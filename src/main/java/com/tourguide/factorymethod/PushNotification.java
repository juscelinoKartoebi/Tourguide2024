package com.tourguide.factorymethod;

public class PushNotification extends Notification implements NotificationInterface {

	@Override
	public String notifyUser() {
		return "Sending a push notification";
	}
}