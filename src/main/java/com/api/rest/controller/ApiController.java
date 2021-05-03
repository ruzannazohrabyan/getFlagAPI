package com.api.rest.controller;

import com.api.rest.service.ApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/countries/{countryName}")
    public ResponseEntity<String> getFlagOfCountry(@PathVariable String countryName) throws JsonProcessingException {
        return apiService.getFlag(countryName);
    }
}
