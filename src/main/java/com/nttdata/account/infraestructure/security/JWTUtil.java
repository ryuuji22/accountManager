/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.infraestructure.security;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 *
 * @author diego
 */
@Component
public class JWTUtil {

	@Value("${JWT.secret}")
	private String secret;

	@Value("${JWT.expirationMinutes}")
	private Integer expirationMinutes;

	public String generateToken(String identification) throws IllegalArgumentException, JWTCreationException {
		return JWT.create().withSubject("User Details").withClaim("identification", identification)
				.withIssuedAt(new Date()).withExpiresAt(this.addMinutesToDate(new Date(), this.expirationMinutes))
				.withIssuer("vaccineapp/kruger/kruger").sign(Algorithm.HMAC256(secret));
	}

	public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject("User Details")
				.withIssuer("vaccineapp/kruger/kruger").build();
		DecodedJWT jwt = verifier.verify(token);
		if( jwt.getExpiresAt().before(new Date())) {
			 throw new JWTVerificationException("Token is expired");
		}
		return jwt.getClaim("identification").asString();
	}


	private Date addMinutesToDate(Date date, int minutes) {
		if (minutes == 0) {
			return date;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);

		return calendar.getTime();
	}
}
