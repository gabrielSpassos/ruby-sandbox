package com.gabrielspassos.redbridge;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Jsr223ClassMethodCallSample {

    private final static String filename = "ruby/tree_with_ivars.rb";

    public static void main(String[] args) throws ScriptException, NoSuchMethodException, FileNotFoundException {
        new Jsr223ClassMethodCallSample();
    }

    //todo: verify why is not working as expected
    private Jsr223ClassMethodCallSample() throws ScriptException, NoSuchMethodException, FileNotFoundException {
        System.out.println("[" + getClass().getName() + "]");
        System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        Reader reader = new FileReader(file);
        Object receiver = engine.eval(reader);
        engine.put("@name", "cherry blossom");
        engine.put("@shape", "oval");
        engine.put("@foliage", "deciduous");
        engine.put("@color", "pink");
        engine.put("@bloomtime", "March - April");
        ((Invocable)engine).invokeMethod(receiver, "update");
        System.out.println(((Invocable)engine).invokeMethod(receiver, "to_s"));

        engine.put("@name", "cedar");
        engine.put("@shape", "pyramidal");
        engine.put("@foliage", "evergreen");
        engine.put("@color", "nondescript");
        engine.put("@bloomtime", "April - May");
        ((Invocable)engine).invokeMethod(receiver, "update");
        System.out.println(((Invocable)engine).invokeMethod(receiver, "to_s"));
    }

}
