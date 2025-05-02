package com.archivist.reading_platform.Strategies.AddToCurrentlyReading;

import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.CurrentlyReading;
import com.archivist.reading_platform.Models.Format;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Repositories.CurrentlyReadingRepository;
import com.archivist.reading_platform.Repositories.FormatRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;

import java.util.List;

public class DirectAdditionStrategy implements CurrentlyReadingStrategy {


    ReaderRepository reader_repo;
    Reader reader;


    public DirectAdditionStrategy(ReaderRepository reader_repo, Reader reader) {
        this.reader_repo = reader_repo;
        this.reader = reader;
    }

    @Override
    public List<CurrentlyReading> addToCurrentlyReading(FormatRepository format_repo, CurrentlyReadingRepository current_read_repo, String isbn) {
        Format format=format_repo.fetchByIsbn(isbn);
        Book book=format.getBook();
        CurrentlyReading current_read=new CurrentlyReading();
        current_read.setReader(reader);
        current_read.setBook(book);
        current_read.setFormat(format);
        current_read=current_read_repo.save(current_read);
        reader.getCurrent_reads().add(current_read);
        reader=reader_repo.save(reader);
        format.getCurrent_reads().add(current_read);
        format_repo.save(format);
        return reader.getCurrent_reads();
    }
}
