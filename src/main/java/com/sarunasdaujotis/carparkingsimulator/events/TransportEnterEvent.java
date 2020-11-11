package com.sarunasdaujotis.carparkingsimulator.events;

import com.sarunasdaujotis.carparkingsimulator.events.base.Event;
import com.sarunasdaujotis.carparkingsimulator.models.RoadTransport;

public class TransportEnterEvent implements Event<RoadTransport> {

	private final RoadTransport roadTransport;

	public TransportEnterEvent(final RoadTransport roadTransport) {
		this.roadTransport = roadTransport;
	}

	@Override
	public RoadTransport getData() {
		return roadTransport;
	}
}
