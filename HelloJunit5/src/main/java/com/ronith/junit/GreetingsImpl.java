package com.ronith.junit;

public class GreetingsImpl implements Greetings{
    @Override
    public String greet(String name) {

        if(name==null||name.length()==0) {
            throw new IllegalStateException();
        }

        return "Hello " + name;
    }
}
