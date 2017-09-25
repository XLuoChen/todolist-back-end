package com.tw.josaber.model;

import com.tw.josaber.entity.TodoItem;

public class ResponseMessage {
    private int status;
    private String message;
    private TodoItem todoItem;

    public ResponseMessage(int status, String message, TodoItem todoItem) {
        this.status = status;
        this.message = message;
        this.todoItem = todoItem;
    }

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }
}
