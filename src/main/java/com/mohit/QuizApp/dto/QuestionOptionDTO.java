package com.mohit.QuizApp.dto;

public class QuestionOptionDTO {
    private Long id;
    private String optionText;
    private boolean isCorrect;

    // ✅ Constructor
    public QuestionOptionDTO(Long id, String optionText, boolean isCorrect) {
        this.id = id;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }

    public boolean isCorrect() { return isCorrect; }
    public void setCorrect(boolean correct) { isCorrect = correct; }
}
