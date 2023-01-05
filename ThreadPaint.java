public class ThreadPaint extends Thread {
    condivisa cond;

    public ThreadPaint(condivisa cond) {
        this.cond = cond;
    }

    @Override
    public void run() {
        while (true) {
            cond.frame.repaint();

            try {
                sleep(33);
            } catch (InterruptedException e) {
                System.out.println("How did we get here");
            }
        }
    }
}
