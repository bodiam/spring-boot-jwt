package io.jworks.boot.jwt.springbootjwt.user

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {
    fun findByUsername(username: String?): ApplicationUser?
}