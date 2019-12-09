package GameEntity.Tower;

import Bullet.Bullet;
import Enemy.Enemy;
import GameEntity.GameEntity;
import GameEntity.Config;
import Main.MainController;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower extends GameEntity {
    protected double radius;
    protected double speed;
    protected Bullet bullet;
    protected Enemy target;
    public ImageView imageView = new ImageView();
    public Image imageBullet;
    public Circle circle= new Circle();
    protected int countSpeed = 0;
    protected int grade;
    protected long price;

    //public Circle circle;

    public Tower(double x, double y){
        grade = 0;
        circle = new Circle();
        coordinate.x = x * Config.TILE_SIZE;
        coordinate.y = y * Config.TILE_SIZE;
    }

    public void findEnemy(Image imageBullet){
        //MainController.enemy.get(0).coordinate.distance(coordinate);
        if (MainController.enemy.size() > 0) {                                  //Tìm địch gần nhất
            target = MainController.enemy.get(0);
            bullet.setTarget(target);
            double min = MainController.enemy.get(0).coordinate.distance(coordinate);
            //if (target.coordinate.distance(coordinate) > radius) {
            for (int i = 0; i < MainController.enemy.size(); i++) {
                if (MainController.enemy.get(i).coordinate.distance(coordinate) < min) {
                    min = MainController.enemy.get(i).coordinate.distance(coordinate);
                    target = MainController.enemy.get(i);
                }
            }
            if (min <= radius) {
                bullet.setTarget(target);
            }
            //}

            if (bullet.isDestroyed() == true && (target.coordinate.distance(this.coordinate) < radius)) {       //Nếu đạn đã destroy thì khởi tạo đối tượng mới cho chính nó
                bullet = new Bullet(coordinate.x, coordinate.y, imageBullet);
                //Config.bulletSFX.play();
            }
        }
    }

    public void shoot(GraphicsContext gc, Group root) {             // Xoay tháp theo địch
        findEnemy(imageBullet);
        if (target!= null){

            double deltaX = target.coordinate.x - coordinate.x;
            double deltaY = target.coordinate.y - coordinate.y;

            double X = coordinate.y + (-coordinate.x / deltaX) * deltaY;
            double Y = coordinate.x + (-coordinate.y / deltaY) * deltaX;
            double angle = Math.atan(Y / X) / (Math.PI) * 180;
            if (target.coordinate.y > coordinate.y) {
                angle += 180;
            }
            imageView.setRotate(angle);
        }
        if (target!=null)
            if (target.coordinate.distance(this.coordinate) < radius)
                bullet.update(gc, root);
    }

    abstract public void upgrade(int grade, Group root);

    public double getRadius() {
        return radius;
    }

    public int getGrade() {
        return grade;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}



/*int i = 1;
            if (target.coordinate.x > coordinate.x || target.coordinate.y > coordinate.y) {
                target = MainController.enemy.get(i);
                i++;
                bullet.setTarget(target);
            }
         */

//System.out.println(bullet.coordinate.x +" "+ bullet.coordinate.y);



        /*for (int i = 0; i< 10; i++)
            bullet.get(i).setTarget(target);*/
        /*double deltaX = target.coordinate.x - this.coordinate.x;
            double deltaY = target.coordinate.y - this.coordinate.y;
            double tata = deltaX / Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
            double degrees = Math.acos(tata);
            degrees = Math.toDegrees(degrees);
            System.out.println(deltaX + "   " + deltaY + "       " + tata + "        " + degrees);
            imageView.setRotate(degrees-270);*/