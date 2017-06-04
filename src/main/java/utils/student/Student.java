package utils.student;

/**
 * Student class for representation of student, which stores most important data:
 * name, surname, index number and AiSD grade
 * Created by Karol Pokomeda on 2017-03-08.
 */

public class Student {
    private  static int currentIndex = 162503;

    private String surname;
    private String name;
    private AisdGrade grade;
    private int indexNumber;

    private static int indexGenerator(){
        currentIndex++;
        return currentIndex;
    }

    /**
     * Student index number is generated automatically
     * @param {String} surname
     * @param {String} name
     */
    public Student (String surname, String name){
        this.surname = surname;
        this.name = name;
        this.grade = AisdGrade.NIEOCENIONY;
        this.indexNumber = Student.indexGenerator();
    }

    /**
     * Student index number is generated automatically
     * @param {String} surname
     * @param {String} name
     * @param {int} index number
     */
    public Student (String surname, String name, int indexNumber){
        this.surname = surname;
        this.name = name;
        this.grade = AisdGrade.NIEOCENIONY;
        this.indexNumber = indexNumber;
    }

    /**
     * @return {String} student's name
     */
    public String getName() {return this.name;}

    /**
     * @return {String} student's surname
     */
    public String getSurname() {return this.surname;}

    /**
     * @return {double} student's AiSD grade
     */
    public double getGrade() {return this.grade.getGrade();}

    /**
     * @return {int} student's index number
     */
    public int getIndexNumber() {return this.indexNumber;}

    /**
     * @param {AisdGrade} grade
     */
    public void setGrade(AisdGrade grade){this.grade = grade;}

    public String toString(){
        return this.name + " " + this.surname + ", nr albumu " + this.indexNumber + "; ocena: " + this.getGrade();
    }
}