package com.tw.josaber.todolist.model;

import com.tw.josaber.todolist.entity.TodoItem;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseMessage message1 = (ResponseMessage) o;

        if (status != message1.status) return false;
        return message != null ? message.equals(message1.message) : message1.message == null;
    }

    @Override
    public int hashCode() {
        int result = status;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
