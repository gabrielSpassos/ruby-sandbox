package com.gabrielspassos.calculator;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Jsr223GetInterfaceSample {

    public static void main(String[] args) throws ScriptException, FileNotFoundException {
        new Jsr223GetInterfaceSample();
    }

    private Jsr223GetInterfaceSample() throws ScriptException, FileNotFoundException {
        System.out.println("[" + getClass().getName() + "]");
        System.setProperty("org.jruby.embed.localvariable.behavior", "transient");
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("jruby");

        // implemented by a Ruby class
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ruby/calculation.rb").getFile());
        FileReader reader = new FileReader(file);
        Object receiver = engine.eval(reader);
        Calculable c = ((Invocable)engine).getInterface(receiver, Calculable.class);
        List<Long> xyz = c.dimension(20L);
        System.out.format("Dimensions are %d x %d x %d.\n", xyz.get(0), xyz.get(1), xyz.get(2));
        double adjacent = 5.0;
        double opposite = 12.0;
        Double hypotenuse = c.hypotenuse(adjacent, opposite);
        System.out.format("Adjacent, opposite, and hypotenuse are %.2f, %.2f, %.2f.\n",
                adjacent, opposite, hypotenuse);

        // implemented by a top level function
        File otherFile = new File(classLoader.getResource("ruby/fluid_force.rb").getFile());
        FileReader otherReader = new FileReader(otherFile);
        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("@w", 49.4); // weight-densities of ethyl alcohol in pounds per cubic foot
        engine.eval(otherReader, bindings);
        FluidForce f = ((Invocable)engine).getInterface(FluidForce.class);
        double a = 2.0;
        double b = 3.0;
        double depth = 6.0;
        Double force = f.getFluidForce(a, b, depth);
        System.out.format("Ethyl alcohol force to %.2f ft x %.2f ft ellipse in depth of %.2f ft is %.5f lb.\n",
                a, b, depth, force);
    }
}
