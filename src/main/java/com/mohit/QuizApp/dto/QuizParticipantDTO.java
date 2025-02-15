package com.mohit.QuizApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class QuizParticipantDTO {
    private String username;
    private boolean completed;
    private Integer score; // Nullable (null if quiz is not completed)

    public QuizParticipantDTO() {
    }

    public QuizParticipantDTO(String username, boolean completed, Integer score) {
        this.username = username;
        this.completed = completed;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
