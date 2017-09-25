package com.tw.josaber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/todoitems")
public class TodoItemsController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map> getTodoItems() {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createTodoItems(@RequestBody Object o) {
        return null;
    }
}
