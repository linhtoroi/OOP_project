package GameEntity;

public class Point {
    public double x, y;
    public Point(){
    }
    public Point(double x,double y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point other){
        double a = x-other.x;
        double b = y-other.y;
        return Math.sqrt(a*a+b*b);
    }
    public void setX(double x) {
            this.x = x;
        }

        public double getX() {
            return x;
        }
        public void setY(double y) {
            this.y = y;
        }
        public double getY() {
            return y;
        }

}
