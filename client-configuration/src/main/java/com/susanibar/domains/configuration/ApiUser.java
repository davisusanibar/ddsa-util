package com.susanibar.domains.configuration;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class ApiUser {

    final static Logger miLogger = Logger.getLogger(ApiUser.class);


    @RequestMapping("/all")
    public ResponseEntity<?> geSecureOkUsers() throws Exception {
        miLogger.info("API - Call -> geSecureOkUsers");
        try {
            RestTemplate restTemplate = new RestTemplate();
            //String getUser = "https://localhost:28082/user/all";
            String getUser = "https://localhost:28082/user/all";

            ResponseEntity<Object> response = restTemplate.getForEntity(getUser, Object.class);

            miLogger.info(response.getBody());

            return response;
        } catch (RestClientException rce){
            throw new Exception("Error to call rest template. Error: " + rce.getMessage());
        }
    }
}
