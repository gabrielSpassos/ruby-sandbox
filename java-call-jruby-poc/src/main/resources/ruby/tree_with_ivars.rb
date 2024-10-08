class Tree
  attr_reader :name, :shape, :foliage, :flower
  def initialize(flower)
    @flower = flower
  end
  def to_s
    "#{name} is a #{shape} shaped, #{foliage} tree, and blooms #{flower.color} flowers in #{flower.bloomtime}."
  end
  def update
    flower.color = @color
    flower.bloomtime = @bloomtime
  end
end

class Flower
  attr_accessor :color, :bloomtime
  def initialize
  end
end

t = Tree.new(Flower.new)
t.to_s()
