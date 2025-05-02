package com.archivist.reading_platform.Strategies.AddToCurrentlyReading;

import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.CurrentlyReadingRepository;
import com.archivist.reading_platform.Repositories.FormatRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import com.archivist.reading_platform.Repositories.ToReadRepository;

import java.util.List;

public class ShiftFromTbrStrategy implements CurrentlyReadingStrategy {


    ToReadRepository tbr_repo;
    ReaderRepository reader_repo;
    Reader reader;
    ToRead tbr_entry;


    public ShiftFromTbrStrategy(ToReadRepository tbr_repo, ReaderRepository reader_repo, Reader reader, ToRead tbr_entry) {
        this.tbr_repo = tbr_repo;
        this.reader_repo = reader_repo;
        this.reader = reader;
        this.tbr_entry = tbr_entry;
    }

    @Override
    public List<CurrentlyReading> addToCurrentlyReading(FormatRepository format_repo, CurrentlyReadingRepository current_read_repo,
                                                        String isbn) {
        Book book=tbr_entry.getBook();
        Format format=format_repo.fetchByIsbn(isbn);
        if(tbr_entry.getReaders().contains(reader))
            tbr_entry.getReaders().remove(reader);
        if(reader.getTbr().contains(tbr_entry))
            reader.getTbr().remove(tbr_entry);
        reader=reader_repo.save(reader);
        tbr_entry=tbr_repo.save(tbr_entry);
        CurrentlyReading current_read=new CurrentlyReading();
        current_read.setBook(book);
        current_read.setFormat(format);
        current_read.setReader(reader);
        current_read=current_read_repo.save(current_read);
        reader.getCurrent_reads().add(current_read);
        reader=reader_repo.save(reader);
        format.getCurrent_reads().add(current_read);
        format_repo.save(format);
        return reader.getCurrent_reads();
    }
}
