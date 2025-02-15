package com.mohit.QuizApp.repo;

import com.mohit.QuizApp.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
    List<QuizAttempt> findByQuizId(Long quizId);  // ✅ Fetch all attempts for a quiz

    List<QuizAttempt> findByUsername(String username);  // ✅ Fetch all attempts by a user

    Optional<QuizAttempt> findByUsernameAndQuizId(String username, Long quizId);  // ✅ Fetch a user's attempt for a specific quiz
}
