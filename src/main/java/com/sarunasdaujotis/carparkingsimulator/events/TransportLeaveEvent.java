package com.sarunasdaujotis.carparkingsimulator.events;

import com.sarunasdaujotis.carparkingsimulator.events.base.Event;

public class TransportLeaveEvent implements Event<String> {

	private final String licenceNumber;

	public TransportLeaveEvent(final String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	@Override
	public String getData() {
		return licenceNumber;
	}
}
