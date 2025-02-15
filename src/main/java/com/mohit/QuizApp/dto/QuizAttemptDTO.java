package com.mohit.QuizApp.dto;

public class QuizAttemptDTO {
    private Long id;
    private String username;
    private String quizTitle;
    private int score;
    private boolean completed;

    // ✅ Constructor
    public QuizAttemptDTO(Long id, String username, String quizTitle, int score, boolean completed) {
        this.id = id;
        this.username = username;
        this.quizTitle = quizTitle;
        this.score = score;
        this.completed = completed;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getQuizTitle() { return quizTitle; }
    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
