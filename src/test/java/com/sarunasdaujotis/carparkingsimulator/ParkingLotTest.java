package com.sarunasdaujotis.carparkingsimulator;

import org.junit.Before;
import org.junit.Test;
import com.sarunasdaujotis.carparkingsimulator.events.TransportEnterEvent;
import com.sarunasdaujotis.carparkingsimulator.events.TransportLeaveEvent;
import com.sarunasdaujotis.carparkingsimulator.models.Car;
import com.sarunasdaujotis.carparkingsimulator.models.ParkingLot;
import com.sarunasdaujotis.carparkingsimulator.models.RoadTransport;
import static org.junit.Assert.assertEquals;

public class ParkingLotTest {

	private ParkingLot parkingLot;
	private WorldEventBus worldEventBus;

	@Before
	public void setUp() {
		parkingLot = ParkingLot.create(new int[]{3, 2, 2, 2});
		worldEventBus = new WorldEventBus();
		worldEventBus.register(new ParkingLotSubscriber(parkingLot));
	}

	@Test
	public void testBalancingAcrossLevels() {
		final RoadTransport car = Car.create("TEST");
		final RoadTransport car1 = Car.create("TEST1");
		final RoadTransport car2 = Car.create("TEST2");
		final RoadTransport car3 = Car.create("TEST3");
		final RoadTransport car4 = Car.create("TEST4");
		final RoadTransport car5 = Car.create("TEST5");

		//Spreading cars across ground and second floor
		worldEventBus.dispatch(new TransportEnterEvent(car));
		worldEventBus.dispatch(new TransportEnterEvent(car1));
		worldEventBus.dispatch(new TransportEnterEvent(car2));
		worldEventBus.dispatch(new TransportEnterEvent(car3));
		assertEquals(0, parkingLot.getLevels().get(0).findAvailable());
		assertEquals(1, parkingLot.getLevels().get(1).findAvailable());

		//One car leaves from ground floor, then car4 enter and gets redirected to ground floor and car5 goes to second floor
		worldEventBus.dispatch(new TransportLeaveEvent("TEST"));
		worldEventBus.dispatch(new TransportEnterEvent(car4));
		worldEventBus.dispatch(new TransportEnterEvent(car5));
		assertEquals(0, parkingLot.getLevels().get(0).findAvailable());
		assertEquals(0, parkingLot.getLevels().get(1).findAvailable());
	}

}