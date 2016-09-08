package quizEngine.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quizEngine.entities.QuizQuestion;
import quizEngine.entities.QuizQuestionDAO;

@RestController
@RequestMapping("/restData/")
public class DataRestController {

    private final QuizQuestionDAO quizQuestionDAO;

    @Autowired
    public DataRestController(QuizQuestionDAO quizQuestionDAO) {
        Assert.notNull(quizQuestionDAO, "QuizQuestionDAO must not be null!");
        this.quizQuestionDAO = quizQuestionDAO;
    }

    @RequestMapping("getAllQuestions")
    public Iterable<QuizQuestion> getAllQuestions() {
        return quizQuestionDAO.findAll();
    }
}
