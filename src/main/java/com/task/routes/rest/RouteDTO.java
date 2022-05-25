package com.task.routes.rest;

import com.task.routes.api.Route;
import java.util.List;
import java.util.stream.Collectors;

class RouteDTO {

	public List<String> route;

	RouteDTO(Route route) {
		this.route = route.borderCrossings.stream()
				.map(country -> country.value)
				.collect(Collectors.toList());
	}

}
