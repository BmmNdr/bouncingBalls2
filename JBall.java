import java.awt.Color;

public class JBall extends Thread {
    public JCoord position;
    public JCoord velocity; // or direction
    private int speed; // in pixel per second

    private Color color;
    private int radius; // in pixels

    private boolean stop = false;

    public JBall(JCoord position, int speed, Color color, int radius, int angle) {
        this.position = position;
        this.speed = speed;
        this.color = color;
        this.radius = radius;

        double a = Math.toRadians(angle);
        velocity = new JCoord(Math.cos(a), Math.sin(a));
    }

    

    public Color getColor() {
        return this.color;
    }

    public double getX() {
        return this.position.getX();
    }

    public int getRadius() {
        return this.radius;
    }

    public double getY() {
        return this.position.getY();
    }

    @Override
    public void run() {

        while (true) {
            if(stop)
                continue;

            position.setX(position.getX() + (velocity.getX()));
            position.setY(position.getY() + (velocity.getY()));

            try {
                sleep(1000 / speed); //in questo modo le palline si muovono sempre di 1 pixel
            } catch (InterruptedException e) {
                System.out.println("How did we get here");
            }
        }

    }

    public double getAngle() {
        return Math.atan2(velocity.getY(), velocity.getX());
    }

    public double getV() {
        return Math.sqrt(Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2));
    }



    public void Stop() {
        stop = !stop;
    }

    
}
