package com.gabrielspassos;

import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Jsr223Sample {

    private final static String filename = "Math.rb";

    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        var manager = new ScriptEngineManager();
        var engine = manager.getEngineByName("jruby");

        ClassLoader classLoader = Jsr223Sample.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        Reader reader = new FileReader(file);
        Object receiver = engine.eval(reader);

        Object[] squareParams = new Object[1];
        squareParams[0] = 144;
        Object squareResult = ((Invocable)engine).invokeFunction("square", squareParams);
        System.out.println("Square of " + squareParams[0] + " = " + squareResult);

        engine.put("$pi", 3.14);
        Object[] circleAreaParam = new Object[1];
        circleAreaParam[0] = 5;
        Object circleAreaResult = ((Invocable)engine).invokeFunction("circle_area", circleAreaParam);
        System.out.println("Circle Area of radius " + circleAreaParam[0] + " = " + circleAreaResult);
    }

}
