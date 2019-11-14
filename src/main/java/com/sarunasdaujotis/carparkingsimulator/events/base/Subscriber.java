package com.sarunasdaujotis.carparkingsimulator.events.base;

import java.util.Set;

public interface Subscriber {

	void handle(Event<?> event);

	Set<Class<?>> supports();
}
