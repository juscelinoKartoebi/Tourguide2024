package com.tourguide.factorymethod;

public class SMSNotification extends Notification implements NotificationInterface {

	@Override
	public String notifyUser() {
		// TODO Auto-generated method stub
		return "Sending an SMS notification";
	}
}