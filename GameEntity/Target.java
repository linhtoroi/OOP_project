package GameEntity;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 12/1/19
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Target extends GameEntity {
    public static long health = 100;
    public Target(double x, double y) {
        coordinate.setX(x);
        coordinate.setY(y);
    }

    public void draw(GraphicsContext gc){};

    public static void drawHealthbar(GraphicsContext gc) {      //vẽ healthbar của địch

        gc.setFill(Color.RED);
        gc.fillText("HEALTH: ",800, 20);
        gc.fillRect(900,0 ,200*health/100, 20);     // 200 IS BAR LENGTH  50 IS BAR THICKNESS
    }

    public void update() {

    }
}