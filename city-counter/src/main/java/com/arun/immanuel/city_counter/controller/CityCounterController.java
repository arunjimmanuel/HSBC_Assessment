package com.arun.immanuel.city_counter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.immanuel.city_counter.service.CityCounterService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
@Validated
public class CityCounterController {

    private CityCounterService cityCounterService;

    public CityCounterController(CityCounterService cityCounterService) {
        this.cityCounterService = cityCounterService;
    }

    @GetMapping("/cities")
    public Map<String, Object> getCityCount(
            @RequestParam @NotBlank(message = "Letter must not be blank") @Pattern(regexp = "^[a-zA-Z]$", message = "Letter must be a single alphabet character") String letter) {
        return cityCounterService.getCityCount(letter);
    }

}
