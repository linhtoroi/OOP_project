package Main;

import GameEntity.GameEntity;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 11/9/19
 * Time: 11:10 AM
 * To change this template use File | Settings | File Templates.
 */
public interface EntityDrawer {
    void draw(GraphicsContext gc, GameEntity entity);
}
