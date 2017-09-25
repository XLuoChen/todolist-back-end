package com.tw.josaber.controller;

import com.tw.josaber.service.TodoItemService;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class TodoItemsControllerTest {

    @Mock
    TodoItemService todoItemService;

    TodoItemsController todoItemsController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        todoItemsController = new TodoItemsController(todoItemService);
    }

}
