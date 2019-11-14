package com.sarunasdaujotis.carparkingsimulator.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Level {
	private Set<RoadTransport> parkingSpaces;
	private int initialCapacity = 0;

	private Level() {
	}

	private Level(final int initialCapacity) {
		this.initialCapacity = initialCapacity;
		this.parkingSpaces = Collections.synchronizedSet(new HashSet<>(initialCapacity));
	}

	public void addRoadTransport(final RoadTransport roadTransport) {
		if (atCapacity()) {
			return;
		}
		parkingSpaces.add(roadTransport);
	}

	public void removeRoadTransport(final RoadTransport roadTransport) {
		parkingSpaces.remove(roadTransport);
	}

	public boolean findAndRemove(final String licenceNumber) {
		Objects.requireNonNull(licenceNumber);
		return parkingSpaces.removeIf(roadTransport -> roadTransport.getLicenceNumber().equals(licenceNumber));
	}

	public int findAvailable() {
		return initialCapacity - parkingSpaces.size();
	}

	public static Level create(final int initialCapacity) {
		return new Level(initialCapacity);
	}

	private boolean atCapacity() {
		return initialCapacity == parkingSpaces.size();
	}
}
