package com.susanibar.domains.configuration;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ApiUser {

    final static Logger miLogger = Logger.getLogger(ApiUser.class);

    @RequestMapping("/insecure/all")
    public ResponseEntity<?> getInsecureUsers() throws Exception {
        miLogger.info("API - Call -> getInsecureUsers");
        try {
            RestTemplate restTemplate = new RestTemplate();
            String getUser = "http://localhost:18080/domain/user/all";

            ResponseEntity<Object> response = restTemplate.getForEntity(getUser, Object.class);

            miLogger.info(response.getBody());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }

    @RequestMapping("/secure/all")
    public ResponseEntity<?> geSecureUsers() throws Exception {
        miLogger.info("API - Call -> geSecureUsers");
        try {
            RestTemplate restTemplate = new RestTemplate();
            String getUser = "https://ddsa.configuration.domain:1443/domain/user/all";

            ResponseEntity<Object> response = restTemplate.getForEntity(getUser, Object.class);

            miLogger.info(response.getBody());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }

    @RequestMapping("/secureok/all")
    public ResponseEntity<?> geSecureOkUsers() throws Exception {
        miLogger.info("API - Call -> geSecureOkUsers");
        try {
            RestTemplate restTemplate = new RestTemplate();
            String getUser = "https://localhost:1443/domain/user/all";

            ResponseEntity<Object> response = restTemplate.getForEntity(getUser, Object.class);

            miLogger.info(response.getBody());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }
}