package com.tw.josaber.controller;

import com.tw.josaber.entity.TodoItem;
import com.tw.josaber.service.TodoItemService;
import com.tw.josaber.utils.DateTimeUtil;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TodoItemsControllerTest {

    @Mock
    TodoItemService todoItemService;
    TodoItemsController todoItemsController;
    TodoItem todoItem;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        todoItemsController = new TodoItemsController(todoItemService);

        todoItem = new TodoItem()
                .setText("test todo item")
                .setDone(false)
                .setTimestamp(DateTimeUtil.getCurrentTime());
    }

}
