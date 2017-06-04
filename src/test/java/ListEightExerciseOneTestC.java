import hash.collection.ChainHashMap;
import hash.collection.MyHashMap;
import hash.function.IntegerPrimeHashFunction;
import utils.student.Student;
import utils.student.StudentGenerator;

import java.util.ArrayList;

/**
 * Created by Karol Pokomeda on 2017-05-20.
 */
public class ListEightExerciseOneTestC {
    public static void main(String[] args){
        MyHashMap<Integer, Student> hashMap1 = new ChainHashMap<>(new IntegerPrimeHashFunction(100000));
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
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
