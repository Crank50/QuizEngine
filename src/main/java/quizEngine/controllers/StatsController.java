package quizEngine.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import quizEngine.entities.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(value="/stats/")
public class StatsController {

  private final QuizQuestionDAO quizQuestionDAO;
  private final TrackerDAO trackerDAO;
  private final DashboardDAO dashboardDAO;
  Tracker tracker = new Tracker();
  QuizQuestion.Category category;

  @Autowired
  public StatsController(QuizQuestionDAO quizQuestionDAO, TrackerDAO trackerDAO, DashboardDAO dashboardDAO) {
    Assert.notNull(quizQuestionDAO, "QuizQuestionDAO must not be null!");
    Assert.notNull(trackerDAO, "trackerDAO must not be null!");
    Assert.notNull(dashboardDAO, "dashboardDAO must not be null!");
    this.quizQuestionDAO = quizQuestionDAO;
    this.trackerDAO = trackerDAO;
    this.dashboardDAO = dashboardDAO;
  }

  //TODO:Quantity of Questions
  @RequestMapping(value = "/")
  public String TotalQuestions(ModelMap modelMap, HttpServletRequest request) {

    modelMap.addAttribute("count", quizQuestionDAO.findAll());
    return "dashboard/dashboard";
  }

}










//  //TODO:Quantity of total quizzes taken
//  @RequestMapping (value="totalQuiz")
//  public String totalQuiz(ModelMap modelMap, HttpServletRequest request) {
//       int q = 0;
//    if(request.getParameter("Let the Quiz Begin") != null){
//      try
//      {
//        Connection connection = TrackerDAO.();
//
//        Statement stmt = connection.createStatement();
//        String query = "SELECT id FROM test";
//        ResultSet rs = stmt.executeQuery(query);
//        while(rs.next()) {
//          Test t = new Test(rs.getInt("id"));
//          tests.add(t);
//        }
//      }
//      catch(SQLException sqle){
//        sqle.printStackTrace();
//      }
//
//      return tests;
//    }
//    return "dashboard/dashboard";
//  }

//  //TODO:Quantity of Unique users -non duplicates
//  @RequestMapping (value="uniqueUsers")
//  public String uniqueUsers() {
//    return "dashboard/dashboard";
//  }
//
//  //TODO:Average of Correctly Answered Questions
//  @RequestMapping (value="stringAvgCorrect")
//  public String stringAvgCorrect() {
//    return "dashboard/dashboard";
//  }
//
//  //TODO:Average of Incorrectly Answered Questions
//  @RequestMapping (value="avgIncorrect")
//  public String avgIncorrect() {
//
//    return "dashboard/dashboard";
//  }
//  //TODO:Abstractify other cool Stats
//
//
//}

