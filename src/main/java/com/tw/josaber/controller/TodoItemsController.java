package com.tw.josaber.controller;

import com.tw.josaber.model.ResponseMessage;
import com.tw.josaber.entity.TodoItem;
import com.tw.josaber.service.TodoItemService;
import com.tw.josaber.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/todoitems")
public class TodoItemsController {

    private TodoItemService todoItemService;

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
        newTodoItem.setTimestamp(DateTimeUtil.getCurrentTime());
        ResponseMessage message = todoItemService.createTodoItem(newTodoItem);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodoItems(@PathVariable int id, @RequestBody TodoItem updateTodoItem) {
        updateTodoItem.setId(id);
        updateTodoItem.setTimestamp(DateTimeUtil.getCurrentTime());
        ResponseMessage message = todoItemService.updateTodoItem(updateTodoItem);
        return ResponseEntity.status(message.getCode()).body(message);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodoItems(@PathVariable int id) {
        ResponseMessage message = todoItemService.deleteTodoItemById(id);
        return ResponseEntity.status(message.getCode()).body(message);
    }
}
