package OOFramework.ExampleClasses;

import OOFramework.Pathfinding.BFS.BreathFirstSearchT2;

public class ExampleBFS {

    public void ShowBFS()
    {
        BreathFirstSearchT2 BFS = new BreathFirstSearchT2();
        BFS.CreateTileGrid(25,25);

        for (int i =0; i<12; i++)
        {
            BFS.SetWall(3+i, 7);
        }
        for (int i =0; i<24; i++)
        {
            BFS.SetWall(0+i, 22);
        }
        for (int i =0; i<24; i++)
        {
            BFS.SetWall(13, 1+i);
        }

        BFS.Addroute(10,12,"route0");
        //BFS.Addroute(19,17,"route1");

        for (int j = 0; j < 25; j++)
        {
            System.out.println("");
            for (int i= 0; i < 25; i++)
            {
                if(BFS.getTileMap()[i][j].isWall)
                {
                    System.out.print("H ");
                }
                else if(BFS.getTileMap()[i][j].routes.get("route0") == null)
                {
                    System.out.print("o ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").IsZero())
                {
                    System.out.print("X ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").x ==  1)
                {
                    System.out.print("> ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").x == -1)
                {
                    System.out.print("< ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").y ==  1)
                {
                    System.out.print("v ");
                }else if(BFS.getTileMap()[i][j].routes.get("route0").y == -1)
                {
                    System.out.print("^ ");
                }
            }
        }

        System.out.println("");
        System.out.println("");
/*
        for (int j = 0; j < 25; j++)
        {
            System.out.println("");
            for (int i= 0; i < 25; i++)
            {
                if(BFS.getTileMap()[i][j].isWall)
                {
                    System.out.print("H ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1") == null)
                {
                    System.out.print("o ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").IsZero())
                {
                    System.out.print("X ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").x ==  1)
                {
                    System.out.print("> ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").x == -1)
                {
                    System.out.print("< ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").y ==  1)
                {
                    System.out.print("v ");
                }else if(BFS.getTileMap()[i][j].routes.get("route1").y == -1)
                {
                    System.out.print("^ ");
                }
            }
        }
 */
    }
}
