package GameEntity;

import GameEntity.Point;
import Main.GameStage;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Point> road = new ArrayList<Point>();
    public Road(){
        GameStage gs = new GameStage(900,1000);
        for (int i = 0; i < 21; i++)
            for (int j = 0; j < 18; j++) {
                if (gs.Map[j][i] == 1)
                    road.add(new Point(i, j));
                System.out.println();
            }
    }
}
