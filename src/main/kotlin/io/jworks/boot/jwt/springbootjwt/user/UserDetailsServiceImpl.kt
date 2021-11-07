package io.jworks.boot.jwt.springbootjwt.user

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val applicationUserRepository: ApplicationUserRepository) :
    UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val applicationUser = applicationUserRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)

        return User(applicationUser.username, applicationUser.password, emptyList())
    }
}