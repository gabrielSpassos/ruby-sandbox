package com.gabrielspassos.calculator;

import java.util.List;

public interface Calculable {

    List<Long> dimension(long base);

    Double hypotenuse(double adjacent, double opposite);

}
