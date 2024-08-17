class Calculation
  include Java::com.gabrielspassos.calculator.Calculable
  def dimension(base)
    x = base + 1
    y = base + 2
    z = base - 1
    return x, y, z
  end
  def hypotenuse(adjacent, opposite)
    Math.hypot(adjacent, opposite)
  end
end
Calculation.new
