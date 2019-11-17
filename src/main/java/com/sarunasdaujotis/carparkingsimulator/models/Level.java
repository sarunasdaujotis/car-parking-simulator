package com.sarunasdaujotis.carparkingsimulator.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Level {
	private Set<RoadTransport> parkingSpaces;
	private int initialCapacity = 0;

	private Level() {
		//No-op; can't be called
	}

	private Level(final int initialCapacity) {
		this.initialCapacity = initialCapacity;
		this.parkingSpaces = Collections.synchronizedSet(new HashSet<>(initialCapacity));
	}

	public int findAvailable() {
		return initialCapacity - parkingSpaces.size();
	}

	boolean findAndRemove(final String licenceNumber) {
		Objects.requireNonNull(licenceNumber);
		return parkingSpaces.removeIf(roadTransport -> roadTransport.getLicenceNumber().equals(licenceNumber));
	}

	void addRoadTransport(final RoadTransport roadTransport) {
		if (atCapacity()) {
			return;
		}
		if (parkingSpaces.contains(roadTransport)) {
			throw new IllegalArgumentException("Transport with licence " + roadTransport.getLicenceNumber() + " already exists");
		}

		parkingSpaces.add(roadTransport);
	}

	private boolean atCapacity() {
		return initialCapacity == parkingSpaces.size();
	}

	static Level create(final int initialCapacity) {
		return new Level(initialCapacity);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Level level = (Level) o;
		return initialCapacity == level.initialCapacity &&
				parkingSpaces.containsAll(level.parkingSpaces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(parkingSpaces, initialCapacity);
	}
}
