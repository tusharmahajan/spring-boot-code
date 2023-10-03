package com.tushar.swiggy.todo;

public class Products {

    private final int id;
    private final String name;

    public Products(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
