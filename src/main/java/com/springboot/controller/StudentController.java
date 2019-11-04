package com.springboot.controller;

import com.springboot.model.Course;
import com.springboot.model.Response;
import com.springboot.model.Student;
import com.springboot.model.TwitterM;
import com.springboot.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import twitter4j.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;





@Component
@ConfigurationProperties
@RestController
public class StudentController{

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private ArrayList<Student> students;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TwitterM twitterM;

    public StudentController(){
        this.students = new ArrayList<Student>();
//        this.twitterM = new TwitterM();
    }

    @RequestMapping(value="/students")
    public List<Student> retrieveStudents() {
        /**
         * reading twitter4j.properties file
         */
        try (InputStream input = new FileInputStream("/Users/vaishali.singh/Workspace/StudentResourceSpringBoot/src/main/resources/twitter4j.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println("cosumer key is"+prop.getProperty("oauth.consumerKey"));
            System.out.println("cosumer key is"+prop.getProperty("oauth.consumerSecret"));
            System.out.println("cosumer key is"+prop.getProperty("oauth.accessToken"));
            System.out.println("cosumer key is"+prop.getProperty("oauth.accessTokenSecret"));

            System.out.println("db key is"+prop.getProperty("db.user"));

            prop.setProperty("db.user", "vaishali");

            System.out.println("db key is"+prop.getProperty("db.user"));

            System.out.println("db key is"+prop.keySet());

            prop.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
            /**
             * configration builder
             */
//            ConfigurationBuilder cb = new ConfigurationBuilder();
//            cb.setDebugEnabled(true)
//                    .setOAuthConsumerKey("*********************")
//                    .setOAuthConsumerSecret("******************************************")
//                    .setOAuthAccessToken("**************************************************")
//                    .setOAuthAccessTokenSecret("******************************************");
//            TwitterFactory tf = new TwitterFactory(cb.build());
//            Twitter twitter = tf.getInstance();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

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


    /* 1st method for the post operation */
/*
    @RequestMapping(value="/students",method=RequestMethod.PUT)
    public Response  updateStudent(@RequestBody Student student){
        if(student.getId()!=null){
            Iterator itr = students.iterator();
            for(int i=0; i < students.size(); i++)
            {
                if(students.get(i).getId().equals(student.getId())){
                    students.set(i, student);
                    return new Response(true,"student info is updated");
                }

            }
        }
        return new Response(false,"student info cannot be updated");
    }
*/
    /* 2nd method for the post operation {Using Path Variable}*/

   /* @PutMapping("/students/{id}")
    public Response  updateStudent(@RequestBody Student student, @PathVariable("id") String id){
        if(student.getId()!=null){
            Iterator itr = students.iterator();
            for(int i=0; i < students.size(); i++)
            {
                if(students.get(i).getId().equals(id)){
                    students.set(i, student);
                    return new Response(true,"student info is updated");
                }

            }
        }
        return new Response(false,"student info cannot be updated");
    }
*/
    /* 3rd method for the post operation {Using RequestParam Variable}*/

    @PutMapping("/students")
    public Response  updateStudent(@RequestBody Student student, @RequestParam("id") String id){
        if(student.getId()!=null){
            Iterator itr = students.iterator();
            for(int i=0; i < students.size(); i++)
            {
                if(students.get(i).getId().equals(id)){
                    students.set(i, student);
                    return new Response(true,"student info is updated");
                }

            }
        }
        return new Response(false,"student info cannot be updated");
    }

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

    @RequestMapping("/vaishalitweet")
    public Response getTwitterProp(){
        try {
            // gets Twitter instance with default credentials
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + "date-"+status.getCreatedAt()+"number od likes-"+status.getFavoriteCount());
                logger.debug("Getting successResponse for get Api", status.getText());
                return new Response(true,status.getText());

            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            logger.debug("unable to get post", te.getMessage());
            return  new Response(false,"unable to get post");
        }

        logger.debug("unable to get post");
        return  new Response(false,"unable to get post");
    }

    /**
     * u need to check this
     * @param tweetText
     * @return
     */
    @RequestMapping(value="/vaishali",method=RequestMethod.POST)
    public Response postTwitter(@RequestBody TwitterM tweetText){
        try {
            System.out.println("tweetText:"+tweetText.getTweetText());
            Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus(tweetText.getTweetText());
            System.out.println("Successfully updated the status to [" + status.getText() + "].");

            logger.debug("Getting successResponse for post Api", status.getText());
            return new Response(true,"posted successfully");
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to post at timeline: " + te.getMessage());
            logger.debug("unable to post post", te.getMessage());
            return  new Response(false,"unable to post");
        }
        //return  new Response(false,"unable to post post");
    }

}