package quizEngine.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;



@Transactional
public interface TrackerDAO extends CrudRepository<Tracker, Long> {
    //Crud Repository has all the methods for Create Read Update and Delete

}

