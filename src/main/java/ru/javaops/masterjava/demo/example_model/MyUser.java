package ru.javaops.masterjava.demo.example_model;

public class MyUser {
    int id;
    String typeProduct;
    String typeChanel;
    int count;

    public MyUser() {
    }

    public MyUser(int id, String typeProduct, String typeChanel, int count) {
        this.id = id;
        this.typeProduct = typeProduct;
        this.typeChanel = typeChanel;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(String typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getTypeChanel() {
        return typeChanel;
    }

    public void setTypeChanel(String typeChanel) {
        this.typeChanel = typeChanel;
    }
}