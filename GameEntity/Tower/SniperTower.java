package GameEntity.Tower;

import Bullet.Bullet;
import GameEntity.Config;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SniperTower extends Tower {

    public SniperTower(double x, double y){
        super(x,y);
        radius = Config.SNIPER_TOWER_RANGE;
        speed = Config.SNIPER_TOWER_SPEED;
        image = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile203.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(Config.TILE_SIZE);
        imageView.setFitWidth(Config.TILE_SIZE);
        imageView.setY(y*Config.TILE_SIZE);
        imageView.setX(x*Config.TILE_SIZE);
        imageBullet = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile274.png");
        bullet = new Bullet(x*Config.TILE_SIZE, y*Config.TILE_SIZE, imageBullet);
        price = Config.SNIPER_TOWER_PRICE;
    }

    @Override
    public void shoot(GraphicsContext gc, Group root) {
        if (countSpeed < speed){
            super.shoot(gc, root);
            countSpeed++;
        }
        else {
            countSpeed = 0;
        }

    }

    @Override
    public void upgrade(int newGrade, Group root){
        speed += Config.UPGRADE_SPEED*Config.SNIPER_TOWER_KIND;
        radius += Config.UPGRADE_RANGE*Config.SNIPER_TOWER_KIND;
        if (newGrade == 1){
            image = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile204.png");
            root.getChildren().remove(imageView);
            imageView = new ImageView(image);
            imageView.setFitHeight(Config.TILE_SIZE);
            imageView.setFitWidth(Config.TILE_SIZE);
            imageView.setY(coordinate.y);
            imageView.setX(coordinate.x);
            root.getChildren().add(imageView);
            this.grade = newGrade;
            System.out.println("day");
        }
        else if (newGrade == 2){
            image = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile205.png");
            root.getChildren().remove(imageView);
            imageView = new ImageView(image);
            imageView.setFitHeight(Config.TILE_SIZE);
            imageView.setFitWidth(Config.TILE_SIZE);
            imageView.setY(coordinate.y);
            imageView.setX(coordinate.x);
            root.getChildren().add(imageView);
            this.grade = newGrade;
            System.out.println("day");
        }
    }
}


//if (bullet.isDestroyed() == false)

/*long startNanoTime = System.nanoTime();
        int i = 0;
        while(i < 10){
            if (System.nanoTime() - startNanoTime > 1000000000.0) {
                startNanoTime = System.nanoTime();
                bullet.get(i).update(gc);
                i++;
            }
        }*/
 /*
        if (target != null) {

            //phuong trinh duong thang giua dan va dich
            double deltaX = target.coordinate.x - coordinate.x;
            double deltaY = target.coordinate.y - coordinate.y;


            //tinh goc giua duong thang noi quan dich vs thap va ox
            double X = coordinate.y + (-coordinate.x / deltaX) * deltaY;
            double Y = coordinate.x + (-coordinate.y / deltaY) * deltaX;
            double angle = Math.atan(Y / X) / (Math.PI) * 180;
            if (target.coordinate.y > coordinate.y) {
                angle += 180;
            }
            imageView.setRotate(angle);
         */