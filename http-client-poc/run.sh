#!/bin/bash

echo "Running standard ruby"
ruby main.rb

echo "Running JRuby"
jruby main.rb

echo "Running Java via JRuby"
javac Client.java
jruby java_main.rb
