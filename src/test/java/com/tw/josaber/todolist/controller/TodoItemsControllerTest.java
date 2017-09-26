package com.tw.josaber.todolist.controller;

import com.tw.josaber.todolist.entity.TodoItem;
import com.tw.josaber.todolist.Application;
import com.tw.josaber.todolist.model.ResponseMessage;
import com.tw.josaber.todolist.service.TodoItemService;
import com.tw.josaber.todolist.utils.SynaxSugar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TodoItemsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoItemService todoItemService;

    private TodoItemsController todoItemsController;
    private TodoItem todoItem;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        todoItemsController = new TodoItemsController(todoItemService);
        mockMvc = MockMvcBuilders.standaloneSetup(todoItemsController).build();

        todoItem = new TodoItem()
                .setId(6)
                .setText("test todo item")
                .setDone(false)
                .setTimestamp("2017-09-26T10:33:21Z");
    }

    @Test
    public void shouldGetTodoItems() throws Exception {
        Map<String, List<TodoItem>> todoItemsMap = new HashMap<String, List<TodoItem>>();
        todoItemsMap.put("items", Arrays.asList(todoItem));

        when(todoItemService.getTodoItemsMap()).thenReturn(todoItemsMap);

        mockMvc.perform(get("http://localhost:8080/todoitems"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString(
                        "{\"items\":[{\"id\":6,\"text\":\"test todo item\","
                                + "\"done\":false,\"timestamp\":\"2017-09-26T10:33:21Z\"}]}"
                )));
    }

    @Test
    public void shouldCreateTodoItem() throws Exception {
        when(todoItemService.createTodoItem((TodoItem) anyObject())).thenReturn(new ResponseMessage(
                SynaxSugar.CREATE_SUCC_CODE, SynaxSugar.CREATE_SUCC_MESSAGE, todoItem
        ));

        mockMvc.perform(post("http://localhost:8080/todoitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":5,\"text\":\"test case\",\"done\":false,\"timestamp\":\"2015-01-21T11:33:21Z\"}"))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().string(
                        "{\"status\":" + SynaxSugar.CREATE_SUCC_CODE+ ","
                                + "\"message\":\"" + SynaxSugar.CREATE_SUCC_MESSAGE + "\","
                                + "\"todoItem\":{\"id\":6,\"text\":\"test todo item\","
                                + "\"done\":false,\"timestamp\":\"2017-09-26T10:33:21Z\"}}"
                ));
    }

    @Test
    public void shouldUpdateTodoItemWhenItemExists() throws Exception {
        when(todoItemService.updateTodoItem(todoItem.setId(5))).thenReturn(new ResponseMessage(
                SynaxSugar.UPDATE_SUCC_CODE, SynaxSugar.UPDATE_SUCC_MESSAGE, todoItem
        ));

        int id = 5;
        mockMvc.perform(put("http://localhost:8080/todoitems/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":" + id + ",\"text\":\"test todo item\",\"done\":true,\"timestamp\":\"2017-09-26T10:33:21Z\"}"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(
                        "{\"status\":" + SynaxSugar.UPDATE_SUCC_CODE+ ","
                                + "\"message\":\"" + SynaxSugar.UPDATE_SUCC_MESSAGE + "\","
                                + "\"todoItem\":{\"id\":5,\"text\":\"test todo item\","
                                + "\"done\":false,\"timestamp\":\"2017-09-26T10:33:21Z\"}}"
                ));
    }


    @Test
    public void shouldNotUpdateTodoItemWhenItemNotExists() throws Exception {
        when(todoItemService.updateTodoItem(todoItem.setId(6))).thenReturn(new ResponseMessage(
                SynaxSugar.UPDATE_FAIL_CODE, SynaxSugar.UPDATE_FAIL_MESSAGE
        ));

        int id = 6;
        mockMvc.perform(put("http://localhost:8080/todoitems/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":" + id + ",\"text\":\"test todo item\",\"done\":true,\"timestamp\":\"2017-09-26T10:33:21Z\"}"))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(
                        "{\"status\":" + SynaxSugar.UPDATE_FAIL_CODE+ ","
                        + "\"message\":\"" + SynaxSugar.UPDATE_FAIL_MESSAGE + "\","
                        + "\"todoItem\":null}"
                ));
    }

    @Test
    public void shouldDeleteTodoItemWhenItemExists() throws Exception {
        when(todoItemService.deleteTodoItemById(7)).thenReturn(new ResponseMessage(
                SynaxSugar.DELETE_SUCC_CODE, SynaxSugar.DELETE_SUCC_MESSAGE
        ));

        int id = 7;
        mockMvc.perform(delete("http://localhost:8080/todoitems/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotDeleteTodoItemWhenItemNotExists() throws Exception {
        when(todoItemService.deleteTodoItemById(8)).thenReturn(new ResponseMessage(
                SynaxSugar.DELETE_FAIL_CODE, SynaxSugar.DELETE_FAIL_MESSAGE
        ));

        int id = 8;
        mockMvc.perform(delete("http://localhost:8080/todoitems/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().string(
                        "{\"status\":" + SynaxSugar.DELETE_FAIL_CODE+ ","
                        + "\"message\":\"" + SynaxSugar.DELETE_FAIL_MESSAGE + "\","
                        + "\"todoItem\":null}"
                ));
    }

}
