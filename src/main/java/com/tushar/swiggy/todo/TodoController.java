package com.tushar.swiggy.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
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
    public List<Products> getAllTodos(){
        String productsUrl = "http://localhost:8081/products/getAll";
        RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(2)).
                                    setReadTimeout(Duration.ofSeconds(4)).build();
        List<Products> products = restTemplate.getForObject(productsUrl, List.class);
        return products;
//        return todoService.getAllTodos();
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
