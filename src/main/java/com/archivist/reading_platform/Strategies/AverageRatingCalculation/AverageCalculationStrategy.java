package com.archivist.reading_platform.Strategies.AverageRatingCalculation;

import com.archivist.reading_platform.Models.Rating;

import java.util.List;

public interface AverageCalculationStrategy {
    public Double calculateAverage(List<Rating> ratings);
}
