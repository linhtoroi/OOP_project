package Enemy;

import GameEntity.Config;
import GameEntity.Point;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 12/2/19
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NormalEnemy extends Enemy{
    public NormalEnemy(double x, double y) {
        super(x, y);
        health = Config.NORMAL_ENEMY_HEALTH;
        armor = Config.NORMAL_ENEMY_ARMOR;
        speed = Config.NORMAL_ENEMY_SPEED;
        reward = Config.NORMAL_ENEMY_REWARD;
        image = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile246.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
    }
    @Override
    public void draw(Group root, GraphicsContext gc) {
        super.draw(root, gc);
        gc.fillRect(coordinate.getX(),coordinate.getY() - 2,10*health/20, 2);
    }
}