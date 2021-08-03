package com.janfranco.JWTExample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janfranco.JWTExample.entity.Authority;
import com.janfranco.JWTExample.entity.User;
import com.janfranco.JWTExample.entity.dto.JWTUserDTO;
import com.janfranco.JWTExample.service.abstracts.JWTService;
import com.janfranco.JWTExample.service.exception.InvalidJWTException;
import com.janfranco.JWTExample.service.exception.JWTExpiredException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTServiceImpl implements JWTService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", new JWTUserDTO(user.getId(), user.getAuthorities() != null ?
                user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList()) : new ArrayList<>()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public JWTUserDTO verifyToken(String token) throws JWTExpiredException, InvalidJWTException {
        if (isTokenExpired(token)) {
            throw new JWTExpiredException();
        }

        return extractUser(token);
    }

    private boolean isTokenExpired(String token) throws InvalidJWTException {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws InvalidJWTException {
        return extractClaim(token, Claims::getExpiration);
    }

    private JWTUserDTO extractUser(String token) throws InvalidJWTException {
        final ObjectMapper mapper = new ObjectMapper();
        final Claims claims = extractAllClaims(token);
        return mapper.convertValue(claims.get("user"), JWTUserDTO.class);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws InvalidJWTException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws InvalidJWTException {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException exception) {
            return exception.getClaims();
        } catch (MalformedJwtException exception) {
            throw new InvalidJWTException();
        }
    }
}
