package com.task.routes.rest;

import com.task.routes.api.CountryCode;
import com.task.routes.api.Route;
import com.task.routes.api.RouteQueries;
import java.util.Arrays;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoutesResource.class)
class RoutesResourceTest {

	@MockBean
	RouteQueries routeQueries;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void shouldReturnPathWhenExists() throws Exception {
		Mockito.when(routeQueries.findBetween(new CountryCode("CZE"), new CountryCode("ITA")))
				.thenReturn(Optional.of(new Route(Arrays.asList(
						new CountryCode("CZE"),
						new CountryCode("AUT"),
						new CountryCode("ITA")),
						new CountryCode("CZE"),
						new CountryCode("ITA"))));

		mockMvc.perform(get("/routing/CZE/ITA"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.route", Matchers.hasSize(3)))
				.andExpect(jsonPath("$.route", Matchers.hasItems("CZE", "AUT", "ITA")));
	}

	@Test
	public void shouldReturn400WhenPathNotExists() throws Exception {
		Mockito.when(routeQueries.findBetween(new CountryCode("CZE"), new CountryCode("ITA")))
				.thenReturn(Optional.empty());

		mockMvc.perform(get("/routing/CZE/ITA"))
				.andExpect(status().is(400));
	}
}