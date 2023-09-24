package com.tushar.swiggy.todo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TodoService {

    private final Map<Integer,String> todos;

    public TodoService() {
        this.todos = new HashMap<>();
    }

    public void addTodo(int id, String description) {
        todos.put(id, description);
    }

    public String getTodo(int id) {
        return todos.get(id);
    }

    public List<String> getAllTodos() {
        List<String> list = new ArrayList<>();
        for(Map.Entry<Integer, String> entry : todos.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    public void deleteTodo(int id) {
        todos.remove(id);
    }

    public void updateTodo(int id, String description) {
        todos.put(id, description);
    }
}
