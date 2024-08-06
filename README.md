# Ruby Sandbox

## JRuby Sandbox

### Install

1. Install [Ruby package manager](https://rvm.io/)
```bash
\curl -sSL https://get.rvm.io | bash -s stable
```

2. Check RVM version
```bash
rvm --version
```

3. Install [JRuby](https://www.jruby.org/)
```bash
rvm install jruby
```

4. Check JRuby version
```bash
jruby -v
```

5. Hello World via terminal
```bash
jruby -e "puts 'Hello World'"
```

6. Execute source code
```bash
jruby Script.rb
```