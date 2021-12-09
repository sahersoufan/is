package ite.fifthyear.is.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import ite.fifthyear.is.domain.Role;
import ite.fifthyear.is.domain.User;
import ite.fifthyear.is.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class JwtController {

    private final UserService userService;


    @GetMapping("/checkExpire")
public void checkExpire(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {

    Cookie[] authCookie = request.getCookies();
    final String[] token_cookie = new String[2];
    Boolean accessTokenIsValid = false;
    Map<String,Boolean> res = new HashMap<>();
    response.setContentType(APPLICATION_JSON_VALUE);


    stream(authCookie).forEach(cookie -> {
        if (cookie.getName().equals("access_token")){
            token_cookie[0] = cookie.getValue();
        }
        if (cookie.getName().equals("refresh_token")){
            token_cookie[1] = cookie.getValue();
        }
    });

    if (token_cookie[0]!= null) {
        try {
            String token = token_cookie[0];
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            accessTokenIsValid = true;
            res.put("res", true);
            res.put("reload",false);

            new ObjectMapper().writeValue(response.getOutputStream(), res);
        } catch (Exception e) {
            accessTokenIsValid = false;
        }


        if (token_cookie[1] != null && !accessTokenIsValid) {
            String refresh = token_cookie[1];
            final String uri = "http://localhost:8080/api/token/refresh";
/*            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders httpHeaders = new HttpHeaders();
            stream(authCookie).forEach(cookie ->
                    httpHeaders.add("Cookie", cookie.getName()+"="+cookie.getValue()
                            ));

            try {

                ResponseEntity<String> refreshResult = restTemplate.exchange(uri, HttpMethod.GET,
                        new HttpEntity<String>(httpHeaders),String.class);

                JSONObject newAccesToken = new JSONObject(refreshResult.getBody());*/
            try {
                JSONObject newAccessToken = refreshToken(refresh, uri );
                Cookie cookie = new Cookie("access_token",newAccessToken.get("access_token").toString());
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");


                response.addCookie(cookie);

                res.put("res", true);
                res.put("reload",true);
                new ObjectMapper().writeValue(response.getOutputStream(), res);
                return;
            }catch (Exception e){
                res.put("res", false);
                new ObjectMapper().writeValue(response.getOutputStream(), res);

            }

            }

    }
    res.put("res", false);
    new ObjectMapper().writeValue(response.getOutputStream(), res);

}

public JSONObject refreshToken(String refreshToken, String uri) throws Exception{


            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            String username = decodedJWT.getSubject();
            User user = userService.getUser(username);
            String access_token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000))
                    .withIssuer(uri)
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
            return new JSONObject("{"+"access_token :"+access_token+"}");

}

}
