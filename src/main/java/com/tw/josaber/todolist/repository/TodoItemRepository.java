package com.tw.josaber.todolist.repository;

import com.tw.josaber.todolist.entity.TodoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Integer> {
}
