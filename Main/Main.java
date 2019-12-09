//luật chơi:
// kéo thả tháp xong khi nào sẵn sàng thì ấn button cuối cùng bên phải để start level
// nút trên là pause game
// để nâng cấp tháp thì click vào tháp và ấn vào cái button cờlê màu xanh ở bên phải, ấn button x màu xanh để bán tháp

package Main;

import Enemy.Enemy;
import GameEntity.*;
import GameEntity.Tower.SniperTower;
import GameEntity.Tower.Tower;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    //private AudioClip jkjk;
    @Override
    public void start(Stage primaryStage) {
        try {

            primaryStage = new GameStage(900,1000);
            final Stage window = primaryStage;

            GameDrawer gameDrawer = new GameDrawer();
            Group menu = new Group();
            ImageView imageViewPlay = gameDrawer.drawMenu(menu);
            Scene menuScene = new Scene(menu);
            primaryStage.setScene(menuScene);


            Parent root1 = FXMLLoader.load(getClass().getResource("Main.fxml"));        // load map
            final Group root = new Group();
            root.getChildren().add(root1);
            final Scene scene = new Scene(root);

            Canvas canvas = new Canvas(1000,900);
            root.getChildren().add(canvas);
            final GraphicsContext gc = canvas.getGraphicsContext2D();
            //Mountain mountain = new Mountain();
            Road road = new Road();
            GameField field = new GameField();

            Label money = new Label();                                                              // khởi tạo tất cả label
            gameDrawer.drawLabelButton(root, money, 1029, 77, 20);
            Label lives = new Label();
            gameDrawer.drawLabelButton(root, lives, 1032, 167, 20);
            Label level = new Label();
            gameDrawer.drawLabelButton(root, level, 1032, 465, 50);

            //lives.setTextAlignment(TextAlignment.CENTER);
            Label mgPrice = new Label();
            gameDrawer.drawLabelButton(root, mgPrice, 1029, 437, 20);
            mgPrice.setText(Config.SNIPER_TOWER_PRICE+"");
            Label sPrice = new Label();
            gameDrawer.drawLabelButton(root, sPrice, 1032, 347, 20);
            sPrice.setText(Config.MACHINE_GUN_TOWER_PRICE+"");
            Label nPrice = new Label();
            gameDrawer.drawLabelButton(root, nPrice, 1032, 257, 20);
            nPrice.setText(Config.NORMAL_TOWER_PRICE+"");
            root.getChildren().addAll(money, lives, nPrice, sPrice, mgPrice, level);

            ImageView start = new ImageView(new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile091.png"));
            start.setFitHeight(Config.BUTTON_SIZE);
            start.setFitWidth(Config.BUTTON_SIZE);
            start.setLayoutX(1000);
            start.setLayoutY(810);
            root.getChildren().add(start);

            ImageView pause = new ImageView(new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile090.png"));
            pause.setFitHeight(Config.BUTTON_SIZE);
            pause.setFitWidth(Config.BUTTON_SIZE);
            pause.setLayoutX(1000);
            pause.setLayoutY(720);
            root.getChildren().add(pause);


            MainController.useDragRelaese(root);               //kéo thả


            final long startNanoTime = System.nanoTime();
            MainController.level();
            MainController.healthlevel = MainController.healthLevels[0];               //truyền máu theo level vào thanh máu của địch hiện tại
            Spawner spawner = new Spawner(3,0);                                 //khởi tạo spaưner
            //spawner.spawn();
            //spawner.update();

            // for playing background MUSIC
           /* final Media bgm = new Media(new File("/Users/apple/IdeaProjects/TowerD/src/AssetsKit_2/Music/DST-TowerDefenseTheme.mp3").toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(bgm);
            mediaPlayer.setVolume(0.3);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();*/

            imageViewPlay.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    window.setScene(scene);                                         // click button de bat dau tro choi
                    MainController.timer.start();
                }
            });
         /*   start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    MainController.timer.start();                                   // click button de bat dau level
                }
            });*/



            Group rootPause = new Group();
            Scene pauseScene = new Scene(rootPause);
            ImageView imageViewReplayP = gameDrawer.winLosePause(rootPause,"PAUSE G");
            pause.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    MainController.timer.stop();                                                                //Click button pause de stop game
                    window.setScene(pauseScene);
                    imageViewReplayP.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            window.setScene(scene);
                            MainController.timer.start();                                //Click button choi tiep
                        }
                    });
                }
            });


            Group rootWin = new Group();
            Scene winScene = new Scene(rootWin);
            ImageView imageViewReplayW = gameDrawer.winLosePause(rootWin,"YOU WIN");
            imageViewReplayW.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {                                 //Click button de choi lai trong scene win
                    MainController.level = Config.LEVEL_DEFAULT;
                    MainController.money = Config.MONEY_DEFAULT;
                    MainController.lives = Config.LIVES_DEFAULT;

                    if (MainController.enemy.size() > 0)
                        for (int i = 0 ; i < MainController.enemy.size(); i++)
                            root.getChildren().remove(MainController.enemy.get(i).imageView);
                    MainController.enemy.clear();

                    if (MainController.towers.size() > 0)
                        for (int i = 0 ; i < MainController.towers.size(); i++) {
                            MainController.mountain.mountain.add(new Point(MainController.towers.get(i).coordinate.x / Config.TILE_SIZE, MainController.towers.get(i).coordinate.y / Config.TILE_SIZE));
                            root.getChildren().remove(MainController.towers.get(i).imageView);
                        }
                    MainController.towers.clear();
                    MainController.levelUp = false;
                    MainController.lose = false;
                    MainController.win = false;
                    //MainController.mountain = new Mountain();
                    window.setScene(scene);
                    MainController.timer.start();
                }
            });

            Group loseRoot = new Group();
            Scene loseScence = new Scene(loseRoot);
            ImageView imageViewReplayL = gameDrawer.winLosePause(loseRoot,"YOULOSE");
            imageViewReplayL.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {                                      //Click button de choi lai tong scence lose
                    MainController.level = Config.LEVEL_DEFAULT;
                    MainController.money = Config.MONEY_DEFAULT;
                    MainController.lives = Config.LIVES_DEFAULT;

                    if (MainController.enemy.size() > 0)
                        for (int i = 0 ; i < MainController.enemy.size(); i++)
                            root.getChildren().remove(MainController.enemy.get(i).imageView);
                    MainController.enemy.clear();

                    if (MainController.towers.size() > 0)
                        for (int i = 0 ; i < MainController.towers.size(); i++) {
                            MainController.mountain.mountain.add(new Point(MainController.towers.get(i).coordinate.x / Config.TILE_SIZE, MainController.towers.get(i).coordinate.y / Config.TILE_SIZE));
                            root.getChildren().remove(MainController.towers.get(i).imageView);
                        }
                    MainController.towers.clear();
                    MainController.levelUp = false;
                    MainController.lose = false;
                    MainController.win = false;
                    //MainController.mountain = new Mountain();
                    window.setScene(scene);
                    MainController.timer.start();
                }
            });


            //primaryStage.setScene(scene);
            MainController.timer = new AnimationTimer()
            {
                public void handle(long currentNanoTime)
                {
                    //jkjk.play();
                    if (MainController.lives == 0){                           //Kiem tra xem lose khong
                        MainController.lose = true;
                        MainController.timer.stop();
                        window.setScene(loseScence);
                    }
                    if(MainController.level == Config.LEVEL && MainController.lives > 0 && MainController.enemy.size() <= 0){             //Kiem tra xem win khong
                        MainController.lose = true;
                        MainController.timer.stop();
                        window.setScene(winScene);
                    }
                    gc.clearRect(0,0,1000,900);
                    //          if(MainController.lives <= 0) mediaPlayer.stop();
                    double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                    //MainController.up(root);
                    MainController.setRadius(root);                                              // tao ban kinh de xem ban kinh va kem theo mua ban thap

                    spawner.update(start);                                                        // spawner ra dich theo level
                    if (MainController.towers.size()>0)                                           // goi ham ban cho thap
                        for (int i = 0; i < MainController.towers.size(); i++){
                            if (MainController.towers.size()>0)
                                MainController.towers.get(i).shoot(gc, root);
                        }
                    //if (MainController.enemy.size() > 0)
                    for (int i = 0; i < MainController.enemy.size(); i++){                    // goi ham ve dich
                        MainController.enemy.get(i).update(root);
                        if (MainController.enemy.size() > 0)
                            MainController.enemy.get(i).draw(root, gc);
                    }
                    level.setText(MainController.level+"");                                       // cap nhat cac label
                    money.setText(MainController.money+"");
                    lives.setText(MainController.lives+"");
                    //MainController.setRadius(root);
                    //MainController.DeleteEnemy();
                }
            };//.start();
            primaryStage.show();


        }


        catch (Exception e) {
            System.out.println("Ex");
            e.printStackTrace();
        }
    }
}




/*Image image = new Image(("/AssetsKit_2/PNG/Retina/towerDefense_tile231.png"));
            ImageView startImage = new ImageView(image);
            startImage.setFitWidth(94);
            startImage.setFitHeight(41);
            Button startButton = new Button("Pause");
            root.getChildren().add(startButton);
            startButton.setLayoutX(1044.0);
            startButton.setLayoutY(377);
            Stage finalPrimaryStage = primaryStage;
            startButton.setOnAction(actionEvent -> {
                StackPane layout = new StackPane();
                Scene scene2 = new Scene(layout, 900, 1000);
                finalPrimaryStage.setScene(scene2);
            });*/
//MainController.hihi(primaryStage);
//MainController.Pause(new ActionEvent(), primaryStage);
/* SniperTower tower1 = new SniperTower(9,3);
            SniperTower tower2 = new SniperTower(2,10);
            MainController.towers.add(tower1);
            MainController.towers.add(tower2);
            root.getChildren().addAll(tower1.imageView, tower2.imageView);*/
//MainController.up(root);



/*
            if (MainController.towers.size()>0){
                SplitPane group = new SplitPane();
                Button upgrade = new Button("Upgrade");
                Button cancel = new Button("Cancel");
                MainController.towers.get(0).imageView.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("hehe");
                        group.getItems().addAll(upgrade,cancel);
                        group.setLayoutX(MainController.towers.get(0).coordinate.x + 50);
                        group.setLayoutY(MainController.towers.get(0).coordinate.y);
                        root.getChildren().add(group);
                    }
                });
                upgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("hihi");
                    }
                });
                cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        System.out.println("haha");
                    }
                });
            }
*/