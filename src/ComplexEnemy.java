import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


// Complex pattern ennemies
// When hero is not seen they move randomnly
// When hero is seen they move on his direction with moveTowardHero

public class ComplexEnemy extends DynamicSprite {
    private DynamicSprite hero;
    private ArrayList<SolidSprite> obstacles;
    //private boolean chasing = false;

    public ComplexEnemy(double x, double y, Image image, double width, double height, DynamicSprite hero, ArrayList<SolidSprite> obstacles) {
        super(x, y, image, width, height);
        this.hero = hero;
        this.obstacles = obstacles;
    }


    // Check if the hero is visible by the ennemy
    // If there is no intersection between the ennemy the hero and object hitbox then the hero is seen
    private boolean hasLineOfSight() {
        Line2D.Double lineToHero = new Line2D.Double(this.getX(), this.getY(), hero.getX(), hero.getY());
        for (SolidSprite obstacle : obstacles) {
            if (obstacle.getHitBox().intersectsLine(lineToHero)) {
                return false;
            }
        }
        return true;
    }


    // The ennemy move to the hero
    private void moveTowardsHero() {
        if (this.getX() < hero.getX()) 
            setDirection(Direction.EAST);
        else if (this.getX() > hero.getX()) 
            setDirection(Direction.WEST);

        if (this.getY() < hero.getY()) 
            setDirection(Direction.SOUTH);
        else if (this.getY() > hero.getY()) 
            setDirection(Direction.NORTH);

        moveIfPossible(new ArrayList<>(obstacles)); // Cast to ArrayList<Sprite>
    }

    // Random Pattern Ennemies
    // Ennemy walk straight for 30 steps
    private int counterEnemySteps = 0;
    private void moveAtRandom(){
        this.counterEnemySteps= (this.counterEnemySteps +1) % 30 ;
        System.out.println(this.counterEnemySteps);
        
        // then the ennemy walk in an other randomly direction
        if (this.counterEnemySteps  == 0){
            Random rand =  new Random();
            int k = rand.nextInt(4) ;
            Direction D = Arrays.asList(Direction.NORTH,Direction.SOUTH,Direction.EAST,Direction.WEST).get(k);
            setDirection(D);
        }
 
            moveIfPossible(new ArrayList<>(obstacles));


    }
    public void update() {
        if (hasLineOfSight()) {
            //chasing = true;
            moveTowardsHero();
        } else {
            //chasing = false;
            moveAtRandom();
            //chasing = True
        }
    }
}
