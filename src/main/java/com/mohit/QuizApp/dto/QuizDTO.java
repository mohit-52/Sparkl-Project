package com.mohit.QuizApp.dto;

import java.util.List;

public class QuizDTO {
    private Long id;
    private String title;
    private int totalMarks;
    private int duration;
    private List<QuestionDTO> questions;


    public QuizDTO() {
    }
    // ✅ Constructor
    public QuizDTO(Long id, String title, int totalMarks, int duration, List<QuestionDTO> questions) {
        this.id = id;
        this.title = title;
        this.totalMarks = totalMarks;
        this.duration = duration;
        this.questions = questions;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getTotalMarks() { return totalMarks; }
    public void setTotalMarks(int totalMarks) { this.totalMarks = totalMarks; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public List<QuestionDTO> getQuestions() { return questions; }
    public void setQuestions(List<QuestionDTO> questions) { this.questions = questions; }
}
