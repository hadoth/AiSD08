import hash.collection.MyHashMap;
import hash.collection.TableHashMap;
import hash.function.IntegerPrimeHashFunction;
import utils.student.Student;
import utils.student.StudentGenerator;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-05-20.
 */
public class ListEightExerciseOneTestB {
    public static void main(String[] args){
        MyHashMap<Integer, Student> hashMap1 = new TableHashMap<>(new IntegerPrimeHashFunction(100));
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student randStudent = StudentGenerator.randomStudent(150000, 250000);
            if (!hashMap1.has(randStudent.getIndexNumber())){
                hashMap1.add(randStudent.getIndexNumber(), randStudent);
                students.add(randStudent);
            }
        }

        for (Student student : students){
            System.out.println(student + "; " + hashMap1.get(student.getIndexNumber()));
        }
    }
}
