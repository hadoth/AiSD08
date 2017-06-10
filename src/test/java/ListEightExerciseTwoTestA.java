import graph.bow.IncidenceBowGraph;
import graph.bow.ListBowGraph;
import graph.bow.MatrixBowGraph;
import graph.bow.MyBowGraph;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-05-30.
 */
public class ListEightExerciseTwoTestA {
    public static void main(String[] args) {
//        MyBowGraph<Character> myGraph = new MatrixBowGraph<>();
        MyBowGraph<Character> myGraph = new ListBowGraph<>();
//        MyBowGraph<Character> myGraph = new IncidenceBowGraph<>();
        char[] characters = {'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x'};
        for (int i = 0; i < characters.length; i++) {
            myGraph.addVertex(characters[i]);
        }

        assert myGraph.vertexCount() == characters.length;
        assert myGraph.bowCount() == 0;

        myGraph.addBow(characters[0], characters[1], 3);
        myGraph.addBow(characters[0], characters[2], 4);
        myGraph.addBow(characters[0], characters[3], 5);
        myGraph.addBow(characters[1], characters[3], 3);
        myGraph.addBow(characters[1], characters[4], 2);
        myGraph.addBow(characters[2], characters[5], 6);
        myGraph.addBow(characters[3], characters[5], 4);
        myGraph.addBow(characters[4], characters[5], 9);
        myGraph.addBow(characters[4], characters[6], 4);
        myGraph.addBow(characters[5], characters[7], 4);
        myGraph.addBow(characters[5], characters[8], 7);
        myGraph.addBow(characters[6], characters[7], 8);
        myGraph.addBow(characters[7], characters[8], 3);

        System.out.println(myGraph);
        System.out.println();

        System.out.println(myGraph.bowCount());
        System.out.println(myGraph.deleteBow('u', 'x'));
        System.out.println(myGraph.deleteBow('w', 'x'));
        System.out.println(myGraph.bowCount());

        System.out.println(myGraph.vertexCount());
        System.out.println(myGraph.deleteVertex('x'));
        System.out.println(myGraph.vertexCount());

        List<Character> BFS = myGraph.BFS('p', (Character character) -> character.equals('w'));
        System.out.println(Arrays.toString(BFS.toArray()));

        List<Character> DFS = myGraph.DFS('p', (Character character) -> character.equals('w'));
        System.out.println(Arrays.toString(DFS.toArray()));
    }
}
