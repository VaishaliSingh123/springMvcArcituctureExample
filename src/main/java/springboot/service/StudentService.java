package com.springboot.service;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.springboot.model.Course;
import com.springboot.model.Student;


@Component
public class StudentService{

    private static List<Student> students=new ArrayList<>();
    private static List<Course> course=new ArrayList<>();

    static{
     //Initialize Data
//        Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
//                .asList("Learn Maven", "Import Project", "First Example",
//                        "Second Example"));
//        Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
//                Arrays.asList("Learn Maven", "Import Project", "First Example",
//                        "Second Example"));
//        Course course3 = new Course("Course3", "Spring Boot", "6K Students",
//                Arrays.asList("Learn Maven", "Learn Spring",
//                        "Learn Spring MVC", "First Example", "Second Example"));
//        Course course4 = new Course("Course4", "Maven",
//                "Most popular maven course on internet!", Arrays.asList(
//                "Pom.xml", "Build Life Cycle", "Parent POM",
//                "Importing into Eclipse"));
//
//        Student ranga = new Student("Student1", "Ranga Karanam",
//                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
//                .asList(course1, course2, course3, course4)));
//
//        Student satish = new Student("Student2", "Satish T",
//                "Hiker, Programmer and Architect", new ArrayList<>(Arrays
//                .asList(course1, course2, course3, course4)));
//
//        course.add(course1);
//        course.add(course2);
//        course.add(course3);
//        course.add(course4);
//
//
//
//        students.add(ranga);
//        students.add(satish);
    }

    public List<Student> retrieveAllStudents(){
        return students;
    }

    public List<Course> retrieveAllCourses(){
        return course;
    }

}