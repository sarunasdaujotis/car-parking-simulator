package com.sarunasdaujotis.carparkingsimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.sarunasdaujotis.carparkingsimulator.events.base.Event;
import com.sarunasdaujotis.carparkingsimulator.events.base.EventBus;
import com.sarunasdaujotis.carparkingsimulator.events.base.Subscriber;

public class WorldEventBus implements EventBus {

	private List<Subscriber> subscribers = Collections.synchronizedList(new ArrayList<>());

	@Override
	public void register(final Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void dispatch(final Event<?> event) {
		subscribers.stream()
				.filter(subscriber -> subscriber.supports()
						.stream()
						.anyMatch(supportedEvent -> supportedEvent == event.getClass()))
				.findFirst()
				.ifPresent(subscriber -> subscriber.handle(event));
	}

	@Override
	public List<Subscriber> getSubscribers() {
		return subscribers;
	}
}
