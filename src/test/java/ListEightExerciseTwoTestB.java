import graph.bow.MatrixBowGraph;
import graph.bow.MyBowGraph;
import graph.edge.ListEdgeGraph;
import graph.edge.MatrixEdgeGraph;
import graph.edge.MyEdgeGraph;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Karol Pokomeda on 2017-05-30.
 */
public class ListEightExerciseTwoTestB {
    public static void main(String[] args){
//        MyEdgeGraph<Character> myGraph = new MatrixEdgeGraph<>();
        MyEdgeGraph<Character> myGraph = new ListEdgeGraph<>();
       char[] characters = {'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x'};
        for (int i = 0; i < characters.length; i++){
            myGraph.addVertex(characters[i]);
        }

        assert myGraph.vertexCount() == characters.length;
        assert myGraph.edgeCount() == 0;

        myGraph.addEdge(characters[0],characters[1],3);
        myGraph.addEdge(characters[0],characters[2],4);
        myGraph.addEdge(characters[0],characters[3],5);
        myGraph.addEdge(characters[1],characters[3],3);
        myGraph.addEdge(characters[1],characters[4],2);
        myGraph.addEdge(characters[2],characters[5],6);
        myGraph.addEdge(characters[3],characters[5],4);
        myGraph.addEdge(characters[4],characters[5],9);
        myGraph.addEdge(characters[4],characters[6],4);
        myGraph.addEdge(characters[5],characters[7],4);
        myGraph.addEdge(characters[5],characters[8],7);
        myGraph.addEdge(characters[6],characters[7],8);
        myGraph.addEdge(characters[7],characters[8],3);

        MyEdgeGraph<Character> MST = myGraph.MST();

        System.out.println(myGraph);
        System.out.println();
        System.out.println(MST);

        myGraph.deleteEdge('u','x');
        myGraph.deleteEdge('w','x');
        try{
            myGraph.MST();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        myGraph.deleteVertex('x');

        System.out.println(myGraph.MST());

        List<Character> BFS = myGraph.BFS('p', (Character character)->character.equals('w'));
        System.out.println(Arrays.toString(BFS.toArray()));

        List<Character> DFS = myGraph.DFS('p', (Character character)->character.equals('w'));
        System.out.println(Arrays.toString(DFS.toArray()));
    }
}
