package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.*;
import com.archivist.reading_platform.Strategies.AverageRatingCalculation.AverageCalculationStrategy;
import com.archivist.reading_platform.Strategies.AverageRatingCalculation.SimpleAverageCalculationStrategy;
import com.archivist.reading_platform.Strategies.DateNormalisation.BasicStrategy;
import com.archivist.reading_platform.Strategies.DateNormalisation.IndianFormatStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ReaderServiceImpl implements ReaderService {


    @Autowired
    ReaderRepository reader_repo;
    @Autowired
    BookRepository book_repo;
    @Autowired
    RatingRepository rating_repo;
    @Autowired
    SeriesRepository series_repo;
    @Autowired
    AuthorRepository author_repo;


    @Override
    public Reader registerReader(String name, String email, String address, Long phone, String birthday) {
        IndianFormatStrategy strategy=new BasicStrategy();
        LocalDate birth_date=strategy.normaliseDate(birthday);
        Reader reader=new Reader();
        reader.setName(name);
        reader.setEmail(email);
        reader.setAddress(address);
        reader.setPhone(phone);
        reader.setBirthday(birth_date);
        reader=reader_repo.save(reader);
        return reader;
    }

    @Override
    public Book rateBook(String reader_name, String book_name, Double rating_value) {
        AverageCalculationStrategy strategy=new SimpleAverageCalculationStrategy();
        Book book=book_repo.fetchByBookName(book_name);
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        Series series=series_repo.fetchBySeriesName(book.getSeries().getSeries_name());
        List<Author> authors=new ArrayList<>();
        for(Author author : book.getAuthors()) {
            authors.add(author_repo.fetchByAuthorName(author.getAuthor_name()));
        }
        Rating rating=new Rating();
        rating.setBook(book);
        rating.setReader(reader);
        rating.setSeries(book.getSeries());
        rating.setRating_value(rating_value);
        rating.getAuthors().addAll(authors);
        rating=rating_repo.save(rating);
        book.getRatings().add(rating);
        book.setAvg_rating(strategy.calculateAverage(book.getRatings()));
        book=book_repo.save(book);
        reader.getRatings().add(rating);
        reader.setAvg_rating(strategy.calculateAverage(reader.getRatings()));
        reader_repo.save(reader);
        series.getRatings().add(rating);
        series.setAvg_rating(strategy.calculateAverage(series.getRatings()));
        series_repo.save(series);
        for(Author author : authors) {
            author.getRatings().add(rating);
            author.setAvg_rating(strategy.calculateAverage(author.getRatings()));
            author_repo.save(author);
        }
        return book;
    }
}
