package io.jworks.boot.jwt.springbootjwt.task

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Task (
    var description: String? = null,

    @Id
    @GeneratedValue
    var id: Long? = null,
)

