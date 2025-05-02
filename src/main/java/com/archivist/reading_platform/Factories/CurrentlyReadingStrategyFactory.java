package com.archivist.reading_platform.Factories;

import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Models.ToRead;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Repositories.ToReadRepository;
import com.archivist.reading_platform.Strategies.AddToCurrentlyReading.CurrentlyReadingStrategy;
import com.archivist.reading_platform.Strategies.AddToCurrentlyReading.DirectAdditionStrategy;
import com.archivist.reading_platform.Strategies.AddToCurrentlyReading.ShiftFromTbrStrategy;

public class CurrentlyReadingStrategyFactory {

    public static CurrentlyReadingStrategy getStrategy(ReaderRepository reader_repo, ToReadRepository tbr_repo,
                                                       Reader reader, Book book) {

        ToRead tbr_entry=tbr_repo.fetchByBookId(book.getId());
        if(tbr_entry!=null && reader.getTbr().contains(tbr_entry))
            return new ShiftFromTbrStrategy(tbr_repo,reader_repo,reader,tbr_entry);
        else
            return new DirectAdditionStrategy(reader_repo,reader);
    }
}
