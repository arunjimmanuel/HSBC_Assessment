package com.arun.immanuel.city_counter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.immanuel.city_counter.service.CityCounterService;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class CityCounterController {

    private CityCounterService cityCounterService;

    public CityCounterController(CityCounterService cityCounterService) {
        this.cityCounterService = cityCounterService;
    }

    @GetMapping("/count")
    public Map<String, Object> getCityCount(@RequestParam String letter) {
        return cityCounterService.getCityCount(letter);
    }

}
