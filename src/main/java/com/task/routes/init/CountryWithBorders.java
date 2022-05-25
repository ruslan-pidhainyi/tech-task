package com.task.routes.init;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

class CountryWithBorders {

	static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public List<String> borders;

	public String cca3;

	public CountryWithBorders() {
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CountryWithBorders.class.getSimpleName() + "[", "]")
				.add("borders=" + borders)
				.add("cca3='" + cca3 + "'")
				.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CountryWithBorders that = (CountryWithBorders) o;
		return Objects.equals(borders, that.borders) && Objects.equals(cca3, that.cca3);
	}

	@Override
	public int hashCode() {
		return Objects.hash(borders, cca3);
	}
}
