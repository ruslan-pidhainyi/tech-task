package com.task.routes.rest;

import com.task.routes.api.CountryCode;
import com.task.routes.api.RouteQueries;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RoutesResource {

	private final RouteQueries routeQueries;

	RoutesResource(RouteQueries routeQueries) {
		this.routeQueries = routeQueries;
	}

	@GetMapping("/routing/{origin}/{destination}")
	ResponseEntity<RouteDTO> routeBetween(@PathVariable String origin, @PathVariable String destination) {
		return routeQueries
				.findBetween(new CountryCode(origin), new CountryCode(destination))
				.map(route -> new ResponseEntity<>(new RouteDTO(route), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
