package com.tw.josaber.controller;

import com.tw.josaber.model.ResponseMessage;
import com.tw.josaber.model.TodoItem;
import com.tw.josaber.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public ResponseEntity<?> createTodoItems(@RequestBody TodoItem newTodoItem) {
        newTodoItem.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
        ResponseMessage message = todoItemService.createTodoItem(newTodoItem);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodoItems(@PathVariable int id, @RequestBody TodoItem updateTodoItem) {
        updateTodoItem.setId(id);
        updateTodoItem.setTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
        ResponseMessage message = todoItemService.updateTodoItem(updateTodoItem);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodoItems(@PathVariable int id) {
        ResponseMessage message = todoItemService.deleteTodoItemById(id);
        return ResponseEntity.status(message.getCode()).body(message);
    }
}
