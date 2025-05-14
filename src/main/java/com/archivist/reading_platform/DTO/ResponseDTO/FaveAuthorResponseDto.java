package com.archivist.reading_platform.DTO.ResponseDTO;

public class FaveAuthorResponseDto {
    private String author;
    private long count;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
