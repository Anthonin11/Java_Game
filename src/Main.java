import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;

    public Main() throws Exception{
        // Creation of the displayed frame :
        displayZoneFrame = new JFrame("Java Labs");
        // Adjusting frame size, in pixels :
        displayZoneFrame.setSize(590,610);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Creation of the hero
        DynamicSprite hero = new DynamicSprite(200, 300,
        ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50);
        hero.setSpeed(10);


        // Engine and timer of the game
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        // Frame is visible :
        displayZoneFrame.setVisible(true);

        // Zone of the game
        Playground level = new Playground("./data/level2.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0)
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
        
        ArrayList<SolidSprite> solidSprites = new ArrayList<>();
        for (Sprite sprite : level.getSolidSpriteList()) {
        if (sprite instanceof SolidSprite) {
            solidSprites.add((SolidSprite) sprite);
        }
        }

        // Loop that generate ennemies
        for (int nb_enemy = 1; nb_enemy<=4 ; nb_enemy++){ 
        ComplexEnemy enemy = new ComplexEnemy(70+100*nb_enemy, 70+100*nb_enemy,
            ImageIO.read(new File("./img/enemyTileSheetLowRes.png")), 48, 50, hero, solidSprites);
            enemy.setSpeed(5);
        physicEngine.addEnemy(enemy);
        
        renderEngine.addToRenderList(enemy);}
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}

