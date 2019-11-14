package com.sarunasdaujotis.carparkingsimulator.events.base;

import java.util.List;

public interface EventBus {
	void register(Subscriber subscriber);

	void dispatch(Event<?> event);

	List<Subscriber> getSubscribers();
}
