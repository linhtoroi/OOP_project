package GameEntity;

import Main.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Mountain {
    public List<Point> mountain = new ArrayList<Point>();
    public Mountain(){
        GameStage gs = new GameStage(900,1000);
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 18; j++){
                if (gs.Map[j][i] == 0)
                    mountain.add(new Point(i,j));
            }
    }
    public List<Point> install(){
        GameStage gs = new GameStage(900,1000);
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 18; j++){
                if (gs.Map[j][i] == 0)
                    mountain.add(new Point(i,j));
            }
        return mountain;
    }
}
