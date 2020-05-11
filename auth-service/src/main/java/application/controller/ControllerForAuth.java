package application.controller;

import application.data.AuthAnswer;
import application.data.AuthAnswerCustomer;
import application.data.PaidType;
import application.security.TokenData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerForAuth {

    @Value("${token.key}")
    private String key;
    private String token;


    @PostMapping("/login")
    public String generToken(@RequestBody AuthAnswer authAnswer) {
        ResponseEntity<AuthAnswerCustomer> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/customer/checkCustomerByEP/" + authAnswer.getEmail() + "/" + authAnswer.getPassword(), AuthAnswerCustomer.class);
        AuthAnswerCustomer response = responseEntity.getBody();
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenData.ID.getValue(), response.getId());
        tokenData.put(TokenData.EMAIL.getValue(), response.getEmail());
        tokenData.put(TokenData.ROLE.getValue(), response.getRole());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenData);
        token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        return token;
    }

    @GetMapping("/getToken")
    public String getToken() {
        return token;
    }


    @GetMapping("/getResponse")
    public List<PaidType> getEntity(@RequestBody AuthAnswer authAnswer) {
        ResponseEntity<AuthAnswerCustomer> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/customer/checkCustomerByEP/" + authAnswer.getEmail() + "/" + authAnswer.getPassword(), AuthAnswerCustomer.class);
        AuthAnswerCustomer response = responseEntity.getBody();
        return response.getPaidTypeList();
    }
}