package Main;

import Enemy.Enemy;
import GameEntity.Config;
import GameEntity.Mountain;
import GameEntity.Point;
import GameEntity.Spawner;
import GameEntity.Tower.MachineGunTower;
import GameEntity.Tower.NormalTower;
import GameEntity.Tower.SniperTower;
import GameEntity.Tower.Tower;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;
import jdk.jshell.Snippet;


import java.awt.*;
import java.util.ArrayList;

public class MainController {
    public static ArrayList<Tower> towers = new ArrayList<Tower>();
    public static ArrayList<Enemy> enemy = new ArrayList<>();
    //public static int countOfClick = 0;
    public static long money = Config.MONEY_DEFAULT;
    public static int lives = Config.LIVES_DEFAULT;
    public static int level = Config.LEVEL_DEFAULT + 1;
    public static boolean levelUp = false;
    public static boolean stop = false;
    public static long[] healthLevels = new long[Config.LEVEL];
    public static long healthlevel;
    public static AnimationTimer timer;
    public static boolean lose = false;
    public static boolean win = false;
    public static Mountain mountain = new Mountain();

    //@FXML
    //ImageView upgrade;
    //@FXML
    //ImageView cancel;
/*
    public static void createEnemy(GameField field, double x, double y) {
        Spawner spawner = new Spawner(x, y);
        spawner.spawn();
        spawner.update();
    }*/

    //hàm tạo tower tại từng vị trí được trọn và truyền vào mảng
    public static void createTower(Group root, double x, double y, int kind) {
        /*if (towers.size() > 0)
            for (int j = 0; j < towers.size(); j++){
                if (towers.get(j).coordinate.x == x && towers.get(j).coordinate.y == y){
                    return;
                }
            }*/
        if (kind == Config.SNIPER_TOWER_KIND && money >= Config.SNIPER_TOWER_PRICE) {
            SniperTower tower2 = new SniperTower(x, y);
            MainController.towers.add(tower2);
            root.getChildren().addAll(tower2.imageView);
            money -= Config.SNIPER_TOWER_PRICE;
        } else if (kind == Config.NORMAL_TOWER_KIND && money >= Config.NORMAL_TOWER_PRICE) {
            NormalTower tower1 = new NormalTower(x, y);
            MainController.towers.add(tower1);
            root.getChildren().add(tower1.imageView);
            money -= Config.NORMAL_TOWER_PRICE;
        } else if (kind == Config.MACHINE_GUN_TOWER_KIND && money >= Config.MACHINE_GUN_TOWER_PRICE) {
            MachineGunTower tower3 = new MachineGunTower(x, y);
            MainController.towers.add(tower3);
            root.getChildren().add(tower3.imageView);
            money -= Config.MACHINE_GUN_TOWER_PRICE;
        }
    }

    //Hàm vẽ và thao tác chuột cho từng tháp và bán kính
    public static void radiusOfTower(Tower tower, Group root) {
        tower.circle.setRadius(tower.getRadius());
        //tower.circle.setFill(Color.BLACK);
        tower.circle.setOpacity(0.3);
        //System.out.println(x+ "   " + y);
        tower.circle.setCenterX(tower.coordinate.x + Config.TILE_SIZE / 2);
        tower.circle.setCenterY(tower.coordinate.y + Config.TILE_SIZE / 2);
        VBox group = new VBox();
        tower.imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().add(tower.circle);
                //System.out.println(0);
            }
        });
        tower.circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                root.getChildren().remove(tower.circle);
                //root.getChildren().remove(group);
                //System.out.println(0);
            }
        });
        tower.circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //if (mouseEvent.isPrimaryButtonDown())
                //buttonOfTower(tower,root,group);
                button(tower, root, group);

            }
        });
    }

    //Action cho các Button nâng cấp và bán súng
    public static void button(Tower tower, Group root, VBox group) {

        ImageView upgrade = new ImageView(new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile043.png"));
        ImageView sell = new ImageView(new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile044.png"));
        sell.setFitHeight(Config.BUTTON_SIZE);
        sell.setFitWidth(Config.BUTTON_SIZE);
        upgrade.setFitHeight(Config.BUTTON_SIZE);
        upgrade.setFitWidth(Config.BUTTON_SIZE);
        group.getChildren().addAll(sell, upgrade);

        group.setLayoutX(1000);
        group.setLayoutY(540);
        root.getChildren().add(group);
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
            }
        });
        upgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {          //Nâng cấp
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
                if (tower.getGrade() < Config.MAX_OF_UPGRADE && money >= tower.getPrice() * Config.UPGRADE_MONEY) {
                    //System.out.println("day truoc");
                    tower.upgrade(tower.getGrade() + 1, root);
                    money -= (long) (tower.getPrice() * Config.UPGRADE_MONEY);
                    tower.setPrice((long) (tower.getPrice() * Config.UPGRADE_MONEY));
                }
                //group.getChildren().removeAll(upgrade,sell);
                root.getChildren().remove(group);
            }
        });
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {        //Bán
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
                //group.getChildren().removeAll(upgrade,sell);
                money += (long) (tower.getPrice() * Config.SELL_MONEY);
                MainController.mountain.mountain.add(new Point(tower.coordinate.x / Config.TILE_SIZE, tower.coordinate.y / Config.TILE_SIZE));
                root.getChildren().remove(tower.imageView);
                towers.remove(tower);
                root.getChildren().remove(group);
            }
        });
    }

    //setRadius cho từng súng
    public static void setRadius(Group root) {
        if (towers.size() > 0) {
            for (int i = 0; i < towers.size(); i++) {
                radiusOfTower(towers.get(i), root);
                //MainController.upgrade(towers.get(i), root);
            }
        }
    }

    public static void up(Group root) {
        if (towers.size() > 0) {
            for (int i = 0; i < towers.size(); i++) {
                //MainController.radiusOfTower(MainController.towers.get(i), root);
                //MainController.upgrade(towers.get(i), root, group);
            }
        }
    }

    //Gọi hàm kép thả cho từng súng
    public static void useDragRelaese(Group root) {
        Image nImage = new Image(("/AssetsKit_2/PNG/Retina/towerDefense_tile249.png"));
        ImageView nomalImage = new ImageView(nImage);
        dragRelease(root, nomalImage, Config.NORMAL_TOWER_KIND);
        Image sImage = new Image(("/AssetsKit_2/PNG/Retina/towerDefense_tile203.png"));
        ImageView sniperImage = new ImageView(sImage);
        dragRelease(root, sniperImage, Config.SNIPER_TOWER_KIND);
        Image mImage = new Image(("/AssetsKit_2/PNG/Retina/towerDefense_tile226.png"));
        ImageView machineGunImage = new ImageView(mImage);
        dragRelease(root, machineGunImage, Config.MACHINE_GUN_TOWER_KIND);
    }



    //kéo thả
    public static void dragRelease(Group root, ImageView imageView, int kind) {

        imageView.setFitWidth(Config.TILE_SIZE);
        imageView.setFitHeight(Config.TILE_SIZE);
        imageView.setX(1020);
        imageView.setY(kind * Config.BUTTON_SIZE + 111);
        root.getChildren().add(imageView);
        imageView.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("10");
                imageView.setY(mouseEvent.getY());
                imageView.setX(mouseEvent.getX());
            }
        });
        imageView.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("20");
                for (int i = 0; i < MainController.mountain.mountain.size(); i++) {
                    if (MainController.mountain.mountain.get(i).x == (int) mouseEvent.getX() / Config.TILE_SIZE && MainController.mountain.mountain.get(i).y == (int) mouseEvent.getY() / Config.TILE_SIZE) {
                        System.out.println(MainController.mountain.mountain.get(i).x + " " + MainController.mountain.mountain.get(i).y + " " + (int) mouseEvent.getX() / Config.TILE_SIZE + " " + (int) mouseEvent.getY() / Config.TILE_SIZE );
                        MainController.createTower(root, (int) mouseEvent.getX() / Config.TILE_SIZE, (int) mouseEvent.getY() / Config.TILE_SIZE, kind);
                        MainController.mountain.mountain.remove(i);        // để không xây 2 tháp đè lên nhau
                            //System.out.println(mouseEvent.getX()+"  "+mouseEvent.getY());
                    }
                }
                //System.out.println(mouseEvent.getX()+"  "+mouseEvent.getY());
                imageView.setX(1020);
                imageView.setY(kind * Config.BUTTON_SIZE + 111);
            }
        });
    }

    //Khởi tạo healthLevels[]
    public static void level() {
        for (int i = 0; i < Config.LEVEL; i++){
            healthLevels[i] = (long)(Config.HEALTH_LEVEL_1*(i+1.2));
        }
    }
/*
    public static ImageView pause(ImageView imageView, ImageView imageView2, Stage stage, GameDrawer gameDrawer){
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainController.timer.stop();
                Group rootPause = new Group();
                ImageView imageViewReplayP = gameDrawer.winLosePause(rootPause,"GAME \n PAUSE");
                rootPause.getChildren().add(imageView);
                Scene pauseScence = new Scene(rootPause);
                stage.setScene(pauseScence);
                imageViewReplayP.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        MainController.timer.start();
                    }
                });
            }
        });
        return im

    }
*/


}

/*
    @FXML
    Button start;
    @FXML
    public static Button pause;

    public void Start(ActionEvent ac){
        Spawner spawner = new Spawner(3, 0);
        spawner.spawn();
        spawner.update();
    }

    /*public static void Pause(ActionEvent ac, Stage stage){
        pause.setOnAction(actionEvent -> {
            StackPane layout = new StackPane();
            Scene scene = new Scene(layout, 900, 1000);
            stage.setScene(scene);
        });
    }

    @FXML
    Label moneyText;

    public void setMoneyText(){
        //moneyText = new TextField();
        moneyText = new Label();
        moneyText.setText(money+"");
    }
}
*/

//Button ban dau viet de nang cap va ban sung
/* public static void buttonOfTower(Tower tower, Group root, VBox group){

        Button upgrade = new Button("Upgrade");
        Button sell = new Button("Sell");
        Button cancel = new Button("Cancel");
        //if (countOfClick == 0) {
            //
        //System.out.println("hjhsjkdafhsjdfh");
        group.getChildren().addAll(upgrade,sell, cancel);

        group.setLayoutX(tower.coordinate.x + 50);
        group.setLayoutY(tower.coordinate.y);
        root.getChildren().add(group);
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
            }
        });
        upgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
                if (tower.getGrade() < 2) {
                    System.out.println("day truoc");
                    tower.upgrade(tower.getGrade() + 1, root);

                }
                //group.getChildren().removeAll(upgrade,sell);
                root.getChildren().remove(group);
            }
        });
        sell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
                //group.getChildren().removeAll(upgrade,sell);
                money += 50;
                root.getChildren().remove(tower.imageView);
                towers.remove(tower);
                root.getChildren().remove(group);
            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //System.out.println("hihi");
                //group.getChildren().removeAll(upgrade,sell);
                root.getChildren().remove(group);
            }
        });
        /*tower.circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.isSecondaryButtonDown()) {
                    System.out.println("hihi");
                    //group.getChildren().removeAll(upgrade,sell);
                    root.getChildren().remove(group);
                }
            }
        });
    */

//Ham xoa Enemy ban dau
 /*
    public static void DeleteEnemy(){
        if (enemy.size() != 0)
            for (int i = 0; i < enemy.size(); i++){
                if (enemy.get(i).Outmap())
                   enemy.remove(i);
            }
    }*/