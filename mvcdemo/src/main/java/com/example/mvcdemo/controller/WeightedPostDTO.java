package com.example.mvcdemo.controller;

public class WeightedPostDTO {
    private Long id;
    private double weightedScore;

    public WeightedPostDTO(Long id, double weightedScore) {
        this.id = id;
        this.weightedScore = weightedScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeightedScore() {
        return weightedScore;
    }

    public void setWeightedScore(double weightedScore) {
        this.weightedScore = weightedScore;
    }




}
