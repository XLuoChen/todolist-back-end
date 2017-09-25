package com.tw.josaber.model;

import com.tw.josaber.entity.TodoItem;

public class ResponseMessage {
    private int code;
    private String message;
    private TodoItem todoItem;

    public ResponseMessage(int code, String message, TodoItem todoItem) {
        this.code = code;
        this.message = message;
        this.todoItem = todoItem;
    }

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }
}
