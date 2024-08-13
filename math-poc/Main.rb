# Radioactive decay
class Main
    def amount_after_years(q0, t)
        q0 * Math.exp(1.0 / $half_life * Math.log(1.0/2.0) * t)
    end
    
    def years_to_amount(q0, q)
        $half_life * (Math.log(q) - Math.log(q0)) / Math.log(1.0/2.0)
    end
end

$half_life = 5715

main = Main.new
amount_after_years_result = main.amount_after_years(10.0, 1000)
years_to_amount_result = main.years_to_amount(10.0, 1.0)
puts "amount_after_years_result=#{amount_after_years_result}"
puts "years_to_amount_result=#{years_to_amount_result}"