package com.arun.immanuel.city_counter.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityCounterService {
    private final String API_URL = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";

    public Map<String, Object> getCityCount(String letter) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

        List<Map<String, Object>> cities = (List<Map<String, Object>>) response.get("list");
        long count = cities.stream()
                .map(city -> (String) city.get("name"))
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .count();

        return Map.of("count", count);
    }
}
