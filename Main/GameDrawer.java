package Main;

//import entity.GameEntity;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Created with IntelliJ IDEA.
 * User: Incognito
 * Date: 11/9/19
 * Time: 11:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class GameDrawer {
    private GraphicsContext gc;
    private GameField field;
    /*public GameDrawer(GraphicsContext gc, GameField field){
        this.gc = gc;
        this.field = field;
    }*/


    //Vẽ các kiểu label, scene mới và button


    public ImageView drawMenu(Group menu){
        Image imageMenu = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile042.png");
        ImageView imageViewMenu = new ImageView(imageMenu);
        imageViewMenu.setFitWidth(900);
        imageViewMenu.setFitHeight(900);

        Label labelMenu = new Label();
        labelMenu.setLayoutX(195);
        labelMenu.setLayoutY(150);
        labelMenu.setFont(Font.font("Verdana", FontWeight.BOLD,100));
        labelMenu.setText("TOWER\nDEFENSE");
        //labelMenu.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY)));
        //money.setBorder(new Border(new BorderStroke()));
        labelMenu.setTextFill(Color.WHITE);
        labelMenu.setTextAlignment(TextAlignment.CENTER);

        //Button playb = new Button();
        Image play = new Image("/AssetsKit_2/PNG/Retina/multimedia.png");
        ImageView imageViewPlay = new ImageView(play);
        imageViewPlay.setFitWidth(200);
        imageViewPlay.setFitHeight(200);
        imageViewPlay.setX(350);
        imageViewPlay.setY(600);

/*      Image cancel = new Image("/AssetsKit_2/PNG/Retina/cancel.png");
        ImageView imageViewCancel = new ImageView(cancel);
        imageViewCancel.setFitWidth(200);
        imageViewCancel.setFitHeight(200);
        imageViewCancel.setX(550);
        imageViewCancel.setY(600);*/

        menu.getChildren().addAll(imageViewMenu, labelMenu, imageViewPlay);
        return imageViewPlay;
    }

    public void drawLabelButton(Group root, Label label, double x, double y, int fontSize){
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(Font.font(null, fontSize));
        //money.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY)));
        //money.setBorder(new Border(new BorderStroke()));
        label.setTextFill(Color.WHITE);
        label.setTextAlignment(TextAlignment.CENTER);
    }

    public ImageView winLosePause(Group root, String winOrLose){
        Image imageWin = new Image("/AssetsKit_2/PNG/Retina/towerDefense_tile042.png");
        ImageView imageViewWin = new ImageView(imageWin);
        imageViewWin.setFitWidth(900);
        imageViewWin.setFitHeight(900);

        Label labelWin = new Label();
        labelWin.setLayoutX(195);
        labelWin.setLayoutY(150);
        labelWin.setFont(Font.font("Verdana", FontWeight.BOLD,100));
        labelWin.setText(winOrLose);
        labelWin.setTextFill(Color.WHITE);
        labelWin.setTextAlignment(TextAlignment.CENTER);

        Image replay = new Image("/AssetsKit_2/PNG/Retina/multimedia.png");
        ImageView imageViewReplay = new ImageView(replay);
        imageViewReplay.setFitWidth(200);
        imageViewReplay.setFitHeight(200);
        imageViewReplay.setX(350);
        imageViewReplay.setY(600);

        root.getChildren().addAll(imageViewWin, labelWin, imageViewReplay);
        return imageViewReplay;
    }

//    public void draw(){
//        for(GameEntity e : field.entities) e.draw(gc,e);
//    }
}
