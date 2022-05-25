package com.task.routes.api;

import java.util.Objects;
import java.util.StringJoiner;

public final class CountryCode {

	public final String value;

	public CountryCode(String value) {
		Objects.requireNonNull(value);
		if (value.isBlank()) {
			throw new IllegalArgumentException("Country code can not be blanc");
		}
		//if there are some restrictions on country code, ex specific pattern like only capital letter's, max length it also be here;
		//i am not aware of those restrictions but that's the place to enforce them
		this.value = value;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CountryCode.class.getSimpleName() + "[", "]")
				.add("value='" + value + "'")
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
		CountryCode that = (CountryCode) o;
		return value.equals(that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
