package com.gabrielspassos.redbridge;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class Jsr223SimpleEvalSample {

    public static void main(String[] args) throws ScriptException {
        new Jsr223SimpleEvalSample();
    }

    private Jsr223SimpleEvalSample() throws ScriptException {
        System.out.println("[" + getClass().getName() + "]");
        defaultBehavior();
        transientBehavior();
    }

    private void defaultBehavior() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        Bindings bindings = new SimpleBindings();
        bindings.put("message", "My message from ruby script");
        String script = "puts message";
        engine.eval(script, bindings);
    }

    private void transientBehavior() throws ScriptException {
        System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        Bindings bindings = new SimpleBindings();
        bindings.put("msg", "changed to transient behavior");
        bindings.put("message", "local variable");
        bindings.put("@message", "instance variable");
        bindings.put("$message", "global variable");
        bindings.put("MESSAGE", "constant");
        String script =
                """
                puts msg
                puts message
                puts @message
                puts $message
                puts MESSAGE""";
        engine.eval(script, bindings);
    }

}
