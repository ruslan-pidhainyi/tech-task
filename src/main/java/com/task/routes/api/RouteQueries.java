package com.task.routes.api;

import java.util.Optional;

public interface RouteQueries {

	Optional<Route> findBetween(CountryCode origin, CountryCode destination);

}
