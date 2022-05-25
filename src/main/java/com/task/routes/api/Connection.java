package com.task.routes.api;

import java.util.Objects;
import java.util.StringJoiner;

//something that exists between two countries,
//in future might have some sort of weight attribute that will be used during calculation
public class Connection {

	//in this case there is no direction so origin/destination would make sense here
	public CountryCode left;

	public CountryCode right;

	public Connection(CountryCode left, CountryCode right) {
		Objects.requireNonNull(left);
		Objects.requireNonNull(right);
		if (left.equals(right)) {
			throw new IllegalArgumentException("Connection can be defined only between distinct sides");
		}
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Connection.class.getSimpleName() + "[", "]")
				.add("left=" + left)
				.add("right=" + right)
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
		Connection that = (Connection) o;
		return Objects.equals(left, that.left) && Objects.equals(right, that.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}
}
