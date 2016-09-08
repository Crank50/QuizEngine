package quizEngine.entities;

import javax.persistence.*;

/**
 * Created by Justin on 8/19/16.
 */
@Entity
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public int tId;
      //TODO:Quantity of each Category of Questions
    public long cId;
    //  //TODO:Quantity of total quizzes taken
    public long qId;
    //  //TODO:Quantity of Unique users -non duplicates
    public long uId;
    //  //TODO:Average of Correctly Answered Questions
    public long aId;
    //  //TODO:Average of Incorrectly Answered Questions
    public long iId;







    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public long getqId() {
        return qId;
    }

    public void setqId(long qId) {
        this.qId = qId;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public long getaId() {
        return aId;
    }

    public void setaId(long aId) {
        this.aId = aId;
    }

    public long getiId() {
        return iId;
    }

    public void setiId(long iId) {
        this.iId = iId;
    }
}
