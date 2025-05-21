package com.archivist.reading_platform.DTO.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class FollowerResponseDto {
    private String username;
    private List<String> followers;


    public FollowerResponseDto() {
        followers=new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}
