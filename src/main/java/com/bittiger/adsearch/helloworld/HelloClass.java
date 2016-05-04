package com.bittiger.adsearch.helloworld;

public class HelloClass {
    public String message = "Hello";

    public HelloClass () {
    }

    public HelloClass (String query) {
        this.message = "Your query is " + query + "!";
    }

    public String getMessage() {
        return message;
    }
}
