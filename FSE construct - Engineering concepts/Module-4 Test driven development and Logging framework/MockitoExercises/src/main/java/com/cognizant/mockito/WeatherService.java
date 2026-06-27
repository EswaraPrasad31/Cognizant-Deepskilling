package com.cognizant.mockito;

public class WeatherService {

    private WeatherApi weatherApi;

    public WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public String fetchWeather() {
        return weatherApi.getWeather();
    }
}