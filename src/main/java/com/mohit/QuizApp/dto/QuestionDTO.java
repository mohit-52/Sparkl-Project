package com.mohit.QuizApp.dto;

import java.util.List;

public class QuestionDTO {
    private Long id;
    private String questionText;
    private List<QuestionOptionDTO> options;

    // ✅ Constructor
    public QuestionDTO(Long id, String questionText, List<QuestionOptionDTO> options) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<QuestionOptionDTO> getOptions() { return options; }
    public void setOptions(List<QuestionOptionDTO> options) { this.options = options; }
}
