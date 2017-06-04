package utils.student;

/**
 * Created by Karol Pokomeda on 2017-03-08.
 */
public enum AisdGrade {
    CELUJACY (5.5),
    BARDZO_DOBRY (5.0),
    DOBRY_PLUS (4.5),
    DOBRY (4.0),
    DOSTATECZNY_PLUS (3.5),
    DOSTATECZNY (3.0),
    NIEDOSTATECZNY (2.0),
    NIEOCENIONY(0);

    private double grade;

    AisdGrade(double grade){
        this.grade = grade;
    }

    /**
     * @return {double} numeric value of grad
     */
    public double getGrade(){
        return this.grade;
    }
}