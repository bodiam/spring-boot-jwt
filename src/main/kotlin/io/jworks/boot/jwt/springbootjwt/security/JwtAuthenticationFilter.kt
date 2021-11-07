package io.jworks.boot.jwt.springbootjwt.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.EXPIRATION_TIME
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.HEADER_STRING
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.SECRET
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.TOKEN_PREFIX
import io.jworks.boot.jwt.springbootjwt.user.ApplicationUser
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val creds: ApplicationUser = ObjectMapper().readValue(req.inputStream)

        println("Creds ${creds.username} ${creds.password}")

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                creds.username,
                creds.password,
                emptyList()
            )
        )
    }

    override fun successfulAuthentication(
        req: HttpServletRequest?,
        res: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication
    ) {
        val token: String = Jwts.builder()
            .setSubject((auth.principal as User).username)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }
}