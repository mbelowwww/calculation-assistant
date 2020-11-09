package service;

import java.math.BigDecimal;

public class CalculationService {
    public final static BigDecimal FOOT_RATION = BigDecimal.valueOf(0.3048);

    public static BigDecimal feetToMeters(BigDecimal feet){
        return feet.multiply(FOOT_RATION);
    }

    public static BigDecimal getTotalHeight(BigDecimal height, BigDecimal airfieldHeight, boolean isPlus){
        if (isPlus){
            return height.add(airfieldHeight);
        } else {
            return height.subtract(airfieldHeight);
        }
    }
}
