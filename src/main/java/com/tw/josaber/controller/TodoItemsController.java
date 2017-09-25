package com.tw.josaber.controller;

import com.tw.josaber.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/todoitems")
public class TodoItemsController {

    private final TodoItemService todoItemService;

    @Autowired
    public TodoItemsController(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map> getTodoItems() {
        return ResponseEntity.ok(todoItemService.getTodoItemsMap());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTodoItems(@RequestBody Object o) {
        return null;
    }
}
