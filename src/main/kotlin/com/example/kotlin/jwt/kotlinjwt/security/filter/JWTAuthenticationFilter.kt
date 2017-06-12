package com.example.kotlin.jwt.kotlinjwt.security.filter

import com.example.kotlin.jwt.kotlinjwt.security.TokenAuthenticationService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.FilterChain;

/**
 * Created by Nemanja on 6/12/17.
 *
 * This filter will intercept all requests to validate the presence of the JWT–that is, the ones that are not issued
 * to / nor /users. This validation is done with the help of the TokenAuthenticationService class.
 */
class JWTAuthenticationFilter: GenericFilterBean() {

    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
        // Must be set as null type with ?
        val authentication: Authentication? = TokenAuthenticationService.getAuthentication((p0 as HttpServletRequest?)!!) as? Authentication
        SecurityContextHolder.getContext()
                .authentication = authentication

        p2!!.doFilter(p0, p1)
    }


}