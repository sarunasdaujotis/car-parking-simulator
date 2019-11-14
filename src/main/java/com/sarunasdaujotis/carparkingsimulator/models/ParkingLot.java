package com.sarunasdaujotis.carparkingsimulator.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

public final class ParkingLot {
	private final List<Level> levels = Collections.synchronizedList(new ArrayList<>());

	private ParkingLot() {
		//No-op; can't be called
	}

	private ParkingLot(int[] capacityPerLevel) {
		for (int initialCapacity : capacityPerLevel) {
			this.levels.add(Level.create(initialCapacity));
		}
	}

	public synchronized void findClosestAndPark(final RoadTransport roadTransport) {
		levels.stream()
				.reduce(closestNotAtCapacity())
				.ifPresent(level -> level.addRoadTransport(roadTransport));
	}

	public synchronized void leaveParking(final String licenceNumber) {
		for (Level level : levels) {
			if (level.findAndRemove(licenceNumber)) {
				return;
			}
		}
	}

	/**
	 * Access method used for testing purposes, should not be used in production code
	 * @return unmodifiable collection
	 */
	public synchronized List<Level> getLevels() {
		return Collections.unmodifiableList(levels);
	}

	private BinaryOperator<Level> closestNotAtCapacity() {
		return (current, next) -> current.findAvailable() > 0 ? current : next;
	}

	public static ParkingLot create(int[] capacityPerLevel) {
		return new ParkingLot(capacityPerLevel);
	}
}
