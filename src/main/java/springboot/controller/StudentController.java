package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.springboot.service.StudentService;
import com.springboot.model.Student;
import com.springboot.model.Course;
import com.springboot.model.Response;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.ListIterator;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController{

    @Autowired
    private StudentService studentService;
    private ArrayList<Student> students;

    public StudentController(){
        this.students = new ArrayList<Student>();
    }

    @RequestMapping(value="/students")
    public List<Student> retrieveStudents() {
        return students;
    }

    @RequestMapping(value="/course")
    public List<Course> retrieveCourses() {
        return studentService.retrieveAllCourses();
    }

    @RequestMapping(value="/students",method=RequestMethod.POST)
    public Response  addStudent(@RequestBody Student student){
        if(student.getCourses()==null||student.getCourses().size()==0){
            return new Response(false,"courses can not be empty");

        }else if(student.getName()==null){
            return new Response(false,"name cannot be null");
        }else if(student.getDescription()==null){
            return new Response(false,"Description cannot be null");
        }
        else {
            Iterator itr = students.iterator();
            while (itr.hasNext())
            {
                Student oneStd=(Student)itr.next();
                if(oneStd.getId().equals(student.getId())){
                    return new Response(false,"Id Must be unique");
                }
            }
            students.add(student);
            return new Response(true,"student added");
        }
    }

   /* @RequestMapping(value="/students/{id}",method=RequestMethod.PUT)
    public Response  updateStudent(@RequestBody Student student, @PathVariable("id") String id){
        if(student.getCourses()==null||student.getCourses().size()==0){
            return new Response(false,"courses can not be empty"

        }else if(student.getName()==null){
            return new Response(false,"name cannot be null");
        }else if(student.getDescription()==null){
            return new Response(false,"Description cannot be null");
        }
        else {
            Iterator itr = students.iterator();
            while (itr.hasNext())
            {
                Student oneStd=(Student)itr.next();
                if(oneStd.getId().equals(student.getId())){
                    int index = students.indexOf(oneStd.getId());
                     students.set(index,student);
                    return new Response(false,"specific value id updated");
                }
            }
            return new Response(true,"student added");
        }
    }*/

    @RequestMapping(value="/students/{id}",method=RequestMethod.DELETE)
    public Response deleteStudent(@PathVariable("id") String id){
           Iterator itr = students.iterator();
           while (itr.hasNext())
           {
               Student oneStd=(Student)itr.next();
               if(oneStd.getId().equals(id)){
                   itr.remove();
                   return new Response(true,"Student successfully deleted");
               }
           }
           return new Response(false,"unable to add student");
    }
}