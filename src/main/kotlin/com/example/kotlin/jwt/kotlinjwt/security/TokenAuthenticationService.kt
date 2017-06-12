package com.example.kotlin.jwt.kotlinjwt.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Nemanja on 6/12/17.
 *
 * Helper service for creating and managing tokens
 */
object TokenAuthenticationService {

    const val EXPIRATIONTIME = 864_000_000 // 10 days
    const val SECRET = "ThisIsSecret"
    val TOKEN_PREFIX = "Bearer"
    val HEADER_STRING = "Authorization"

    /**
     * Creates new token and adds it to response
     */
    fun addAuthentication(res: HttpServletResponse, username: String) {
        val JWT: String = Jwts.builder()
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact()

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT)
    }

    /**
     * Checks if token is present in header in requests and parses it
     * Then returns ner Username and Password token or null if something went wrong
     */
    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(HEADER_STRING)
        if (token != null) {
            val user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    .body
                    .subject

            if (user != null) {
                return  UsernamePasswordAuthenticationToken(user, null, emptyList())
            } else {
                return null
            }
        }

        return null
    }

}