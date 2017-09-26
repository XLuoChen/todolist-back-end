package com.tw.josaber.service;

import com.tw.josaber.repository.TodoItemRepository;
import com.tw.josaber.model.ResponseMessage;
import com.tw.josaber.entity.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tw.josaber.utils.SynaxSugar.*;

@Service
public class TodoItemService {

    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public Map<String, List<TodoItem>> getTodoItemsMap() {
        Map<String, List<TodoItem>> todoItemMap = new HashMap<String, List<TodoItem>>();
        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        for (TodoItem todoItem: todoItemRepository.findAll()) {
            todoItems.add(todoItem);
        }
        todoItemMap.put("items", todoItems);
        return todoItemMap;
    }

    public ResponseMessage createTodoItem(TodoItem newTodoItem) {
        TodoItem todoItem = new TodoItem();
        todoItem.setText(newTodoItem.getText());
        todoItem.setDone(false);
        todoItem.setTimestamp(newTodoItem.getTimestamp());
        TodoItem resultTodoItem = todoItemRepository.save(todoItem);
        return new ResponseMessage(CREATE_SUCC_CODE, CREATE_SUCC_MESSAGE, resultTodoItem);
    }

    public ResponseMessage updateTodoItem(TodoItem updateTodoItem) {
        if(todoItemRepository.exists(updateTodoItem.getId())) {
            TodoItem todoItem = todoItemRepository.findOne(updateTodoItem.getId());
            todoItem.setDone(updateTodoItem.isDone());
            todoItem.setTimestamp(updateTodoItem.getTimestamp());
            todoItem.setText(updateTodoItem.getText());
            TodoItem resultTodoItem = todoItemRepository.save(todoItem);
            return new ResponseMessage(UPDATE_SUCC_CODE, UPDATE_SUCC_MESSAGE, resultTodoItem);
        } else {
            return new ResponseMessage(UPDATE_FAIL_CODE, UPDATE_FAIL_MESSAGE);
        }
    }

    public ResponseMessage deleteTodoItemById(int id) {
        if(todoItemRepository.exists(id)) {
            todoItemRepository.delete(id);
            return new ResponseMessage(DELETE_SUCC_CODE, DELETE_SUCC_MESSAGE);
        } else {
            return new ResponseMessage(DELETE_FAIL_CODE, DELETE_FAIL_MESSAGE);
        }
    }
}
