package com.task.routes.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryWithBordersMappingTest {

	@Test
	void shouldMapFromJSONString() throws JsonProcessingException {
		//given
		String json = "{\n" +
				"    \"cca3\": \"ABW\",\n" +
				"    \"noize\": false,\n" +
				"  \"noize2\": [],\n" +
				"    \"borders\": [\"AMM\", \"KKF\"]\n" +
				"}\n" +
				"\n";
		//when
		CountryWithBorders result = CountryWithBorders.mapper.readValue(json, CountryWithBorders.class);
		//then
		assertEquals(result.cca3, "ABW");
		assertEquals(result.borders, Arrays.asList("AMM", "KKF"));
	}

	@Test
	void shouldMapFromJSONArrayString() throws JsonProcessingException {
		//given
		String jsonArray = "[{\n" +
				"    \"cca3\": \"ABW\",\n" +
				"    \"noize\": false,\n" +
				"  \"noize2\": [],\n" +
				"    \"borders\": [\"IP\", \"AA\"]\n" +
				"},\n" +
				"{\n" +
				"    \"cca3\": \"KKL\",\n" +
				"    \"borders\": [\"OI\", \"AQ\"]\n" +
				"}]";
		//when
		CountryWithBorders[] results = CountryWithBorders.mapper.readValue(jsonArray, CountryWithBorders[].class);
		//then
		assertEquals(results.length, 2);

		assertEquals(results[0].cca3, "ABW");
		assertEquals(results[0].borders, Arrays.asList("IP", "AA"));

		assertEquals(results[1].cca3, "KKL");
		assertEquals(results[1].borders, Arrays.asList("OI", "AQ"));

	}

}