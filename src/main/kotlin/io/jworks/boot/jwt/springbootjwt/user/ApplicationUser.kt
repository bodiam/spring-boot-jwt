package io.jworks.boot.jwt.springbootjwt.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class ApplicationUser (
    var username: String? = null,
    var password: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null,
)