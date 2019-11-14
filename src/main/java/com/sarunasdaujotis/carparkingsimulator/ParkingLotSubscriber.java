package com.sarunasdaujotis.carparkingsimulator;

import java.util.Set;
import com.sarunasdaujotis.carparkingsimulator.events.TransportEnterEvent;
import com.sarunasdaujotis.carparkingsimulator.events.TransportLeaveEvent;
import com.sarunasdaujotis.carparkingsimulator.events.base.Event;
import com.sarunasdaujotis.carparkingsimulator.events.base.Subscriber;
import com.sarunasdaujotis.carparkingsimulator.models.ParkingLot;
import com.sarunasdaujotis.carparkingsimulator.models.RoadTransport;

public class ParkingLotSubscriber implements Subscriber {

	private final ParkingLot parkingLot;

	public ParkingLotSubscriber(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}

	@Override
	public void handle(final Event<?> event) {
		if (event instanceof TransportEnterEvent) {
			final RoadTransport roadTransport = ((TransportEnterEvent) event).getData();
			parkingLot.findClosestAndPark(roadTransport);
		}
		if (event instanceof TransportLeaveEvent) {
			final String licenceNumber = ((TransportLeaveEvent) event).getData();
			parkingLot.leaveParking(licenceNumber);
		}
	}

	@Override
	public Set<Class<?>> supports() {
		return Set.of(TransportEnterEvent.class, TransportLeaveEvent.class);
	}
}
