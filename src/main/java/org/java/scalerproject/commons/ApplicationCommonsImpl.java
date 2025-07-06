package org.java.scalerproject.commons;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationCommonsImpl implements ApplicationCommons {

    private final RestTemplate restTemplate;

    ApplicationCommonsImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public void validateToken(String token) {
        if(token==null||token.isEmpty()) {
            throw new RuntimeException("token is null or empty");
        }
        String url = "http://UserServiceScalerProject/validate";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, token);
        HttpEntity<Void> responseEntity = new HttpEntity<Void>(headers);
        Boolean isValid =  restTemplate.postForObject(url, responseEntity, Boolean.class);
        if(Boolean.FALSE.equals(isValid)) {
            throw new RuntimeException("validate token failed");
        }
    }
}
