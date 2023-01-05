import java.awt.*;
import javax.swing.JFrame;

public class myFrame extends JFrame {
    condivisa cond;

    private Image offScreenImageDrawed;
    private Graphics offScreenGraphicsDrawed;

    public myFrame(condivisa cond, int width, int height) {
        this.cond = cond;
        this.setSize(width, height);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.offScreenImageDrawed = null;
        this.offScreenGraphicsDrawed = null;
    }

    @Override
    public void paint(Graphics g) {
        final Dimension d = getSize();
        offScreenImageDrawed = createImage(d.width, d.height);

        this.offScreenGraphicsDrawed = offScreenImageDrawed.getGraphics();
        this.offScreenGraphicsDrawed.setColor(Color.white);
        this.offScreenGraphicsDrawed.fillRect(0, 0, d.width, d.height);
        /////////////////////
        // Paint Offscreen //
        /////////////////////
        renderOffScreen(offScreenImageDrawed.getGraphics());
        g.drawImage(offScreenImageDrawed, 0, 0, null);
    }

    public void renderOffScreen(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (JBall ball : this.cond.balls) {
            g2d.setColor(ball.getColor());
            g2d.fillOval((int) ball.getX()  - ball.getRadius(), (int) ball.getY() - ball.getRadius(), ball.getRadius() * 2, ball.getRadius() * 2);
        }
    }
}
