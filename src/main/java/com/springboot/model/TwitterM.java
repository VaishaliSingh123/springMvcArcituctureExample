package com.springboot.model;


import org.springframework.stereotype.Service;

@Service
public class TwitterM{
    private String tweetText;

    public TwitterM(){

    }

    public TwitterM(String tweetText){
        super();
        this.tweetText=tweetText;
    }

    public String getTweetText() {
        return this.tweetText;
    }
    public void setTweetText(String tweetText){
        this.tweetText=tweetText;
    }

    @Override
    public String toString(){
        return "tweet="+this.tweetText;
    }
}