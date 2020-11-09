/*

*/
/*import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JUnitTesting {
    @org.junit.jupiter.api.Test

    public void shouldProcessUserInput() {
        Main my_main=new Main();

        StringWriter output = new StringWriter();
        String input = "Add WebUser Niv";
        //assertEquals(10, System.processUserInput(new Scanner(input), new PrintWriter(output)));
        assertEquals(output.toString(), "Enter your password: ");
    }*//*

    void Main_check() throws FileNotFoundException {
        Main my_main=new Main();
        System.out.println(my_main);
        File file = new File("C:\\Users\\Admin\\Desktop\\sample.txt");
        //Instantiating the PrintStream class
        PrintStream stream = new PrintStream(file);
        System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
        System.setOut(stream);
        ByteArrayInputStream in = new ByteArrayInputStream("Add WebUser Niv".getBytes());
        System.setIn(in);
        System.out.println("Hello, how are you");
        System.out.println("Welcome to Tutorialspoint");

        //assertEquals( "Enter your password: ",my_main.toString());

        //System.setIn(sysInBackup);
        //assertEquals("Enter your password: ",System.out.toString());

// do your thing

// optionally, reset System.in to its original
    }
    */
/*
    @org.junit.jupiter.api.Test
        // check if its correct name
    void getName() throws  Exception {
        BestFirstSearch b=new BestFirstSearch();
        assertEquals("BestFirstSearch",b.getName());
    }
    ArrayList<AState> Sol_Path=new ArrayList<>();
    @org.junit.jupiter.api.Test
        //the tetst check the case of maze full of walls-1
    void test1_fullmazewithWalls() throws  Exception {
        Maze m=build_full();
        BestFirstSearch b=new BestFirstSearch();
        //DepthFirstSearch b=new DepthFirstSearch();
        SearchableMaze searchableMaze = new SearchableMaze(m);
        assertEquals(0,b.getNumberOfNodesEvaluated());
        assertEquals(Sol_Path,b.solve(searchableMaze).getSolutionPath());
        assertEquals(0,b.solve(searchableMaze).getSolutionPath().size());
    }
    @org.junit.jupiter.api.Test
        // check the case of maze without solution
    void test2_maze_without_Sol() throws  Exception {
        Maze m=build_diagonal();
        BestFirstSearch b=new BestFirstSearch();
        DepthFirstSearch d=new DepthFirstSearch();
        SearchableMaze searchableMaze = new SearchableMaze(m);
        assertEquals(Sol_Path,b.solve(searchableMaze).getSolutionPath());
        assertEquals(0,b.solve(searchableMaze).getSolutionPath().size());
    }
    @org.junit.jupiter.api.Test
        // check the case of empty maze. the way needs to be the same always and also the evaluated
    void test3_Regular_maze() throws  Exception {
        EmptyMazeGenerator m= new EmptyMazeGenerator();
        Maze maze = m.generate(15/*rows*//*
*/
/*columns);
        Position end_pos1 = new Position(14, 0);
        maze.Set_end(end_pos1);
        BestFirstSearch b=new BestFirstSearch();
        //DepthFirstSearch b=new DepthFirstSearch();
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Solution x=b.solve(searchableMaze);
        assertNotNull(b.getNumberOfNodesEvaluated(),"suppose to return null");
        assertNotNull(x.getSolutionPath().size(),"suppose to return null");
    }

*//*

}
*/
