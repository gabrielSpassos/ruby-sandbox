package com.gabrielspassos.redbridge;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Set;

public class Jsr223PersistentLocalVariableSample {

    public static void main(String[] args) throws ScriptException {
        new Jsr223PersistentLocalVariableSample();
    }

    private Jsr223PersistentLocalVariableSample() throws ScriptException {
        System.out.println("[" + getClass().getName() + "]");
        System.setProperty("org.jruby.embed.localvariable.behavior", "persistent");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        Object ret = engine.eval("x=144");
        Object ret2 = engine.eval("Math.sqrt x");
        System.out.println("Square root of " + ret + " is " + ret2);

        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        String message = "a red big bridge in San Francisco.";
        bindings.put("message", message);
        ret = engine.eval("ret=\"You can see #{message}\"", bindings);
        System.out.println(ret);

        bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        String correction = "elsewhere in the world";
        bindings.put("correction", correction);
        ret = engine.eval("ret = ret.gsub(/in San Francisco/, correction)", bindings);
        System.out.println(ret);

        Set<String> keys = bindings.keySet();
        for (String key : keys) {
            System.out.println(key + ", " + bindings.get(key));
        }
    }
}
