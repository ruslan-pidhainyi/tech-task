package com.task.routes;

import com.task.routes.api.Connection;
import com.task.routes.api.CountryCode;
import com.task.routes.api.Route;
import com.task.routes.api.RouteCommands;
import com.task.routes.api.RouteQueries;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RoutesApplicationTests {

	@Autowired
	private RouteCommands routeCommands;

	@Autowired
	private RouteQueries routeQueries;

	@Test
	void shouldFindRoute() {
		//given
		routeCommands.add(new Connection(new CountryCode("AA"), new CountryCode("BB")));
		routeCommands.add(new Connection(new CountryCode("BB"), new CountryCode("CC")));
		routeCommands.add(new Connection(new CountryCode("CC"), new CountryCode("DD")));
		//when
		Optional<Route> optionalRoute = routeQueries.findBetween(new CountryCode("AA"), new CountryCode("DD"));
		//then
		assertTrue(optionalRoute.isPresent());

		List<CountryCode> borderCrossings = optionalRoute.get().borderCrossings;
		assertEquals(4, borderCrossings.size());
		assertEquals(Arrays.asList(
				new CountryCode("AA"),
				new CountryCode("BB"),
				new CountryCode("CC"),
				new CountryCode("DD")),

				borderCrossings);
	}

	@Test
	void shouldNotFindRoute() {
		//given
		routeCommands.add(new Connection(new CountryCode("AA"), new CountryCode("BB")));
		routeCommands.add(new Connection(new CountryCode("CC"), new CountryCode("DD")));
		//when
		Optional<Route> optionalRoute = routeQueries.findBetween(new CountryCode("AA"), new CountryCode("DD"));
		//then
		assertFalse(optionalRoute.isPresent());
	}

}
