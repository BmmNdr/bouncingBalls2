public class collisionCheck extends Thread {

    condivisa cond;

    public collisionCheck(condivisa cond) {
        this.cond = cond;
    }

    @Override
    public void run() {
        while(true){
            for(int i = 0; i < cond.balls.length - 1; i++){
                for(int j  = i + 1; j < cond.balls.length; j++){
                    if(collideWith(cond.balls[j], cond.balls[i]))
                        resolveCollision(cond.balls[j], cond.balls[i]);
                }
                wallCollision(cond.balls[i]);
            }
    
            wallCollision(cond.balls[cond.balls.length - 1]);
        }
    }

    private void wallCollision(JBall ball) {
        if (ball.getX() - ball.getRadius() <= 0 || ball.getX() + ball.getRadius() >= cond.frame.getWidth())
            ball.velocity.setX(ball.velocity.getX() * -1);

        if (ball.getY() - ball.getRadius() <= 0 || ball.getY() + ball.getRadius() >= cond.frame.getHeight())
            ball.velocity.setY(ball.velocity.getY() * -1);

        if (ball.getY() + ball.getRadius() > cond.frame.getHeight())
            ball.position.setY(cond.frame.getHeight() - ball.getRadius());
        else if (ball.getY() - ball.getRadius() < 0)
            ball.position.setY(ball.getRadius());

        if (ball.getX() + ball.getRadius() > cond.frame.getWidth())
            ball.position.setX(cond.frame.getWidth() - ball.getRadius());
        else if (ball.getX() - ball.getRadius() < 0)
            ball.position.setX(ball.getRadius());
    }

    private boolean collideWith(JBall ball, JBall ball2) {
        return ball2.position.distance(ball.position) < ball2.getRadius() + ball.getRadius();
    }

    private void resolveCollision(JBall ball, JBall ball2) {
        // https://en.wikipedia.org/wiki/Elastic_collision#Two-dimensional_collision_with_two_moving_objects

        // Angoli di movimento
        double thisAngle = ball2.getAngle();
        double otherAngle = ball.getAngle();

        // Angolo di contatto
        double distanceAngle = Math.atan2(ball.getY() - ball2.getY(), ball.getX() - ball2.getX());

        // "Massa" dei due oggetti
        double thisMass = Math.pow(ball2.getRadius(), 3);
        double otherMass = Math.pow(ball.getRadius(), 3);

        // Misura Scalare delle velocità iniziali
        double thisV = ball2.getV();
        double otherV = ball.getV();

        // Elastic Collision Formula:
        ball2.velocity.setX((thisV * Math.cos(thisAngle - distanceAngle) * (thisMass - otherMass)
                + 2 * otherMass * otherV * Math.cos(otherAngle - distanceAngle)) / (thisMass + otherMass)
                * Math.cos(distanceAngle)
                + thisV * Math.sin(thisAngle - distanceAngle) * Math.cos(distanceAngle + Math.PI / 2));

        ball2.velocity.setY((thisV * Math.cos(thisAngle - distanceAngle) * (thisMass - otherMass)
                + 2 * otherMass * otherV * Math.cos(otherAngle - distanceAngle)) / (thisMass + otherMass)
                * Math.sin(distanceAngle)
                + thisV * Math.sin(thisAngle - distanceAngle) * Math.sin(distanceAngle + Math.PI / 2));

        ball.velocity.setX((otherV * Math.cos(otherAngle - distanceAngle) * (otherMass - thisMass)
                + 2 * thisMass * thisV * Math.cos(thisAngle - distanceAngle)) / (thisMass + otherMass)
                * Math.cos(distanceAngle)
                + otherV * Math.sin(otherAngle - distanceAngle) * Math.cos(distanceAngle + Math.PI / 2));

        ball.velocity.setY((otherV * Math.cos(otherAngle - distanceAngle) * (otherMass - thisMass)
                + 2 * thisMass * thisV * Math.cos(thisAngle - distanceAngle)) / (thisMass + otherMass)
                * Math.sin(distanceAngle)
                + otherV * Math.sin(otherAngle - distanceAngle) * Math.sin(distanceAngle + Math.PI / 2));

        resolveOverlap(ball, ball2);
    }

    private void resolveOverlap(JBall ball, JBall ball2) {

        //ball.Stop();
        //ball2.Stop();

        while(ball2.position.distance(ball.position) < ball2.getRadius() + ball.getRadius()){
            double d = ball2.position.distance(ball.position);
            double overlap = ball2.getRadius() + ball.getRadius() - d;
            JBall smallerBall = ball2.getRadius() <= ball.getRadius() ? ball2 : ball;
            JBall biggerBall = ball2.getRadius() > ball.getRadius() ? ball2 : ball;

            double theta = Math.atan2(biggerBall.getY() - smallerBall.getY(), biggerBall.getX() - smallerBall.getX());

            smallerBall.position.setX(smallerBall.getX() - overlap * Math.cos(theta));
            smallerBall.position.setY(smallerBall.getY() - overlap * Math.sin(theta));
        }


        /*try {
            sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ball.Stop();
        ball2.Stop();*/
    }
}
