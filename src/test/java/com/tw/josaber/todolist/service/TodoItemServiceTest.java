package com.tw.josaber.todolist.service;

import com.tw.josaber.todolist.entity.TodoItem;
import com.tw.josaber.todolist.model.ResponseMessage;
import com.tw.josaber.todolist.repository.TodoItemRepository;
import com.tw.josaber.todolist.utils.DateTimeUtil;
import com.tw.josaber.todolist.utils.SynaxSugar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TodoItemServiceTest {

    @Mock
    private TodoItemRepository todoItemRepository;

    private TodoItem todoItem;
    private TodoItemService todoItemService;

    @Before
    public void setUp() {
        initMocks(this);

        todoItem = new TodoItem().setText("test text - service")
                .setDone(false)
                .setTimestamp(DateTimeUtil.getCurrentTime());

        todoItemService = new TodoItemService(todoItemRepository);
    }

    @Test
    public void shouldGetAllTodoItems() {
        List<TodoItem> todoItems = Arrays.asList(todoItem);

        when(todoItemRepository.findAll()).thenReturn(todoItems);
        Assert.assertEquals(todoItem, todoItemService.getTodoItemsMap().get("items").get(0));
    }

    @Test
    public void shouldCreateTodoItem() {
        when(todoItemRepository.save(todoItem)).thenReturn(todoItem.setId(5));

        assertEquals(new ResponseMessage(SynaxSugar.CREATE_SUCC_CODE, SynaxSugar.CREATE_SUCC_MESSAGE, todoItem),
                todoItemService.createTodoItem(todoItem));
    }

    @Test
    public void shouldUpdateTodoItemWhenItemExists() {
        todoItem.setId(6);
        when(todoItemRepository.exists(todoItem.getId())).thenReturn(true);
        when(todoItemRepository.findOne(todoItem.getId())).thenReturn(todoItem);
        when(todoItemRepository.save(todoItem)).thenReturn(todoItem);

        assertEquals(new ResponseMessage(SynaxSugar.UPDATE_SUCC_CODE, SynaxSugar.UPDATE_SUCC_MESSAGE, todoItem),
                todoItemService.updateTodoItem(todoItem));
    }

    @Test
    public void shouldNotUpdateTodoItemWhenItemNotExists() {
        todoItem.setId(7);
        when(todoItemRepository.exists(todoItem.getId())).thenReturn(false);

        assertEquals(new ResponseMessage(SynaxSugar.UPDATE_FAIL_CODE, SynaxSugar.UPDATE_FAIL_MESSAGE),
                todoItemService.updateTodoItem(todoItem));
    }

    @Test
    public void shouldDeleteTodoItemWhenItemExists() {
        int id = 8;
        when(todoItemRepository.exists(id)).thenReturn(true);

        assertEquals(new ResponseMessage(SynaxSugar.DELETE_SUCC_CODE, SynaxSugar.DELETE_SUCC_MESSAGE),
                todoItemService.deleteTodoItemById(id));
    }

    @Test
    public void shouldNotDeleteTodoItemWhenItemNotExists() {
        int id = 9;
        when(todoItemRepository.exists(id)).thenReturn(false);

        assertEquals(new ResponseMessage(SynaxSugar.DELETE_FAIL_CODE, SynaxSugar.DELETE_FAIL_MESSAGE),
                todoItemService.deleteTodoItemById(id));
    }
}
