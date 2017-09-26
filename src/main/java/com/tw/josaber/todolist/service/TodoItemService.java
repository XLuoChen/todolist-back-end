package com.tw.josaber.todolist.service;

import com.tw.josaber.todolist.entity.TodoItem;
import com.tw.josaber.todolist.repository.TodoItemRepository;
import com.tw.josaber.todolist.model.ResponseMessage;
import com.tw.josaber.todolist.utils.SynaxSugar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new ResponseMessage(SynaxSugar.CREATE_SUCC_CODE, SynaxSugar.CREATE_SUCC_MESSAGE, resultTodoItem);
    }

    public ResponseMessage updateTodoItem(TodoItem updateTodoItem) {
        if(todoItemRepository.exists(updateTodoItem.getId())) {
            TodoItem todoItem = todoItemRepository.findOne(updateTodoItem.getId());
            todoItem.setDone(updateTodoItem.isDone());
            todoItem.setTimestamp(updateTodoItem.getTimestamp());
            todoItem.setText(updateTodoItem.getText());
            TodoItem resultTodoItem = todoItemRepository.save(todoItem);
            return new ResponseMessage(SynaxSugar.UPDATE_SUCC_CODE, SynaxSugar.UPDATE_SUCC_MESSAGE, resultTodoItem);
        } else {
            return new ResponseMessage(SynaxSugar.UPDATE_FAIL_CODE, SynaxSugar.UPDATE_FAIL_MESSAGE);
        }
    }

    public ResponseMessage deleteTodoItemById(int id) {
        if(todoItemRepository.exists(id)) {
            todoItemRepository.delete(id);
            return new ResponseMessage(SynaxSugar.DELETE_SUCC_CODE, SynaxSugar.DELETE_SUCC_MESSAGE);
        } else {
            return new ResponseMessage(SynaxSugar.DELETE_FAIL_CODE, SynaxSugar.DELETE_FAIL_MESSAGE);
        }
    }
}
