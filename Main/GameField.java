package Main;

import GameEntity.GameEntity;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 11/8/19
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameField {
    public HashSet<GameEntity> entities = new HashSet<GameEntity>();

    public void addEntity(GameEntity entity){
        entities.add(entity);
    }

    public void removeEntity(GameEntity entity){
        entities.remove(entity);
    }



}