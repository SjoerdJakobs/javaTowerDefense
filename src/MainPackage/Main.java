package MainPackage;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
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

        //ExampleBFS bfs = new ExampleBFS();
        //bfs.ShowBFS();
        Program program = new Program();
        launch(Program.class);
        //ExampleBFS exampleBFS = new ExampleBFS();
        //exampleBFS.ShowBFS();

        //Vector2 v1 = new Vector2(1,0);
        //Vector2 v2 = new Vector2(8,-2);
        //System.out.println(Vector2.AngleBetweenVectors(v1,v2));
        //180° × π/180 = 3,142rad it works :D
    }
}

