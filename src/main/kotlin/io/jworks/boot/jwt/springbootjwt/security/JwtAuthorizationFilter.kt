package io.jworks.boot.jwt.springbootjwt.security

import io.jsonwebtoken.Jwts
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.HEADER_STRING
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.SECRET
import io.jworks.boot.jwt.springbootjwt.security.SecurityConstants.TOKEN_PREFIX
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(authManager: AuthenticationManager?) : BasicAuthenticationFilter(authManager) {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = req.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }
        val authentication = getAuthentication(req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_STRING) ?: return null

        // parse the token.
        val user: String? = Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .body
            .subject

        return if (user != null) {
            UsernamePasswordAuthenticationToken(user, null, ArrayList())
        } else null
    }
}