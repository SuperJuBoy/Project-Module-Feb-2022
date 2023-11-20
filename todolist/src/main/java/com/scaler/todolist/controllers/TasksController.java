package com.scaler.todolist.controllers;

import com.scaler.todolist.models.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/tasks")
@RestController
public class TasksController {

    private Map<String,Task> taskMap = new HashMap<String,Task>();

    @GetMapping("/")
    ResponseEntity<List<Task>> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        for(String id: taskMap.keySet()){
            taskList.add(taskMap.get(id));
        }

        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/")
    ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        Task taskToAdd = new Task(task.getId(),task.getName());
        taskMap.put(taskToAdd.getId(),taskToAdd);
        return ResponseEntity.status(201).body(taskToAdd);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getTaskById(@PathVariable String id) {
        if(taskMap.containsKey(id)){
            return ResponseEntity.status(200).body(taskMap.get(id));
        }else{
            return ResponseEntity.status(404).body("Task not Found");
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<Object> patchTaskById(@PathVariable String id,@RequestBody Task task) {

        if(taskMap.containsKey(id)){
            Task taskToUpdate = new Task(id,task.getName());
            taskMap.put(taskToUpdate.getId(),taskToUpdate);
            return ResponseEntity.status(201).body(taskToUpdate);
        }else{
            return ResponseEntity.status(404).body("Task not Found");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteTaskById(@PathVariable String id) {

        if(taskMap.containsKey(id)){
            taskMap.remove(id);
            return ResponseEntity.status(202).body("Task successfully deleted");
        }else{
            return ResponseEntity.status(404).body("Task not Found");
        }
    }

    /*
     * ASSIGNMENT:
     *  1. GET -> /tasks/3
     *          get task no 3
     *          send 404 error to client if task no 3 does not exist
     *  2. PATCH -> /tasks/2
     *          update due date or done status for task no 2
     *          send 404 error to client if task no 3 does not exist
     *  3. DELETE -> tasks/5
     *          delete task no 5 (response with correct HTTP code)
     *          if task 5 does not exist, send 404
     */


}
