package OOFramework.ExampleClasses;

import OOFramework.Pathfinding.BFS.BreathFirstSearch;

public class ExampleBFS {

    public void ShowBFS()
    {
        BreathFirstSearch BFS = new BreathFirstSearch();
        BFS.CreateTileGrid(25,25);

        for (int i =0; i<12; i++)
        {
            BFS.SetWall(3+i, 7);
        }
        for (int i =0; i<24; i++)
        {
            BFS.SetWall(13, 1+i);
        }

        BFS.Addroute(10,12,"route0");
        BFS.Addroute(19,17,"route1");

        for (int j = 0; j < 25; j++)
        {
            System.out.println("");
            for (int i= 0; i < 25; i++)
            {
                if(BFS.getTileMap()[i][j].isWall)
                {
                    System.out.print("H ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").IsZero())
                {
                    System.out.print("X ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").x == 1)
                {
                    System.out.print("> ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").x == -1)
                {
                    System.out.print("< ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").y == 1)
                {
                    System.out.print("v ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").y == -1)
                {
                    System.out.print("^ ");
                }
                else
                {
                    System.out.print("o ");
                }
            }
        }

        System.out.println("");
        System.out.println("");

        for (int j = 0; j < 25; j++)
        {
            System.out.println("");
            for (int i= 0; i < 25; i++)
            {
                if(BFS.getTileMap()[i][j].isWall)
                {
                    System.out.print("H ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").IsZero())
                {
                    System.out.print("X ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").x == 1)
                {
                    System.out.print("> ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").x == -1)
                {
                    System.out.print("< ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").y == 1)
                {
                    System.out.print("v ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").y == -1)
                {
                    System.out.print("^ ");
                }
                else
                {
                    System.out.print("o ");
                }
            }
        }
    }
}
