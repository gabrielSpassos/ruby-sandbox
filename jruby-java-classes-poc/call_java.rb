require "java"

java_import "java.util.TreeSet"
java_import "com.example.CallMe"
java_import "com.example.ISpeaker"

class CallJava
  include ISpeaker
  def initialize
    super
    @count = 0
  end

  def say(msg)
    puts "Ruby saying #{msg}"
  end
  
  def addOne(from)
      @count += 1
      puts "Now got #@count from #{from}"
  end
end

puts "Hello from ruby"
set = TreeSet.new
set.add "foo"
set.add "bar"
set.add "foo"
set.add "baz"
set.each { |v| puts "value: #{v}" }

cm = CallMe.new
cm.hello

rubyCM = CallMe.new("Ruby Caller")
rubyCM.hello

cj = CallJava.new
cj.say("hello with java interface")
cj.addOne("ruby script")
cj.addOne("ruby script")
