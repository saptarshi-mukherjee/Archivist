package com.archivist.reading_platform.Services.BookService;

import com.archivist.reading_platform.DTO.ResponseDTO.SmallBookResponseDto;
import com.archivist.reading_platform.Models.Book;
import com.archivist.reading_platform.Models.BookEntry;

import java.util.List;

public interface BookService {
    public Book addBook(String book_name, List<String> author_list, String isbn, Integer page_count, String type, String series_name, List<String> genre_list,
                        Double book_number);
    public List<Book> generalSearch(String prefix);
    public List<BookEntry> searchBooksInSeries(String series_name);
    public SmallBookResponseDto getBookByName(String book_name);
}
