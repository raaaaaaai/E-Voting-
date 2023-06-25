package com.example.voting;

public class Vote {
    private int voteCount;
    private String path;

    public Vote(int voteCount) {
        this.voteCount = voteCount;

    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getPath() {
        return path;
    }
}
