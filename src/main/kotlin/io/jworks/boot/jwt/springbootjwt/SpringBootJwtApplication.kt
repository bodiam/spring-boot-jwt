package io.jworks.boot.jwt.springbootjwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootJwtApplication

fun main(args: Array<String>) {
    runApplication<SpringBootJwtApplication>(*args)
}
