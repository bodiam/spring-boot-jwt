package io.jworks.boot.jwt.springbootjwt.task

import org.springframework.data.jpa.repository.JpaRepository


interface TaskRepository : JpaRepository<Task, Long>