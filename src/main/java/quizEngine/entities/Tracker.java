package quizEngine.entities;





import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Justin on 8/17/16.
 */
@Entity
@Table (name = "tracks")
public class Tracker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    private long correct;
    private long incorrect;
    private int totalQ;
    private String name;
    private String email;

    private LocalDateTime dateTime;


    public long getCorrect() {
        return correct;
    }

    public void setCorrect(long correct) {
        this.correct = correct;
    }

    public long getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(long incorrect) {
        this.incorrect = incorrect;
    }


    public int getTotalQ() {
        return totalQ;
    }

    public void setTotalQ(int totalQ) {
        this.totalQ = totalQ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

