package com.archivist.reading_platform.Strategies.AverageRatingCalculation;

import com.archivist.reading_platform.Models.Rating;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class SimpleAverageCalculationStrategy implements AverageCalculationStrategy {


    @Override
    public Double calculateAverage(List<Rating> ratings) {
        int sum=0;
        double avg=-1.0;
        for(Rating rating : ratings) {
            sum+=rating.getRating_value();
        }
        avg=(double)sum/ ratings.size();
        BigDecimal rounded_avg=new BigDecimal(avg).setScale(2, RoundingMode.HALF_UP);
        return rounded_avg.doubleValue();
    }
}
