package com.cognizant.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MultipleReturnsTest {

    @Test
    public void testMultipleReturns() {

        WeatherApi mockWeatherApi = Mockito.mock(WeatherApi.class);

        when(mockWeatherApi.getWeather())
                .thenReturn("Sunny", "Rainy", "Cloudy");

        WeatherService weatherService =
                new WeatherService(mockWeatherApi);

        assertEquals("Sunny", weatherService.fetchWeather());

        assertEquals("Rainy", weatherService.fetchWeather());

        assertEquals("Cloudy", weatherService.fetchWeather());
    }
}