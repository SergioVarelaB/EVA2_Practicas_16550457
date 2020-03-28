package com.example.eva2_7_sqlite2;

public class Info {
    public String name;
    public String phone;

    public Info() {
        name = "prueba";
        phone = "123456789";
    }

    public Info(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
