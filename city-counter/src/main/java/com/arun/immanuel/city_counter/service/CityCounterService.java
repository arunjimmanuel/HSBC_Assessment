package com.arun.immanuel.city_counter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CityCounterService {
    private final String API_URL = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";

    @Cacheable("cityCounts")
    public Map<String, Object> getCityCount(String letter) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);

        List<Map<String, Object>> cities = (List<Map<String, Object>>) response.get("list");
        List<String> matchedCities = cities.stream()
                .map(city -> (String) city.get("name"))
                .filter(name -> name != null && name.toLowerCase().startsWith(letter.toLowerCase()))
                .toList();
        Map<String, Object> result = new HashMap<>();
        result.put("count", matchedCities.size());
        result.put("cities", String.join(", ", matchedCities));
        return result;
    }

    @CacheEvict(value = "cityCounts", allEntries = true)
    @Scheduled(fixedRate = 60 * 60 * 1000) // 1 Hour
    public void clearCityCountCache() {
        System.out.println("Cleared cityCounts cache at " + java.time.LocalDateTime.now());
    }
}
