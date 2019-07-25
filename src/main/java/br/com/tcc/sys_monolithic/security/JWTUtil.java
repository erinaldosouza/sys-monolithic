package br.com.tcc.sys_monolithic.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.token.authorities.key}")
	private String tokenAuthoritiesKey;
	
	public String generateToken(String username, Collection<? extends GrantedAuthority> autorities) {
		
		return Jwts.builder()
				   .setSubject(username)
				   .claim(tokenAuthoritiesKey, autorities)
				   .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
				   .signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
				   .compact();		
	}
	
	
	public String getUsername(String token) {
		String username = null;
		Claims claims = getClaims(token);
		
		if (claims != null) {
			username = claims.getSubject();
		}
		
		return username;
	}
	
	public boolean isValid(String token) {
		
		boolean isValid = Boolean.FALSE;
		Claims claims = getClaims(token);
		
		if(claims != null) {
			Date now = new Date();			
			Date expirationDate = claims.getExpiration();
			isValid = (!StringUtils.isEmpty(claims.getSubject()) && expirationDate != null && now.before(expirationDate));
		}
		
		return isValid;
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
	
	public String getTokenAuthoritiesKey() {
		return this.tokenAuthoritiesKey;
	}
}
