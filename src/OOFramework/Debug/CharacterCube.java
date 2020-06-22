package OOFramework.Debug;

import OOFramework.Collision2D.Colliders.BoxCollider;
import OOFramework.Collision2D.Colliders.Collider2D;
import OOFramework.Collision2D.Enums.ColliderTag;
import OOFramework.FXGraphics2dClasses.Rectangle;
import OOFramework.InputHandling.*;
import OOFramework.Maths.Vector2;
import OOFramework.Sound.SoundPlayer;
import OOFramework.StandardObject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CharacterCube extends StandardObject {
    private final Vector2 pos;
    private final Vector2 lastPos;
    private final double maxMovSpeed = 450;
    private final double accelaration = 75;
    private final double maxSpeed = 2000;
    private final double jumpPower = 1300;
    private final double width;
    private final double halfWidth;
    private final double height;
    private final double halfHeight;
    private final double gravityVal = 9.8;
    private final double weightVal = 170;
    private final boolean canJump = true;
    private final boolean canDoubleJump = false;
    private final BoxCollider collider;
    private final Rectangle rectangle;
    private final Rectangle rectangleSmoll;
    private final MouseInput mouseInput;
    private final Vector2 mousePos;
    private Vector2 velocity;
    private double velocitySpeed;
    private final double friction = 500;
    private boolean grounded = true;
    private boolean isCollidingWithGround = false;
    private final MouseEventCallback onMouseMoveCallback;
    private final ArrayList<Key> keys = new ArrayList<>();

    private final KeyboardEventCallback keyboardEventCallback;
    private final Key keyW;
    private final Key keyA;
    private final Key keyS;
    private final Key keyD;
    private final Key keySPACE;
    private final KeyboardInput keyboardInput;

    public CharacterCube(Vector2 pos, double width, double height) {
        super(true, true, true, true, 1000, 1000);
        this.pos = pos;
        this.lastPos = new Vector2();
        this.width = width;
        this.halfWidth = width * 0.5;
        this.height = height;
        this.halfHeight = height * 0.5;
        this.velocity = new Vector2();
        this.mousePos = new Vector2();

        keyboardEventCallback = this::onSpace;

        this.keyboardInput = KeyboardInput.getInstance();
        keyW = new Key(KeyCode.W);
        keyA = new Key(KeyCode.A);
        keyS = new Key(KeyCode.S);
        keyD = new Key(KeyCode.D);
        keySPACE = new Key(KeyCode.SPACE, keyboardEventCallback, null, null);
        keys.add(keyW);
        keys.add(keyA);
        keys.add(keyS);
        keys.add(keyD);
        keys.add(keySPACE);
        for (Key k : keys) {
            keyboardInput.AddKey(k);
        }

        this.mouseInput = MouseInput.getInstance();
        onMouseMoveCallback = this::OnMouseMoved;
        mouseInput.getOnMouseMovedToBeAdded().add(this.onMouseMoveCallback);
        mouseInput.setShouldAddToOnMouseMoved(true);

        //SoundPlayer.Play("bensoundFunnysong.wav", 0.5f);

        //this is all the collision code you need to set it up
        this.collider = new BoxCollider(pos, width, height);//
        this.collider.collisionCallback = this::OnCollision;//
        this.collider.preCollisionCallback = this::PreCollision;//
        this.collider.setColliderTag(ColliderTag.DEFAULT);  //
        this.collider.setOwnerObject(this);                 //
        //////////////////////////////////////////////////////

        this.rectangle = new Rectangle((int) pos.x, (int) pos.y, (int) width, (int) height, 0);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        rectangle.setRectangleColor(Color.blue);
        rectangle.SetImageByFileName("arrow100x100.png");

        this.rectangleSmoll = new Rectangle((int) pos.x, (int) pos.y, (int) width / 10, (int) height / 10, 0);
        rectangleSmoll.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        rectangleSmoll.setRectangleColor(Color.lightGray);

        frameworkProgram.getCanvas().setOnMouseClicked(e ->
        {
            pos.x = e.getX();
            pos.y = e.getY();
        });

    }

    public void OnMouseMoved(MouseEvent e) {
        //System.out.println(Vector2.AngleBetweenVectors(pos,new Vector2(e.getX()-pos.x,e.getY()-pos.y)));
        System.out.println(Vector2.LookAtVector(pos, mousePos));
        mousePos.x = e.getX();
        mousePos.y = e.getY();
    }

    private void onSpace(KeyEvent e) {
        System.out.println("pressed by event");
        if (grounded) {
            //velocity.y += -jumpPower;
        }
    }

    @Override
    protected void InputLoop(double deltaTime) {
        super.InputLoop(deltaTime);
        for (Key k : keys) {
            if (k.getKeyState() == KeyState.PRESSED || k.getKeyState() == KeyState.HOLD) {
                switch (k.getKeyCode()) {
                    case A:
                        //System.out.println(k.getKeyState());
                        if (velocity.x > -maxMovSpeed) {
                            velocity.x -= accelaration;
                        }
                        break;
                    case D:
                        if (velocity.x < maxMovSpeed) {
                            velocity.x += accelaration;
                        }
                        break;
                    case SPACE:
                        if (k.getKeyState() == KeyState.PRESSED) {
                            System.out.println("pressed by check");
                        }
                        if (grounded) {
                            velocity.y += -jumpPower;
                            grounded = false;
                        }
                        break;

                }
            }
        }
    }

    @Override
    protected void RenderLoop(double deltaTime) {
        frameworkProgram.getGraphics2D().setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        rectangle.FilledDraw(frameworkProgram.getGraphics2D());
        rectangle.ImageDraw(frameworkProgram.getGraphics2D());
        rectangleSmoll.FilledDraw(frameworkProgram.getGraphics2D());
    }

    public void PreCollision() {
        isCollidingWithGround = false;
    }

    public void OnCollision(Collider2D other) {
        if (other.getColliderTag() == ColliderTag.TEST) {
            isCollidingWithGround = true;
            //if(this.pos.y + halfHeight-10 < other.getPos().y-(20)&&velocity.y > 0)
            if (this.pos.y + halfHeight > other.getPos().y - (20) && this.lastPos.y + halfHeight < other.getPos().y - (20)) {
                velocity.y = 0;
                //velocity.y += -jumpPower;
                SoundPlayer.Play("Jump.wav", 0.5f);
                grounded = true;
                this.pos.y = other.getPos().y - (20 + halfHeight);
            }
        }
    }

    @Override
    protected void MainLoop(double deltaTime) {
        super.MainLoop(deltaTime);
        rectangle.getSquare2D().setRotation((float) Vector2.LookAtVector(pos, mousePos));
        lastPos.x = pos.x;
        lastPos.y = pos.y;

        if (!grounded) {
            velocity.y += gravityVal * weightVal * deltaTime;
        }

        velocitySpeed = velocity.GetMagnitude();

        if (velocitySpeed > maxSpeed) {
            velocitySpeed = maxSpeed;
        }

        this.pos.x += velocity.x * deltaTime;
        this.pos.y += velocity.y * deltaTime;
        collider.setPos(pos);
        rectangle.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));
        rectangleSmoll.getSquare2D().setPosition(new Point2D.Double(pos.x, pos.y));

        //dragg
        //velocity.subtract(velocity.MultiplyByDouble((friction / weightVal) * deltaTime));

        if (!velocity.IsZero()) {
            velocity = velocity.Normalize().MultiplyByDouble(velocitySpeed - (friction * deltaTime));
        }
        if (!isCollidingWithGround) {
            grounded = false;
        }
    }
}
