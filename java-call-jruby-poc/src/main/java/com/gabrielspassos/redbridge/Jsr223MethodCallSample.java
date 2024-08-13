package com.gabrielspassos.redbridge;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Jsr223MethodCallSample {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        new Jsr223MethodCallSample();
    }

    private Jsr223MethodCallSample() throws ScriptException, NoSuchMethodException {
        System.out.println("[" + getClass().getName() + "]");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");
        String script =
                "# Radioactive decay\n" +
                "def amount_after_years(q0, t)\n" +
                "q0 * Math.exp(1.0 / $half_life * Math.log(1.0/2.0) * t)\n" +
                "end\n" +
                "def years_to_amount(q0, q)\n" +
                "$half_life * (Math.log(q) - Math.log(q0)) / Math.log(1.0/2.0)\n" +
                "end";
        Object receiver = engine.eval(script);

        engine.put("$half_life", 5715); // Carbon
        String method = "amount_after_years"; // calculates the amount left after given years
        Object[] args = new Object[2];
        args[0] = 10.0;    // initial amount is 10.0g
        args[1] = 1000;    // suppose 1000 years have passed
        Object result = ((Invocable)engine).invokeFunction(method, args);
        System.out.println(args[0] + "g Carbon to decay to " + result + "g in " + args[1] + " years");

        method = "years_to_amount"; // calculates the years to decay to a given amount
        args[0] = 10.0;    // initial amount is 10.0g
        args[1] = 1.0;     // suppose 1.0g is still there
        result = ((Invocable)engine).invokeFunction(method, args);
        System.out.println(args[0] + "g Carbon to decay to " + args[1] + "g in " + result + " years");

    }
}
