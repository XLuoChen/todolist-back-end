package com.tw.josaber.service;

import com.tw.josaber.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoItemService {
    public Map getTodoItemsMap() {
        Map<String, List<TodoItem>> todoItemMap = new HashMap<String, List<TodoItem>>();
        // TODO
        todoItemMap.put("items", new ArrayList<TodoItem>());
        return todoItemMap;
    }
}
