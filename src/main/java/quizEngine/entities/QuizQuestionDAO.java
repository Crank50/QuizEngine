package quizEngine.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface QuizQuestionDAO extends CrudRepository<QuizQuestion, Long> {

    // category!=ALL && questionType==ALL && difficulty==ALL
    List<QuizQuestion> findByCategory(QuizQuestion.Category category);

    // category!=ALL && questionType!=ALL && difficulty==ALL
    List<QuizQuestion> findByCategoryAndQuestionType(QuizQuestion.Category category,QuizQuestion.QuestionType questionType);


    // category==ALL && questionType!=ALL && difficulty==ALL
    List<QuizQuestion> findByQuestionType(QuizQuestion.QuestionType questionType);

    // category==ALL && questionType!=ALL && difficulty!=ALL
    List<QuizQuestion> findByQuestionTypeAndDifficulty(QuizQuestion.QuestionType questionType,QuizQuestion.Difficulty difficulty);

    // category==ALL && questionType==ALL && difficulty!=ALL
    List<QuizQuestion> findByDifficulty(QuizQuestion.Difficulty difficulty);

    // category!=ALL && questionType==ALL && difficulty!=ALL
    List<QuizQuestion> findByCategoryAndDifficulty(QuizQuestion.Category category,QuizQuestion.Difficulty difficulty);

    List<QuizQuestion> findByQuizSize (QuizQuestion.QuizSize quizSize);

    // category!=ALL && questionType!=ALL && difficulty!=ALL && quizSize != All
    List<QuizQuestion> findByCategoryAndQuestionTypeAndDifficultyAndQuizSize(QuizQuestion.Category category,QuizQuestion.QuestionType questionType,QuizQuestion.Difficulty difficulty, QuizQuestion.QuizSize quizSize);

    // category==ALL && questionType==ALL && difficulty!=ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndDifficulty(QuizQuestion.QuizSize quizSize,QuizQuestion.Difficulty difficulty);

    // category!=ALL && questionType==ALL && difficulty==ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndCategory(QuizQuestion.QuizSize quizSize,QuizQuestion.Category category);

    // category==ALL && questionType!=ALL && difficulty==ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndQuestionType(QuizQuestion.QuizSize quizSize,QuizQuestion.QuestionType questionType);

    // category!=ALL && questionType!=ALL && difficulty==ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndCategoryAndQuestionType(QuizQuestion.QuizSize quizSize,QuizQuestion.Category category,QuizQuestion.QuestionType questionType);

    // category!=ALL && questionType==ALL && difficulty!=ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndCategoryAndDifficulty(QuizQuestion.QuizSize quizSize,QuizQuestion.Category category,QuizQuestion.Difficulty difficulty);

    // category==ALL && questionType!=ALL && difficulty!=ALL && quizSize != All
    List<QuizQuestion> findByQuizSizeAndQuestionTypeAndDifficulty(QuizQuestion.QuizSize quizSize,QuizQuestion.QuestionType questionType,QuizQuestion.Difficulty difficulty);
}