package com.archivist.reading_platform.DTO.ResponseDTO;

public class FaveGenreResponseDto {
    private String genre;
    private long count;


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
