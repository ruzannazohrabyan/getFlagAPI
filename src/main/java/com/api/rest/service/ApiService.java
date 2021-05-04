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

    public ResponseEntity<JsonNode> getFlag(String countryName) throws JsonProcessingException {
        String externalUrl = "https://restcountries.eu/rest/v2/name/" + countryName;

        JsonNode country = restTemplate.getForEntity(externalUrl, JsonNode.class).getBody();
        JsonNode node = country.get(0).get("flag");
        String flag = "{\"flag\": " + node + "}";
        return new ResponseEntity<>(new ObjectMapper().readTree(flag), HttpStatus.OK);
    }

}
