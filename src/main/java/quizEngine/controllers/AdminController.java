package quizEngine.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import quizEngine.entities.QuizQuestion;
import quizEngine.entities.QuizQuestionDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping(value="/admin/")
public class AdminController {

    private final QuizQuestionDAO quizQuestionDAO;

    @Autowired
    public AdminController(QuizQuestionDAO quizQuestionDAO) {
        Assert.notNull(quizQuestionDAO, "QuizQuestionDAO must not be null!");
        this.quizQuestionDAO = quizQuestionDAO;
    }

    @RequestMapping(value="/")
    public String allQuestions(ModelMap model) {
        Iterable<QuizQuestion> quizQuestions = quizQuestionDAO.findAll();
        model.addAttribute("quizQuestions",quizQuestions);
        return "admin/viewAllQuestions";
    }

    @RequestMapping(value="addQuestion")
    public String addQuestion(ModelMap model) {
        model.addAttribute("quizQuestion", new QuizQuestion());
        return "admin/newQuestion";
    }

    @RequestMapping(value="saveNewQuestion")
    public View saveNewQuestion(QuizQuestion quizQuestion) {
        quizQuestionDAO.save(quizQuestion);
        return new RedirectView("/admin/");
    }

    @RequestMapping(value="viewQuestion")
    public String viewQuestion(long id,ModelMap model) {
        QuizQuestion quizQuestion = quizQuestionDAO.findOne(id);
        model.addAttribute("quizQuestion",quizQuestion);
        return "admin/editQuestion";
    }
    @RequestMapping(value="deleteQuestion")
    public View deleteQuestion(long id) {
        QuizQuestion quizQuestion = quizQuestionDAO.findOne(id);
        quizQuestionDAO.delete(quizQuestion);
        return new RedirectView("/admin/");

    }

    @RequestMapping(value="saveEditedQuestion")
    public View saveEditedQuestion(QuizQuestion quizQuestion) {
        quizQuestionDAO.save(quizQuestion);
        return new RedirectView("/admin/");
    }
    @RequestMapping("uploadQuestions")
    public String uploadQuestions() {
        return "admin/uploadQuestions";
    }

    @RequestMapping("saveUploadedQuestions")
    public View saveUploadedQuestions(MultipartFile QuizQuestionsFile) {

        String returnView = "";
        if (!QuizQuestionsFile.isEmpty()) {
            try {
                String path = "/Users/Justin/UPLOADS_Quiz_Engine/";
                Files.write(Paths.get(path+QuizQuestionsFile.getOriginalFilename()),QuizQuestionsFile.getBytes());
                System.out.println("-------- File Upload Successful");
                addUploadToDatabase(path+QuizQuestionsFile.getOriginalFilename());
            } catch (IOException | RuntimeException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("-------- File Is EMPTY!");
        }
        return new RedirectView("/admin/");
    }


    private void addUploadToDatabase(String filePath) {
        try {
            Path quizQuestionUploadedFilePath = Paths.get(filePath);
            ObjectMapper mapper = new ObjectMapper();
            List<QuizQuestion> uploadedQuestions = mapper.readValue(Files.newInputStream(quizQuestionUploadedFilePath), new TypeReference<List<QuizQuestion>>(){});
            for(QuizQuestion uploadedQuizQuestion : uploadedQuestions) {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setCategory(uploadedQuizQuestion.getCategory());
                quizQuestion.setQuestionType(uploadedQuizQuestion.getQuestionType());
                quizQuestion.setDifficulty(uploadedQuizQuestion.getDifficulty());
                quizQuestion.setQuestion(uploadedQuizQuestion.getQuestion());
                quizQuestion.setCorrectMultipleChoiceAnswer(uploadedQuizQuestion.getCorrectMultipleChoiceAnswer());
                quizQuestion.setWrongMultipleChoiceAnswer1(uploadedQuizQuestion.getWrongMultipleChoiceAnswer1());
                quizQuestion.setWrongMultipleChoiceAnswer2(uploadedQuizQuestion.getWrongMultipleChoiceAnswer2());
                quizQuestion.setWrongMultipleChoiceAnswer3(uploadedQuizQuestion.getWrongMultipleChoiceAnswer3());
                quizQuestion.setTrueOrFalse(uploadedQuizQuestion.isTrueOrFalse());
                quizQuestion.setCodeLines(uploadedQuizQuestion.getCodeLines());
                quizQuestionDAO.save(quizQuestion);
            }
        } catch (IOException ioe) {
            System.out.println("Issue reading List from JSON file");
            ioe.printStackTrace();
        }
    }

}
