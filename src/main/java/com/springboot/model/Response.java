package com.springboot.model;


 public  class  Response{
     boolean success;
     String message;

     public Response(boolean success,String message){
         this.success = success;
         this.message = message;
     }
     public void setMessage(String message) {
         this.message = message;
     }

     public void setSuccess(boolean success) {
         this.success = success;
     }

     public String getMessage() {
         return message;
     }
     public boolean getSuccess() {
         return this.success;
     }
 }