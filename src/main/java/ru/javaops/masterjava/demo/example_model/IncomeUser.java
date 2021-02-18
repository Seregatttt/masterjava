package ru.javaops.masterjava.demo.example_model;

public class IncomeUser {
    int id;
    String typeProduct;
    String typeChanel;
    String name;
    String flag;

    public IncomeUser() {
    }

    public IncomeUser(int id, String typeProduct, String typeChanel, String name, String flag) {
        this.id = id;
        this.typeProduct = typeProduct;
        this.typeChanel = typeChanel;
        this.name = name;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "IncomeUser{" +
                "id=" + id +
                ", typeProduct='" + typeProduct + '\'' +
                ", typeChanel='" + typeChanel + '\'' +
                ", name='" + name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
