package GameEntity;

import Enemy.Enemy;
import Enemy.NormalEnemy;
import Enemy.SmallerEnemy;
import Enemy.TankerEnemy;
import Enemy.BossEnemy;
import Main.GameField;
import Main.Main;
import Main.MainController;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 11/30/19
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Spawner extends GameEntity {
    private float count_down = 50;
    private float time_between_enemy = 20;
    private float time_between_level = 2000000000;
    private float countt;
    private int waves = 0;
    private boolean stillthislevel = true;

    //private int waves = 0;
    //private int enemy_per_wave = 6;


    public Spawner(double x, double y) {
        coordinate.setX(x);
        coordinate.setY(y);
        countt = 0;
    }
    public void update(ImageView start){//GameField field){             //update để spawner theo level
        count_down--;

        if (count_down <= 0){
            spawn();
            //System.out.println("level"+MainController.level);
            if (MainController.levelUp)
            {
                count_down = time_between_level;
                if (MainController.enemy.size() <= 0)
                    MainController.timer.stop();
                start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (MainController.level < 9) {
                            MainController.timer.start();
                            MainController.levelUp = false;
                            MainController.level++;
                            MainController.healthlevel = MainController.healthLevels[MainController.level - 1];
                            count_down = 0;
                        }
                    }
                });

            }
            else {
                count_down = time_between_enemy;
            }
        }
    }


    public void spawn(){//GameField field){
//        int X = (int)coordinate.x/25;
//        double x = (double)X * 25 + Math.random()*25 + 1 ;
        double x = coordinate.x;
        double y = coordinate.y;
        int randomHealth = (int)(Math.random() * 4) + 1;            //random địch theo lượng máu cho ơr từng level
        //System.out.println(MainController.healthlevel);
        //field.entities.add(new NormalEnemy(x,y));
        if (randomHealth == 1 && MainController.healthlevel >= Config.SMALLER_ENEMY_HEALTH) {
            MainController.enemy.add(new SmallerEnemy(x, y));
            MainController.healthlevel -= Config.SMALLER_ENEMY_HEALTH;
        }
        else if (randomHealth == 2 &&  MainController.healthlevel >= Config.NORMAL_ENEMY_HEALTH) {
            MainController.enemy.add(new NormalEnemy(x, y));
            MainController.healthlevel -= Config.NORMAL_ENEMY_HEALTH;
        }
        else if (randomHealth == 3 &&  MainController.healthlevel >= Config.TANKER_ENEMY_HEALTH) {
            MainController.enemy.add(new TankerEnemy(x, y));
            MainController.healthlevel -= Config.TANKER_ENEMY_HEALTH;
        }
        else if (randomHealth == 4 &&  MainController.healthlevel >= Config.BOSS_ENEMY_HEALTH) {
            MainController.enemy.add(new BossEnemy(x, y));
            MainController.healthlevel -= Config.BOSS_ENEMY_HEALTH;
        }
        else {
           // System.out.println("hihi");
            MainController.levelUp = true;
            stillthislevel = false;
        }
        //}
    }

    public void draw(GraphicsContext gc) {

    }
}
/* public void update(){
        count_down--;
        if(count_down <= 0 && countt < 10) {
            spawn();
            count_down = time_between_wave;
            countt++;
        }
    }*/