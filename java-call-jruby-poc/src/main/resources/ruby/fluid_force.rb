def get_fluid_force(x, y, depth)
  area = Math::PI * x * y # ellipse
  return @w * area * depth
end
