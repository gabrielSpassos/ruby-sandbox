require 'net/http'
require 'uri'

class Client 
    def run()
        url = URI.parse('http://localhost:8080/api/people')
        http = Net::HTTP.new(url.host, url.port)
        request = Net::HTTP::Get.new(url)

        start_time = Time.now
        response = http.request(request)
        end_time = Time.now
        time_taken = end_time - start_time

        status_code = response.code
        headers = response.each_header.to_h
        body = response.body

        puts "HTTP Response Status #{status_code}"
        puts "HTTP Response Body #{body}"

        puts "Start #{start_time}"
        puts "End #{end_time}"
        puts "Final #{time_taken}"
        time_taken
    end
end

client = Client.new

threads = []
results = []
100.times do
  threads << Thread.new { Thread.current[:output] = client.run }
end
threads.each do |t|
  t.join
  puts t[:output]
  results.append(t[:output])
end

min = results.min()
max = results.max()

puts "Result #{results}"
puts "Min #{min}"
puts "Max #{max}"
