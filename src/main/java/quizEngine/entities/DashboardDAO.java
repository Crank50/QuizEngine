package quizEngine.entities;


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Justin on 8/19/16.
 */
@Transactional
public interface DashboardDAO extends CrudRepository <Dashboard, Long> {

}
