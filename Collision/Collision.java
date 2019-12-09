package Collision;

//import entity.GameEntity;
//import entity.bullet.Bullet;
//import entity.enemy.Enemy;

import Bullet.Bullet;
import Enemy.Enemy;


public class Collision { //va chạm đạn với địch
    private static final double collision_range = 10;
    public static boolean isCollide(Enemy e1, Bullet e2) {
        if (e1.coordinate.distance(e2.coordinate) < collision_range) return true;
        return false;
    }




}

