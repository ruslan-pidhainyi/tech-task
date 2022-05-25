package com.task.routes.api;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public final class Route {

	public final List<CountryCode> borderCrossings;

	public final CountryCode origin;

	public final CountryCode destination;

	public Route(List<CountryCode> borderCrossings, CountryCode origin, CountryCode destination) {
		Objects.requireNonNull(borderCrossings);
		Objects.requireNonNull(origin);
		Objects.requireNonNull(destination);
		if (borderCrossings.size() < 1) {
			throw new IllegalArgumentException("Route can not be empty");
		}
		if (!Objects.equals(borderCrossings.get(0), origin)) {
			throw new IllegalArgumentException("Origin should be the first starting point of the route; expected value : "
					+ origin + " actual " + borderCrossings.get(0));
		}
		if (!Objects.equals(borderCrossings.get(borderCrossings.size() - 1), destination)) {
			throw new IllegalArgumentException("Destination should be the final element of the route; expected value : "
					+ destination + " actual " + borderCrossings.get(borderCrossings.size() - 1));
		}
		this.borderCrossings = borderCrossings;
		this.origin = origin;
		this.destination = destination;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Route route = (Route) o;
		return Objects.equals(borderCrossings, route.borderCrossings) && Objects.equals(origin, route.origin) && Objects.equals(destination,
				route.destination);
	}

	@Override
	public int hashCode() {
		return Objects.hash(borderCrossings, origin, destination);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Route.class.getSimpleName() + "[", "]")
				.add("borderCrossings=" + borderCrossings)
				.add("origin=" + origin)
				.add("destination=" + destination)
				.toString();
	}

}
