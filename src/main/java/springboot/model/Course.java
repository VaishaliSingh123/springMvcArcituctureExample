package com.springboot.model;

import java.util.List;

public class Course{
    private String id;
    private String name;
    private String description;
    private List<String> steps;

    public Course(){

    }

    public Course(String id,String name,String description,List<String>  steps){
       super();
       this.id=id;
       this.name=name;
       this.description=description;
       this.steps=steps;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name){
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getSteps(){
        return steps;
    }
}