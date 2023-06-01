package com.example.withauthmanager.services;


import java.util.List;

public interface JwtTokenService {
    String createToken(String userMail, List<String> grantedAuthorities);

    String getUserEmail(String token);

    List<String> getAuthorities(String token);
}
