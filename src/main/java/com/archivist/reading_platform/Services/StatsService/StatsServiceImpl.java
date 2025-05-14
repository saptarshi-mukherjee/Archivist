package com.archivist.reading_platform.Services.StatsService;

import com.archivist.reading_platform.Models.Read;
import com.archivist.reading_platform.Models.Reader;
import com.archivist.reading_platform.Projections.AuthorProjection;
import com.archivist.reading_platform.Projections.GenreProjection;
import com.archivist.reading_platform.Repositories.ReadRepository;
import com.archivist.reading_platform.Repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;


@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private ReadRepository read_repo;
    @Autowired
    private ReaderRepository reader_repo;


    @Override
    public Long getReaderId(String reader_name) {
        Reader reader=reader_repo.fetchByReaderName(reader_name);
        return reader.getId();
    }

    @Override
    public Long getAverageBookLength(long reader_id) {
        List<Read> reads=read_repo.fetchByReaderId(reader_id);
        int sum=0;
        for(Read read : reads)
            sum+=read.getFormat().getPage_count();
        double avg=(double) sum/reads.size();
        return Math.round(avg);
    }

    @Override
    public Long getTimeToFinish(long reader_id) {
        List<Read> reads=read_repo.fetchByReaderId(reader_id);
        long sum=0;
        for(Read read : reads)
            sum+= Period.between(read.getStart_date(),read.getEnd_date()).getDays();
        double avg=(double)sum/reads.size();
        return Math.round(avg);
    }

    @Override
    public List<GenreProjection> getFaveGenres(long reader_id) {
        return read_repo.fetchFaveGenres(reader_id);
    }

    @Override
    public List<AuthorProjection> getFaveAuthors(long reader_id) {
        return read_repo.fetchFaveAuthors(reader_id);
    }
}
