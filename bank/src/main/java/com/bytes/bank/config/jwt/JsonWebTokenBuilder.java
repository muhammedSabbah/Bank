package com.bytes.bank.config.jwt;

import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public abstract class JsonWebTokenBuilder {
	
	protected static final String secret = "AZXS";
	
	protected Long expiration = 3600000L;
	
	protected abstract Map<String, Object> extractParameters(Object obj);
	
	public String generateToken(Object obj) {
		Map<String, Object> params = extractParameters(obj);
		return build(params);
	}

	protected String build(Map<String, Object> parameters) {
		JwtBuilder builder = generateJwtBuilder();
		parameters.forEach((key, value) -> {
			if (key.equals(Claims.SUBJECT)) {
				builder.setSubject((String) value);
			} else if (key.equals(Claims.ISSUED_AT)) {
				builder.setIssuedAt((Date) value);
			} else if (key.equals(Claims.EXPIRATION)) {
				builder.setExpiration((Date) value);
			} else if (key.equals(Claims.AUDIENCE)) {
				builder.setAudience((String) value);
			} else {
				builder.claim(key, value);
			}
		});

		builder.signWith(SignatureAlgorithm.HS256, secret);
		return builder.compact();
	}
	
	private JwtBuilder generateJwtBuilder() {
		return Jwts.builder();
	}
}
