package org.example.bq;

import java.sql.Timestamp;

public class Student {
    private String name;
    private int id;
    private String city;
    private String timestamp;

    public Student(String name, int id, String city, String timestamp) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
