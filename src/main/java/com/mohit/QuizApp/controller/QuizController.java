package com.mohit.QuizApp.controller;

import com.mohit.QuizApp.dto.*;
import com.mohit.QuizApp.entity.*;
import com.mohit.QuizApp.repo.QuestionRepository;
import com.mohit.QuizApp.repo.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.mohit.QuizApp.repo.QuizAttemptRepository;
import com.mohit.QuizApp.repo.UserRepository;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/admin/quizzes")
public class QuizController {
    private final QuizRepository quizRepo;
    private final QuestionRepository questionRepo;
    private final QuizAttemptRepository attemptRepo;
    private final UserRepository userRepo;

    public QuizController(QuizRepository quizRepo, QuestionRepository questionRepo,
                          QuizAttemptRepository attemptRepo, UserRepository userRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
        this.attemptRepo = attemptRepo;
        this.userRepo = userRepo;
    }

    // ✅ Get All Quizzes Without Infinite Recursion
    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        List<Quiz> quizzes = quizRepo.findAll();
        List<QuizDTO> quizDTOs = quizzes.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(quizDTOs);
    }

    // ✅ Convert Quiz entity to QuizDTO
    private QuizDTO convertToDTO(Quiz quiz) {
        List<QuestionDTO> questionDTOs = quiz.getQuestions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new QuizDTO(quiz.getId(), quiz.getTitle(), quiz.getTotalMarks(), quiz.getDuration(), questionDTOs);
    }

    // ✅ Convert Question entity to QuestionDTO
    private QuestionDTO convertToDTO(Question question) {
        List<QuestionOptionDTO> optionDTOs = question.getOptions().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new QuestionDTO(question.getId(), question.getQuestionText(), optionDTOs);
    }

    // ✅ Convert QuestionOption entity to QuestionOptionDTO
    private QuestionOptionDTO convertToDTO(QuestionOption option) {
        return new QuestionOptionDTO(option.getId(), option.getOptionText(), option.isCorrect());
    }

    //create quiz
    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizRepo.save(quiz));
    }

    // ✅ Add Questions to a Quiz
    @PostMapping("/{id}/questions")
    public ResponseEntity<QuestionDTO> addQuestionToQuiz(@PathVariable Long id, @RequestBody Question question) {
        Quiz quiz = quizRepo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        question.setQuiz(quiz); // ✅ Link question to quiz

        // ✅ Ensure all options are correctly linked to the question
        List<QuestionOption> options = question.getOptions();
        if (options != null) {
            for (QuestionOption option : options) {
                option.setQuestion(question);
            }
        }

        // ✅ Save the question
        Question savedQuestion = questionRepo.save(question);

        // ✅ Convert to DTO before returning
        QuestionDTO responseDTO = convertToDTO(savedQuestion);
        return ResponseEntity.ok(responseDTO);
    }


    // ✅  Get Quiz Participants with Status
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<QuizParticipantDTO>> getQuizParticipants(@PathVariable Long id) {
        List<QuizAttempt> attempts = attemptRepo.findByQuizId(id);
        List<QuizParticipantDTO> participants = attempts.stream()
                .map(attempt -> new QuizParticipantDTO(attempt.getUsername(), attempt.isCompleted(), attempt.isCompleted() ? attempt.getScore() : null))
                .collect(Collectors.toList());

        return ResponseEntity.ok(participants);
    }

    // ✅ 5. Get a Specific User's Quiz Response
    @GetMapping("/{id}/response/{userId}")
    public ResponseEntity<QuizResponseDTO> getQuizResponse(@PathVariable Long id, @PathVariable Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Optional<QuizAttempt> attempt = attemptRepo.findByUsernameAndQuizId(user.getUsername(), id);

        if (attempt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        QuizResponseDTO responseDTO = new QuizResponseDTO();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setQuizId(id);
        responseDTO.setScore(attempt.get().getScore());
        responseDTO.setCompleted(attempt.get().isCompleted());

        return ResponseEntity.ok(responseDTO);
    }



}
