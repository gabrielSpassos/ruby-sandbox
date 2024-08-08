#!/bin/bash

echo "Compile CallMe.java"
javac com/example/CallMe.java

echo "Compile ISpeaker.java"
javac com/example/ISpeaker.java

echo "Run Ruby Script with JRuby"
jruby call_java.rb
