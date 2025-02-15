package com.mohit.QuizApp.controller;

import com.mohit.QuizApp.dto.QuizAttemptDTO;
import com.mohit.QuizApp.entity.Question;
import com.mohit.QuizApp.entity.QuestionOption;
import com.mohit.QuizApp.entity.Quiz;
import com.mohit.QuizApp.entity.QuizAttempt;
import com.mohit.QuizApp.repo.QuizAttemptRepository;
import com.mohit.QuizApp.repo.QuizRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/quizzes")
public class QuizAttemptController {
    private final QuizRepository quizRepo;
    private final QuizAttemptRepository attemptRepo;

    public QuizAttemptController(QuizRepository quizRepo, QuizAttemptRepository attemptRepo) {
        this.quizRepo = quizRepo;
        this.attemptRepo = attemptRepo;
    }

    // ✅ 1️⃣ Get Available Quizzes
    @GetMapping
    public ResponseEntity<List<Quiz>> getQuizzes() {
        List<Quiz> quizzes = quizRepo.findAll();
        return ResponseEntity.ok(quizzes);
    }

    // ✅ 2️⃣ Start a Quiz
    @PostMapping("/{quizId}/start")
    public ResponseEntity<QuizAttemptDTO> startQuiz(@PathVariable Long quizId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        // ✅ Check if user already started the quiz
        Optional<QuizAttempt> existingAttempt = attemptRepo.findByUsernameAndQuizId(username, quizId);
        if (existingAttempt.isPresent()) {
            return ResponseEntity.badRequest().body(null); // Quiz already started
        }

        QuizAttempt attempt = new QuizAttempt(null, username, quiz, 0, false);
        QuizAttempt savedAttempt = attemptRepo.save(attempt);

        // ✅ Convert to DTO for clean response
        QuizAttemptDTO attemptDTO = new QuizAttemptDTO(savedAttempt.getId(), username, quiz.getTitle(), 0, false);
        return ResponseEntity.ok(attemptDTO);
    }

    // ✅ 3️⃣ Submit Quiz
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizAttemptDTO> submitQuiz(@PathVariable Long quizId, @RequestBody List<String> answers) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        QuizAttempt attempt = attemptRepo.findByUsernameAndQuizId(username, quizId)
                .orElseThrow(() -> new RuntimeException("Quiz attempt not found"));

        if (attempt.isCompleted()) {
            return ResponseEntity.badRequest().body(null); // Already submitted
        }

        int score = calculateScore(quizId, answers);
        attempt.setScore(score);
        attempt.setCompleted(true);
        QuizAttempt savedAttempt = attemptRepo.save(attempt);

        // ✅ Convert to DTO for clean response
        QuizAttemptDTO attemptDTO = new QuizAttemptDTO(savedAttempt.getId(), username, attempt.getQuiz().getTitle(), savedAttempt.getScore(), true);
        return ResponseEntity.ok(attemptDTO);
    }

    // ✅ 4️⃣ Score Calculation
    private int calculateScore(Long quizId, List<String> answers) {
        List<Question> questions = quizRepo.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found")).getQuestions();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getOptions().stream()
                    .filter(QuestionOption::isCorrect)
                    .map(QuestionOption::getOptionText)
                    .findFirst()
                    .orElse("");

            if (i < answers.size() && correctAnswer.equalsIgnoreCase(answers.get(i))) {
                score += 10; // Each correct answer = 10 points
            }
        }
        return score;
    }

    // ✅ 5️⃣ Get Quiz Response of the Logged-in User
    @GetMapping("/{quizId}/response")
    public ResponseEntity<QuizAttemptDTO> getQuizResponse(@PathVariable Long quizId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        QuizAttempt attempt = attemptRepo.findByUsernameAndQuizId(username, quizId)
                .orElseThrow(() -> new RuntimeException("Quiz attempt not found"));

        // ✅ Convert to DTO for clean response
        QuizAttemptDTO responseDTO = new QuizAttemptDTO(attempt.getId(), username, attempt.getQuiz().getTitle(), attempt.getScore(), attempt.isCompleted());
        return ResponseEntity.ok(responseDTO);
    }
}
