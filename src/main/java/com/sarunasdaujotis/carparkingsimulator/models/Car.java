package com.sarunasdaujotis.carparkingsimulator.models;

import java.util.Objects;

public class Car implements RoadTransport {
	private String licenceNumber;

	private Car() {
	}

	private Car(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	@Override
	public String getLicenceNumber() {
		return licenceNumber;
	}

	public static Car create(String licenceNumber) {
		return new Car(licenceNumber);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final Car car = (Car) o;
		return licenceNumber.equals(car.licenceNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(licenceNumber);
	}
}
