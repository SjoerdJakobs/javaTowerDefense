package MainPackage;

//import static javafx.application.Application.launch;

import OOFramework.ExampleClasses.ExampleBFS;
import OOFramework.Maths.Vector2;
import OOFramework.Pathfinding.BFS.BreathFirstSearch;

import static javafx.application.Application.launch;

public class Main
{
    public static void main(String[] args)
    {
        /*
        Vector2 v0 = new Vector2(10,0);
        Vector2 v1 = new Vector2(10,20);
        Vector2 v2 = new Vector2(0.43,0);
        Vector2 v3 = new Vector2(0.43,0.8);
        Vector2 v4 = new Vector2(10,0.2);
        System.out.println(v4.x + "," + v4.y);
        System.out.println(v4.GetMagnitude());
        v4.Normalize();
        System.out.println(v4.x + "," + v4.y);
        System.out.println(v4.GetMagnitude());
        */

        Program program = new Program();
        launch(Program.class);
        //ExampleBFS exampleBFS = new ExampleBFS();
        //exampleBFS.ShowBFS();
    }
}

