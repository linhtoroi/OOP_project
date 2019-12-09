package Enemy;

import GameEntity.GameEntity;
import GameEntity.Config;
import GameEntity.Target;
import GameEntity.Road;
import Main.MainController;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import Main.GameStage;
import GameEntity.Point;
import java.util.HashSet;


public abstract class Enemy extends GameEntity {
    protected Point p = new Point(0,0);
    private  int mm;
    protected final Point[] DELTA_DIRECTION_ARRAY =
            {new Point(0.0, -1.0), new Point(0.0, 1.0), new Point(-1.0, 0.0), new Point(1.0, 0.0)};

    protected long health;
    protected long armor;
    protected double speed=1;
    protected long reward;
    public ImageView imageView;
    protected boolean isDestroy = false;

    public Enemy(){
        coordinate.x = 0;
        coordinate.y = 0;
        mm = 0;
    }

    public Enemy(double x, double y){
        coordinate.x = x*50 - 25;
        coordinate.y = y*50;
        ImageView imageView = new ImageView();
        //System.out.println(coordinate.x+ "    "+coordinate.y);
    }

    public void draw(Group root, GraphicsContext gc){
        if(root.getChildren().contains(imageView)) root.getChildren().remove(imageView);
        imageView.setY(coordinate.y);
        imageView.setX(coordinate.x);
        Point u = findPath(root);
        //ImageView imageView2 = new ImageView();
                                                                                //Xoay địch
            if (u == DELTA_DIRECTION_ARRAY[1]) {
                //if(root.getChildren().contains(imageView))
                //root.getChildren().remove(imageView);
                imageView.setRotate(90);
            } else if (u == DELTA_DIRECTION_ARRAY[3]) {
                //if(root.getChildren().contains(imageView))
                //root.getChildren().remove(imageView);
                imageView.setRotate(0);
            } else if (u == DELTA_DIRECTION_ARRAY[2]) {
                //if(root.getChildren().contains(imageView))
                //root.getChildren().remove(imageView);
                imageView.setRotate(180);
                //imageView.setRotate(90);
            }
        if (!isDestroy) {
            root.getChildren().add(imageView);
        }
        //System.out.println(imageView.getRotate());


        //health bars
        gc.setFill(Color.RED);
    }
        //gc.setFill(Color.BLACK);
        //gc.fillRect(coordinate.getX()-10,coordinate.getY(),50,50);
        //gc.drawImage(image,entity.getX(),entity.getY());

    public void update(Group root){
        if(isDestroy) return;
        if(health <= 0) {
            doDestroy();
            MainController.enemy.remove(this);
            return;
        }
       // System.out.println("hgjhgh");
        Point u = findPath(root);
        if(p.getX() + u.getX() == 0 && p.getY() + u.getY() == 0) u = p;
        else p = u;
        //if(u == null)System.out.print("dfasldjf");
        coordinate.x += u.getX() * speed;
        coordinate.y += u.getY() * speed;
    }
    public boolean Outmap(){
        if (this.coordinate.x > 1000 || this.coordinate.y < 900)
            return true;
        return false;
    }

    protected Point findPath(Group root){
        int col = (int) coordinate.x / Config.TILE_SIZE + 1 ;
        int row = (int) coordinate.y / Config.TILE_SIZE;
        int[][] a = GameStage.Map;
        //System.out.println(a[row][col]);
        if(a[row][col] == 4 && coordinate.x <= 0) {
            //System.out.println("jjkjkhk");
            Target.health --;
            root.getChildren().remove(imageView);
            //root.getChildren().remove(this.imageView);
            MainController.enemy.remove(this);
            MainController.lives--;
            doDestroy();
        }
        if(a[row + 1][col] == 1) {
           // System.out.println(row +"    "+ col);
            return DELTA_DIRECTION_ARRAY[1];   // di xuong
        }
        else {
            if(a[row][col + 1] == 1) {
                return DELTA_DIRECTION_ARRAY[3];  // re phai
            }
            else {
                if(col > 1 && a[row][col - 1] == 1) return DELTA_DIRECTION_ARRAY[2];  // re trai
            }
        }

        return DELTA_DIRECTION_ARRAY[1];
    }

    public void doDestroy() {
        isDestroy = true;
    }

    public boolean isDestroy() {
        return isDestroy;
    }
    public long getHealth() {
        return health;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public void damaged(long strength) {
        health -= strength;
    }

    public long getReward() {
        return reward;
    }
}

/*public class Enemy extends GameEntity {
    private HashSet<Enemy> enemies = new HashSet<Enemy>();
    private int mm;
    public Enemy(double x, double y){
        coordinate.x = x;
        coordinate.y = y;
        mm = 0;
    }
    public void draw(GraphicsContext gc, GameEntity entity){
        gc.setFill(Color.BLACK);
        gc.fillRect(entity.coordinate.getX(),entity.coordinate.getY(),50,50);
        //gc.drawImage(image,entity.getX(),entity.getY());
    }
    public void update(Road road){
        /*if(mm < road.road.size()) {
            coordinate.setX(road.road.get(mm).x);
            coordinate.setY(road.road.get(mm).y);
            mm++;
        }
        coordinate.setX(coordinate.x + 1);
        coordinate.setY(coordinate.y + 1);

    }


    private void findPath(){

    }

    private boolean evaluate(){
        return true;
    }

}*/