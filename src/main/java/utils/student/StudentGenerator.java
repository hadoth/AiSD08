package utils.student;

/**
 * Created by Karol Pokomeda on 2017-03-08.
 * Class used for automatic generation of randomized Student objects
 */
public class StudentGenerator {
    private static final String[] SURNAMES =
            {"Mykita", "Sola", "Janczura", "Jaczyszyn", "Stolz",
                    "Cyculak", "Bemm", "Drozd", "Haller", "Hamera"};
    private static final String[] NAMES =
            {"Anna", "Maria", "Teresa", "Joanna", "Patrycja",
            "Patryk", "Przemek", "Piotr", "Protazy", "Paweł"};
    private static final AisdGrade[] GRADES =
            {AisdGrade.NIEOCENIONY, AisdGrade.NIEDOSTATECZNY, AisdGrade.DOSTATECZNY, AisdGrade.DOSTATECZNY_PLUS,
            AisdGrade.DOBRY, AisdGrade.DOBRY_PLUS, AisdGrade.BARDZO_DOBRY, AisdGrade.CELUJACY};

    /**
     * Creates student with surname and name randomly picked from prewritten list
     * @return {Student} randomly generated student
     */
    public static Student randomStudent(){
        return new Student(SURNAMES[(int)(Math.random()*SURNAMES.length)], NAMES[(int)(Math.random()*NAMES.length)]);
    }

    public static Student randomStudent(int minIndexValue, int maxIndexValue){
        return new Student(
                SURNAMES[(int)(Math.random()*SURNAMES.length)],
                NAMES[(int)(Math.random()*NAMES.length)],
                randomIndex(minIndexValue, maxIndexValue)
        );
    }

    /**
     * @param {int} numberOfStudent - number of randomly generated studens
     * @return {Student[]} list of randomly generated students
     */
    public static Student[] randomStudents(int numberOfStudents){
        Student[] studentList = new Student[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++){
            studentList[i] = StudentGenerator.randomStudent();
            studentList[i].setGrade(GRADES[(int)(Math.random()*GRADES.length)]);
        }
        return studentList;
    }

    private static int randomIndex(int minValue, int maxValue){
        return ((int)(Math.random()*(maxValue - minValue + 1))) + minValue;
    }

    public static Student[] randomStudents(int numberOfStudents, int minIndexValue, int maxIndexValue){
        Student[] studentList = new Student[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++){
            studentList[i] = StudentGenerator.randomStudent(minIndexValue, maxIndexValue);
            studentList[i].setGrade(GRADES[(int)(Math.random()*GRADES.length)]);
        }
        return studentList;
    }
}
