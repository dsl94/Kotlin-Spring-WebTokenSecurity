package com.example.kotlin.jwt.kotlinjwt.security.filter

import com.example.kotlin.jwt.kotlinjwt.security.TokenAuthenticationService
import com.example.kotlin.jwt.kotlinjwt.security.dto.AccountCredentials
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.*
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Nemanja on 6/12/17.
 */
class JWTLoginFilter(var url:String, var authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(url) {
    init {
        authenticationManager = authManager
    }

    override fun attemptAuthentication(httpServletRequest: HttpServletRequest?, httpServletResponse: HttpServletResponse?): Authentication {
        // Reading username and password from request and mapping them to class that will represent it
        val mapper = jacksonObjectMapper()
        val creds: AccountCredentials = mapper.readValue<AccountCredentials>(httpServletRequest!!.inputStream)

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(creds.username, creds.password,
                                                                                        emptyList()))
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        TokenAuthenticationService.addAuthentication(response!!, authResult!!.name)
    }
}