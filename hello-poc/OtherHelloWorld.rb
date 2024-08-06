class HelloWorldSayer
    def hello_world
        yield "Hello"
        yield "World"
        yield "from Ruby"
    end
end

sayer = HelloWorldSayer.new
sayer.hello_world do |it|
    puts it.swapcase
end
