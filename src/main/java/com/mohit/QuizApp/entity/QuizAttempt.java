package com.mohit.QuizApp.entity;

import jakarta.persistence.*;

@Entity
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private int score;
    private boolean completed;

    // ✅ Manually defined No-Args Constructor
    public QuizAttempt() {
    }

    // ✅ Manually defined All-Args Constructor
    public QuizAttempt(Long id, String username, Quiz quiz, int score, boolean completed) {
        this.id = id;
        this.username = username;
        this.quiz = quiz;
        this.score = score;
        this.completed = completed;
    }

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
