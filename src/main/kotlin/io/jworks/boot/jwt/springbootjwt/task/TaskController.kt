package io.jworks.boot.jwt.springbootjwt.task

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/tasks")
class TaskController(private val taskRepository: TaskRepository) {

    @PostMapping
    fun addTask(@RequestBody task: Task) = taskRepository.save(task)

    @GetMapping
    fun tasks(): List<Task> = taskRepository.findAll()

    @PutMapping("/{id}")
    fun editTask(@PathVariable id: Long, @RequestBody task: Task) {
        val existingTask = taskRepository.findById(id).orElseThrow { IllegalArgumentException("Couldn't find task with id $id") }
        existingTask.description = task.description
        taskRepository.save(existingTask)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) = taskRepository.deleteById(id)
}