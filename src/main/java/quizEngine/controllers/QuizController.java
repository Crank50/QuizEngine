package quizEngine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import quizEngine.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping(value="/quiz/")
public class QuizController {


    private final TrackerDAO trackerDAO;
    private final QuizQuestionDAO quizQuestionDAO;

    @Autowired//The tracker DAO CRUD is now called to be available to use. "Yo CRUD Come here a min!"
    public QuizController(QuizQuestionDAO quizQuestionDAO, TrackerDAO trackerDAO) {
        Assert.notNull(quizQuestionDAO, "QuizQuestionDAO must not be null!");
        Assert.notNull(trackerDAO, "TrackerDAO must not be null!");
        this.quizQuestionDAO = quizQuestionDAO;
        this.trackerDAO = trackerDAO;
    }

    @RequestMapping(value = "/")
    public String dashboard(ModelMap model) {
        model.addAttribute("categories", QuizQuestion.Category.values());
        model.addAttribute("QuizTypes", QuizQuestion.QuizType.values());
        model.addAttribute("questionTypes", QuizQuestion.QuestionType.values());
        model.addAttribute("difficulties", QuizQuestion.Difficulty.values());
        model.addAttribute("quizSizes", QuizQuestion.QuizSize.values());
        model.addAttribute("tracker", new Tracker());
        //This creates the tracker "the locker" for information to be stored in.

        return "quiz/index";
    }

    @RequestMapping(value = "startQuiz")
    public View startQuiz(String name, String email, String category, String quizType, String questionType, String difficulty, String quizSize, HttpServletRequest request) {

        request.getSession().setAttribute("name", name);
        //Add Date & Time with the saving of name & email
        request.getSession().setAttribute("email", email);
        //Store name & email into Hashmap in Results Entity
        request.getSession().setAttribute("category", category);
        request.getSession().setAttribute("quizType", quizType);
        request.getSession().setAttribute("questionType", questionType);
        request.getSession().setAttribute("difficulty", difficulty);
        request.getSession().setAttribute("quizSize", quizSize);

        Tracker tracker = new Tracker();
        tracker.setEmail(email);
        tracker.setName(name);
        tracker.setDateTime(LocalDateTime.now());
//        trackerDAO.save(tracker);
//        request.getSession().setAttribute("tracker", tracker);


        Iterable<QuizQuestion> quizQuestions = null;
        int numberOfQuestions = 0;
        // A selection has been made on category
        // category!=ALL && questionType==ALL && difficulty==ALL && quizSize == All
        if (!category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategory(QuizQuestion.Category.valueOf(category));
        }
        // category!=ALL && questionType!=ALL && difficulty==ALL && quizSize == All
        else if (!category.equals(QuizQuestion.Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndQuestionType(QuizQuestion.Category.valueOf(category), QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category!=ALL && questionType!=ALL && difficulty!=ALL && !quizSize == All
        else if (!category.equals(QuizQuestion.Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndQuestionTypeAndDifficultyAndQuizSize(QuizQuestion.Category.valueOf(category), QuizQuestion.QuestionType.valueOf(questionType), QuizQuestion.Difficulty.valueOf(difficulty), QuizQuestion.QuizSize.valueOf(quizSize));
        }
        // category==ALL && questionType!=ALL && difficulty==ALL && quizSize == All
        else if (category.equals(QuizQuestion.Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuestionType(QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category==ALL && questionType!=ALL && difficulty!=ALL && quizSize == All
        else if (category.equals(QuizQuestion.Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuestionTypeAndDifficulty(QuizQuestion.QuestionType.valueOf(questionType), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category==ALL && questionType==ALL && difficulty!=ALL && quizSize == All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByDifficulty(QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category!=ALL && questionType==ALL && difficulty!=ALL && quizSize == All
        else if (!category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByCategoryAndDifficulty(QuizQuestion.Category.valueOf(category), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category==ALL && questionType==ALL && difficulty==ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSize(QuizQuestion.QuizSize.valueOf(quizSize));
        }
        // category==ALL && questionType==ALL && difficulty!=ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndDifficulty(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category!=ALL && questionType==ALL && difficulty==ALL && quizSize != All
        else if (!category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndCategory(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.Category.valueOf(category));
        }
        // category==ALL && questionType!=ALL && difficulty==ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && !questionType.equals(QuizQuestion.QuestionType.ALL) && difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndQuestionType(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category!=ALL && questionType!=ALL && difficulty==ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndCategoryAndQuestionType(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.Category.valueOf(category), QuizQuestion.QuestionType.valueOf(questionType));
        }
        // category!=ALL && questionType==ALL && difficulty!=ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndCategoryAndDifficulty(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.Category.valueOf(category), QuizQuestion.Difficulty.valueOf(difficulty));
        }
        // category==ALL && questionType!=ALL && difficulty!=ALL && quizSize != All
        else if (category.equals(QuizQuestion.Category.ALL) && questionType.equals(QuizQuestion.QuestionType.ALL) && !difficulty.equals(QuizQuestion.Difficulty.ALL) && !quizSize.equals(QuizQuestion.QuizSize.ALL)) {
            quizQuestions = quizQuestionDAO.findByQuizSizeAndQuestionTypeAndDifficulty(QuizQuestion.QuizSize.valueOf(quizSize), QuizQuestion.QuestionType.valueOf(questionType), QuizQuestion.Difficulty.valueOf(difficulty));
        }


        if (quizQuestions != null) {
            numberOfQuestions = countIterable(quizQuestions);
        }


        // category==ALL && questionType==ALL && difficulty==ALL || no results
        if (quizQuestions == null || numberOfQuestions < 1) {
            quizQuestions = quizQuestionDAO.findAll();
            numberOfQuestions = countIterable(quizQuestions);
        }

        int i = 0;
        HashMap<Integer, QuizQuestion> quizQuestionsHashMap = new HashMap<>();
        for (QuizQuestion quizQuestion : quizQuestions) {
            quizQuestionsHashMap.put(i, quizQuestion);
            i++;
        }


        trackerDAO.save(tracker);
        tracker.setTotalQ(countIterable(quizQuestions));
        request.getSession().setAttribute("tracker", tracker);

        request.getSession().setAttribute("quizQuestionsHashMap", quizQuestionsHashMap);
        ArrayList<Integer> usedQuestions = new ArrayList<>();
        request.getSession().setAttribute("usedQuestions", usedQuestions);

        return new RedirectView("nextQuestion");
    }

    @RequestMapping(value = "nextQuestion")
    public String nextQuestion(ModelMap model, HttpServletRequest request) {
        ArrayList<Integer> usedQuestions = (ArrayList<Integer>) request.getSession().getAttribute("usedQuestions");
        HashMap<Integer, QuizQuestion> quizQuestionsHashMap = (HashMap<Integer, QuizQuestion>) request.getSession().getAttribute("quizQuestionsHashMap");
        int numberOfQuestions = quizQuestionsHashMap.size();


        if (usedQuestions.size() >= numberOfQuestions) {
            return "quiz/quizResults";
        }
        boolean isNewQuestion = false;
        int questionNumber = -1;
        while (!isNewQuestion) {
            questionNumber = randomInt(0, numberOfQuestions - 1);
            if (!usedQuestions.contains(new Integer(questionNumber))) {
                isNewQuestion = true;
            }



        }
        request.getSession().setAttribute("questionNumber", questionNumber);
        model.addAttribute("quizQuestion", quizQuestionsHashMap.get(questionNumber));
        usedQuestions.add(questionNumber);
        request.getSession().setAttribute("usedQuestions", usedQuestions);

        return "quiz/question";
    }


    @RequestMapping(value = "questionAnswer")
    public String questionAnswer(String multiAnswer, String trueFalseAnswer, ArrayList<String> codeLines, Long correct, Long incorrect, ModelMap model, String quizSize, HttpServletRequest request) {
        HashMap<Integer, QuizQuestion> quizQuestionsHashMap = (HashMap<Integer, QuizQuestion>) request.getSession().getAttribute("quizQuestionsHashMap");
        Tracker tracker = (Tracker) request.getSession().getAttribute("tracker");
        int questionNumber = (Integer) request.getSession().getAttribute("questionNumber");
        QuizQuestion quizQuestion = quizQuestionsHashMap.get(questionNumber);
        model.addAttribute("quizQuestion", quizQuestion);
        model.remove("correct");
        model.remove("incorrect");
        int tots = 1;
        long currentQuizTotal = tots + tracker.getCorrect() + tracker.getIncorrect();
        System.out.println(currentQuizTotal);





        if (quizQuestion.getQuestionType().equals(QuizQuestion.QuestionType.MULTIPLE_CHOICE)) {
            if (multiAnswer != null && multiAnswer.equalsIgnoreCase("yes")) {
                model.addAttribute("Right", "RAINBOW TO THE MAX!");
                request.getSession().setAttribute("correct", correct);
                tracker.setCorrect(tracker.getCorrect() + 1);

            } else {
                model.addAttribute("Wrong", "SORRY Wrong Answer");
                request.getSession().setAttribute("incorrect", incorrect);
                tracker.setIncorrect(tracker.getIncorrect() + 1);
            }
        } else if (quizQuestion.getQuestionType().equals(QuizQuestion.QuestionType.TRUE_FALSE)) {
            if (trueFalseAnswer != null && quizQuestion.isTrueOrFalse() == Boolean.valueOf(trueFalseAnswer)) {
                model.addAttribute("Right", "RAINBOW TO THE MAX!!");
                request.getSession().setAttribute("correct", correct);
                tracker.setCorrect(tracker.getCorrect() + 1);

            } else {
                model.addAttribute("Wrong", "SORRY Wrong Answer");
                request.getSession().setAttribute("incorrect", incorrect);
                tracker.setIncorrect(tracker.getIncorrect() + 1);

            }

        } else if (quizQuestion.getQuestionType().equals(QuizQuestion.QuestionType.CODE)) {
            if (codeLines == (quizQuestion.getCodeLines())) {
                model.addAttribute("code", codeLines);
                model.addAttribute("Right", "RAINBOW TO THE MAX");
                request.getSession().setAttribute("correct", correct);

                tracker.setCorrect(tracker.getCorrect() + 1);
            } else {
                model.addAttribute("Wrong", "Sorry Wrong Answer");
                request.getSession().setAttribute("incorrect", incorrect);
                tracker.setCorrect(tracker.getCorrect() + 1);

            }
        }
            if (tracker.getTotalQ() == 3 && quizSize.equals(QuizQuestion.QuizSize.SMALL)) {
                return "quiz/quizResults";

            } else if (tracker.getTotalQ() == 25 && quizQuestion.getQuizSize().equals(QuizQuestion.QuizSize.MEDIUM)) {
                return "quiz/quizResults";

            } else if (tracker.getTotalQ() == 35 && quizQuestion.getQuizSize().equals(QuizQuestion.QuizSize.LARGE)) {
                return "quiz/quizResults";
            }

        request.getSession().setAttribute("currentQuizTotal", currentQuizTotal +1 );

        return "quiz/answer";
    }



    private int countIterable(Iterable<?> it) {
        if (it instanceof Collection)
            return ((Collection<?>)it).size();

        // else iterate
        int i = 0;
        for (Object obj : it) i++;
        return i;
    }

    private static int randomInt(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) +1) +min;
        return randomNumber;
    }
}
