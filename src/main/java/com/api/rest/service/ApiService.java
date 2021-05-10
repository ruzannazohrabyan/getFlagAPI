package com.api.rest.service;

import com.api.rest.domain.Flag;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {
    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Flag> getFlag(String countryName) throws JsonProcessingException {
        String externalUrl = "https://restcountries.eu/rest/v2/name/" + countryName;

        JsonNode country = restTemplate.getForEntity(externalUrl, JsonNode.class).getBody();
        Flag flag = new Flag(country.get(0).get("flag").asText());

        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

}
