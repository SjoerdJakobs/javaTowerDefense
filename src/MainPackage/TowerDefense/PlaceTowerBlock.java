package MainPackage.TowerDefense;

import MainPackage.Program;
import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FrameworkProgram;
import OOFramework.Maths.Vector2;

public class PlaceTowerBlock {
    private BoxCollider collider;
    private Vector2 pos;
    private double width;
    private double height;
    private FrameworkProgram frameworkProgram;

    public PlaceTowerBlock(Vector2 pos, double width, double height) {
        this.pos = pos;
        this.width = width;
        this.height = height;

        //this is all the collision code you need to set it up
        this.collider = new BoxCollider(pos, width, height);  //
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.setColliderTag(ColliderTag.SPAWN_BOX);//
        //////////////////////////////////////////////////////

        frameworkProgram = Program.getProgramInstance();

        frameworkProgram.getCanvas().setOnMouseClicked(e ->
        {
            System.out.println("yeeee1");
            if (collider.ContainsPoint(new Vector2(e.getX(), e.getY()))) {
                System.out.println("yeeee2");
            }
        });
    }

    public void OnCollision(Collider2D other) {
        //System.out.println("colliding box");
    }
}
