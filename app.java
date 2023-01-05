import java.awt.Color;

public class app {
    public static void main(String[] args) {
        condivisa cond = new condivisa();
        ThreadPaint paint = new ThreadPaint(cond);

        myFrame frame = new myFrame(cond, 1500, 600);

        // speed in pixels per second; angle in degree

        // collisione orizzontale
        /*
         * JBall balls[] = { new JBall(new JCoord(100, 150), 400, Color.BLUE, 20, 0)
         * ,new JBall(new JCoord(500, 150), 300, Color.ORANGE, 20, 180)
         * };
         */

        // Collisione verticale
        /*
         * JBall balls[] = { new JBall(new JCoord(500, 150), 400, Color.BLUE, 20, 90),
         * new JBall(new JCoord(500, 500), 200, Color.ORANGE, 40, 270)
         * };
         */

        // 45 gradi
        /*
         * JBall balls[] = { new JBall(new JCoord(100, 100), 400, Color.BLUE, 20, 45),
         * new JBall(new JCoord(500, 500), 300, Color.ORANGE, 20, 225)
         * };
         */

         JBall balls[] = { new JBall(new JCoord(50, 50), 1000, Color.RED, 50, 0)
            ,new JBall(new JCoord(200, 100), 900, Color.GREEN, 45, 10)
            ,new JBall(new JCoord(350, 150), 800, Color.BLUE, 40, 20)
            ,new JBall(new JCoord(500, 200), 700, Color.ORANGE, 35, 30)
            ,new JBall(new JCoord(650, 250), 600, Color.YELLOW, 30, 40)
            ,new JBall(new JCoord(800, 300), 500, Color.GRAY, 25, 50)
            ,new JBall(new JCoord(950, 350), 400, Color.BLACK, 20, 60)
            ,new JBall(new JCoord(1100, 400), 300, Color.CYAN, 15, 70)
            ,new JBall(new JCoord(1250, 450), 200, Color.MAGENTA, 10, 80)
            ,new JBall(new JCoord(1400, 500), 100, Color.PINK, 5, 90)};


        cond.addBalls(balls);
        cond.addFrame(frame);

        collisionCheck collisionHandler = new collisionCheck(cond);

        paint.start();
        for (JBall ball : cond.balls) {
            ball.start();
        }
        collisionHandler.start();

        for (JBall ball : cond.balls) {
            try {
                ball.join();
            } catch (InterruptedException e) {
                System.out.println("How did we get here");
            }
        }

        try {
            paint.join();
        } catch (InterruptedException e) {
            System.out.println("How did we get here");
        }

        try {
            collisionHandler.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("How did we get here");
        }
    }
}