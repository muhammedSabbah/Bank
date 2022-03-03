package com.bytes.bank.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;

public class JwtTokenUtil extends JsonWebTokenBuilder{
	
	private Clock clock = DefaultClock.INSTANCE;
	
	public String getSubject(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public String getJti(String token) {
		return getClaimFromToken(token, Claims::getId);
	}

	public Date getIssuedAtDate(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDate(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public String getAudience(String token) {
		return getClaimFromToken(token, Claims::getAudience);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Date calculateExpirationDate(Date createdDate) {
		// expiration after 1 hour
		// 1 hour = 60 minutes = 60 × 60 seconds = 3600 seconds
		// = 3600 × 1000 milliseconds = 3,600,000 ms.
		return new Date(createdDate.getTime() + expiration);
	}

	@Override
	protected Map<String, Object> extractParameters(Object obj) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(obj instanceof String) {
			parameters.put(Claims.SUBJECT, (String) obj);
		} else if(obj instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) obj;
			final Date createdDate = clock.now();
			final Date expirationDate = calculateExpirationDate(createdDate);
			parameters.put(Claims.SUBJECT, userDetails.getUsername());
			parameters.put(Claims.ISSUED_AT, createdDate);
			parameters.put(Claims.EXPIRATION, expirationDate);
			parameters.put(Claims.AUDIENCE, userDetails.getAuthorities().toString());
		}
		return parameters;
	}
}
