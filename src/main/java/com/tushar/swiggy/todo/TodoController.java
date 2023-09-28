package com.tushar.swiggy.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public void addTodo(@PathVariable int id, @RequestBody String description){
        todoService.addTodo(id, description);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public String getTodo(@PathVariable int id){
        return todoService.getTodo(id);
    }

    @RequestMapping(value = "/getAll/todos", method = RequestMethod.GET)
    public List<String> getAllTodos(){
        return todoService.getAllTodos();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable int id){
        todoService.deleteTodo(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public void updateTodo(@PathVariable int id, @RequestBody String description){
        todoService.updateTodo(id, description);
    }
}
