package com.example;

public class CallMe {

    String mName;

    public CallMe() {
        this("Default Java CallMe Class");
    }
    
    public CallMe(String name) {
        this.mName = name;
    }
    
    public void hello() {
        System.out.println("Hello from " + mName);
    }
    
    public static void main(String []args) {
        System.out.println("Called main");
    }
}