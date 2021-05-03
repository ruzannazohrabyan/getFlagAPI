package com.api.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ResponseEntity<String> getFlag(String countryName) throws JsonProcessingException {
        String externalUrl = "https://restcountries.eu/rest/v2/name/" + countryName;

        String country = restTemplate.getForEntity(externalUrl, String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(country).get(0);
        String flag = node.get("flag").asText();
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

}
