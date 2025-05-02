package com.archivist.reading_platform.Strategies.AddToCurrentlyReading;

import com.archivist.reading_platform.Models.CurrentlyReading;
import com.archivist.reading_platform.Repositories.CurrentlyReadingRepository;
import com.archivist.reading_platform.Repositories.FormatRepository;

import java.util.List;

public interface CurrentlyReadingStrategy {
    public List<CurrentlyReading> addToCurrentlyReading(FormatRepository format_repo,
                                                        CurrentlyReadingRepository current_read_repo,
                                                        String isbn);
}
