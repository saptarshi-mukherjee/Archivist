package com.archivist.reading_platform.Strategies.DateNormalisation;

import java.time.LocalDate;

public interface IndianFormatStrategy {
    public LocalDate normaliseDate(String date_str);
}
