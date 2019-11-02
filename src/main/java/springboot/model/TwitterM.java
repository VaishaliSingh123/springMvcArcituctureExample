package com.springboot.model;

public class TwitterM{
    private String tweetText;

    public TwitterM(){

    }

    public TwitterM(String tweetText){
        super();
        this.tweetText=tweetText;
    }

    public String getTweet() {
        return this.tweetText;
    }
    public void setTweet(String tweetText){
        this.tweetText=tweetText;
    }

    @Override
    public String toString(){
        return "tweet="+this.tweetText;
    }
}