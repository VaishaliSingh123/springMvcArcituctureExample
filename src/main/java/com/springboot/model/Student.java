package com.springboot.model;

import java.util.List;

public class Student{
    private String id;
    private String name;
    private String description;
    private List<String> courses;

    public Student(){

    }

    public Student(String id,String name,String description,List<String>  courses){
        super();
        this.id=id;
        this.name=name;
        this.description=description;
        this.courses = courses;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString(){
        return "id="+this.id+"\nname="+this.name+"\ndescription="+this.description+"\ncourses"+this.courses;
    }
}