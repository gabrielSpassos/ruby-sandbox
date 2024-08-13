package com.gabrielspassos;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        System.out.println(engine);
    }

}