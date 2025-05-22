package com.archivist.reading_platform.Services.ReaderService;

import com.archivist.reading_platform.DTO.ResponseDTO.*;
import com.archivist.reading_platform.Factories.CurrentlyReadingStrategyFactory;
import com.archivist.reading_platform.Models.*;
import com.archivist.reading_platform.Repositories.*;
import com.archivist.reading_platform.Services.NotificationService.NotificationService;
import com.archivist.reading_platform.Strategies.AddToCurrentlyReading.CurrentlyReadingStrategy;
import com.archivist.reading_platform.Strategies.AverageRatingCalculation.AverageCalculationStrategy;
import com.archivist.reading_platform.Strategies.AverageRatingCalculation.SimpleAverageCalculationStrategy;
import com.archivist.reading_platform.Strategies.DateNormalisation.BasicStrategy;
import com.archivist.reading_platform.Strategies.DateNormalisation.IndianFormatStrategy;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
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
    @Autowired
    ReviewRepository review_repo;
    @Autowired
    CommentRepository comment_repo;
    @Autowired
    ToReadRepository tbr_repo;
    @Autowired
    FormatRepository format_repo;
    @Autowired
    CurrentlyReadingRepository current_read_repo;
    @Autowired
    ReadRepository read_repo;
    @Autowired
    private RedisTemplate<String,Object> redis_template;
    @Autowired
    private NotificationService notification_service;


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

    @Override
    public AllBookReviewResponseDto reviewBook(String reader_name, String book_name, String review_text) {
        Book book=book_repo.fetchByBookName(book_name);
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        Review review=new Review();
        review.setBook(book);
        review.setReader(reader);
        review.setReview_text(review_text);
        review=review_repo.save(review);
        book.getReviews().add(review);
        book=book_repo.save(book);
        reader.getReviews().add(review);
        reader=reader_repo.save(reader);
        List<ReviewResponseDto> review_response_list=new ArrayList<>();
        for(Review r : book.getReviews()) {
            ReviewResponseDto review_response=new ReviewResponseDto();
            review_response.setReader_name(r.getReader().getName());
            review_response.setReview_time(r.getReview_time());
            review_response.setReview_text(r.getReview_text());
            review_response.setLike_count(r.getLike_count());
            review_response_list.add(review_response);
        }
        AllBookReviewResponseDto response=new AllBookReviewResponseDto();
        response.setBook_name(book.getBook_name());
        response.setReviews(review_response_list);
        List<Reader> followers=new ArrayList<>(reader.getFollowers());
        notification_service.postNotification(reader.getId(),review.getId(),null,followers);
        return response;
    }

    @Override
    public Review likeReview(long review_id) {
        Review review=review_repo.fetchByReviewId(review_id);
        review.setLike_count(review.getLike_count()+1);
        review=review_repo.save(review);
        return review;
    }

    @Override
    public AllCommentsResponseDto commentOnReview(long review_id, String reader_name, String comment_text) {
        Review review=review_repo.fetchByReviewId(review_id);
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        Comment comment=new Comment();
        comment.setReader(reader);
        comment.setReview(review);
        comment.setComment_text(comment_text);
        comment=comment_repo.save(comment);
        review.getComments().add(comment);
        review=review_repo.save(review);
        reader.getComments().add(comment);
        reader=reader_repo.save(reader);
        comment_repo.flush();
        List<CommentResponseDto> comment_responses=new ArrayList<>();
        for(Comment com : comment.getReview().getComments()) {
            CommentResponseDto comment_response=new CommentResponseDto();
            comment_response.setCommenter_name(com.getReader().getName());
            comment_response.setComment_time(com.getComment_time());
            comment_response.setComment_text(com.getComment_text());
            comment_response.setLike_count(com.getLike_count());
            comment_responses.add(comment_response);
        }
        AllCommentsResponseDto response=new AllCommentsResponseDto();
        response.setReviewer_name(comment.getReview().getReader().getName());
        response.setReview_time(comment.getReview().getReview_time());
        response.setReview_text(comment.getReview().getReview_text());
        response.setLike_count(comment.getReview().getLike_count());
        response.setComments_list(comment_responses);
        List<Reader> followers=new ArrayList<>(reader.getFollowers());
        notification_service.postNotification(reader.getId(),review.getId(),comment.getId(),followers);
        return response;
    }

    @Override
    public Comment likeComment(long comment_id) {
        Comment comment=comment_repo.fetchByCommentId(comment_id);
        comment.setLike_count(comment.getLike_count()+1);
        comment=comment_repo.save(comment);
        return comment;
    }

    @Override
    public Reader addToTBR(String reader_name, String book_name) {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        Book book=book_repo.fetchByBookName(book_name);
        long book_id=book.getId();
        ToRead tbr=tbr_repo.fetchByBookId(book_id);
        if(tbr==null) {
            tbr=new ToRead();
            tbr.setBook(book);
            tbr.getReaders().add(reader);
        }
        else {
            tbr.getReaders().add(reader);
        }
        tbr=tbr_repo.save(tbr);
        reader.getTbr().add(tbr);
        reader=reader_repo.save(reader);
        return reader;
    }

    @Override
    public List<ToRead> getTbr(String reader_name) {
        return reader_repo.fetchByReaderName(reader_name).getTbr();
    }

    @Override
    public List<CurrentlyReading> addToCurrentlyReading(String reader_name, String book_name, String isbn) {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        Book book=book_repo.fetchByBookName(book_name);
        CurrentlyReadingStrategy strategy= CurrentlyReadingStrategyFactory.getStrategy(reader_repo,tbr_repo,reader,book);
        List<CurrentlyReading> current_reads=strategy.addToCurrentlyReading(format_repo,current_read_repo,isbn);
        return current_reads;
    }

    @Override
    public List<CurrentlyReading> getCurrentlyReading(String reader_name) {
        return reader_repo.fetchByReaderName(reader_name).getCurrent_reads();
    }

    @Override
    public List<CurrentlyReading> updateProgress(String book_name, String reader_name, int page_no) throws Exception {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        List<CurrentlyReading> current_reads=reader.getCurrent_reads();
        CurrentlyReading temp=null;
        for(CurrentlyReading current_read : current_reads) {
            if(current_read.getBook().getBook_name().equals(book_name)) {
                temp=current_read;
                break;
            }
        }
        if(temp==null)
            throw new Exception("add to currently reading first");
        int total_pages=temp.getFormat().getPage_count();
        if(page_no>total_pages)
            throw new Exception("exceeds total page count");
        double progress=((double) page_no/total_pages)*100;
        progress=new BigDecimal(progress).setScale(2, RoundingMode.HALF_UP).doubleValue();
        temp.setProgress(progress);
        current_read_repo.save(temp);
        reader=reader_repo.save(reader);
        return reader.getCurrent_reads();
    }

    @Override
    public List<Read> addToRead(String reader_name, String book_name, String isbn) throws Exception {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        List<CurrentlyReading> current_reads=reader.getCurrent_reads();
        int index=-1;
        for(int i=0;i<current_reads.size();i++) {
            if(current_reads.get(i).getBook().getBook_name().equals(book_name) &&
                current_reads.get(i).getFormat().getIsbn().equals(isbn)) {
                index=i;
                break;
            }
        }
        if(index==-1)
            throw new Exception("Read the book first");
        Read read=new Read();
        read.setBook(current_reads.get(index).getBook());
        read.setReader(current_reads.get(index).getReader());
        read.setFormat(current_reads.get(index).getFormat());
        read.setStart_date(current_reads.get(index).getStart_date());
        read.setEnd_date(LocalDate.now());
        read=read_repo.save(read);
        current_read_repo.delete(current_reads.get(index));
        current_reads.remove(index);
        reader.setCurrent_reads(current_reads);
        Format format=read.getFormat();
        format.getReads().add(read);
        format_repo.save(format);
        reader.getReads().add(read);
        reader=reader_repo.save(reader);
        return reader.getReads();
    }

    @Override
    public List<Read> getReads(String reader_name) {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        return reader.getReads();
    }

    @Override
    @Scheduled(cron = "0 */2 * * * *")
    public void testScheduled() {
        System.out.println("PRINTING!!!!!");
    }

    @Override
    public FollowerResponseDto followReader(String follower_name, String username) {
        Reader user=reader_repo.fetchByReaderName(username);
        Reader follower=reader_repo.fetchByReaderName(follower_name);
        user.getFollowers().add(follower);
        follower.getFollowing().add(user);
        user=reader_repo.save(user);
        follower=reader_repo.save(follower);
        FollowerResponseDto response=new FollowerResponseDto();
        response.setUsername(user.getName());
        for(Reader f : user.getFollowers())
            response.getFollowers().add(f.getName());
        notification_service.postNotification(follower.getId(),null,null,List.of(user));
        return response;
    }

    @Override
    public FollowerResponseDto getFollowers(String reader_name) {
        FollowerResponseDto cached_response=(FollowerResponseDto) redis_template.opsForValue().get("FOLLOWERS::"+reader_name);
        if(cached_response!=null)
            return cached_response;
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        FollowerResponseDto response=new FollowerResponseDto();
        for(Reader follower : reader.getFollowers())
            response.getFollowers().add(follower.getName());
        response.setUsername(reader.getName());
        redis_template.opsForValue().set("FOLLOWERS::"+reader_name,response, Duration.ofMinutes(15));
        return response;
    }
}
