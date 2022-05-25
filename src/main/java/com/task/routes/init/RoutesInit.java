package com.task.routes.init;

import com.task.routes.api.Connection;
import com.task.routes.api.CountryCode;
import com.task.routes.api.RouteCommands;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
class RoutesInit {

	private final RouteCommands routeCommands;

	RoutesInit(RouteCommands routeCommands) {
		this.routeCommands = routeCommands;
	}

	@PostConstruct
	public void postConstruct() throws IOException {
		File file = ResourceUtils.getFile("classpath:countries.json");
		Arrays.stream(CountryWithBorders.mapper.readValue(file, CountryWithBorders[].class))
				.forEach(this::addAllConnectionsFor);
	}

	private void addAllConnectionsFor(CountryWithBorders countryWithBorders) {
		countryWithBorders.borders.stream()
				.map(countryBorder -> new Connection(new CountryCode(countryWithBorders.cca3), new CountryCode(countryBorder)))
				.forEach(routeCommands::add);
	}

}
