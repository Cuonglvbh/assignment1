package com.mycompany.selfworks;

public class Student {
    private String id;
    private String name;
    public float marks;

    public Student(String id, String name, float marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }
    
    @Override
    public String toString() {
        return "Student id:'" + id + "', name:'" + name + "', grade:" + marks ;
    }
}
