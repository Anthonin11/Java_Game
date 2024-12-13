import java.util.ArrayList;

// Classe de gestion des intéractions des éléments de l'environnement et des Sprites statiques et Dynamiques

public class PhysicEngine implements Engine{
    private ArrayList<DynamicSprite> movingSpriteList;
    private ArrayList <Sprite> environment;

    // Initialisation
    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    // Add element to the playground
    public void addToEnvironmentList(Sprite sprite){
        if (!environment.contains(sprite)){
            environment.add(sprite);
        }
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment=environment;
    }

    // Add moving element to the playground
    public void addToMovingSpriteList(DynamicSprite sprite){
        if (!movingSpriteList.contains(sprite)){
            movingSpriteList.add(sprite);
        }
    }

    // Ennemies
    private ArrayList<ComplexEnemy> enemies = new ArrayList<>();

    public void addEnemy(ComplexEnemy enemy) {      
          enemies.add(enemy);
    }

    // Update of ennemies
    @Override
    public void update() {
    // Every moving element move from one step
    for (DynamicSprite dynamicSprite : movingSpriteList) {
        dynamicSprite.moveIfPossible(environment);
    }
    // For the ennemy :
    for (ComplexEnemy enemy : enemies) {
        enemy.update();
        // Check if ennemy and hero are in collision
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            if (dynamicSprite.getHitBox().intersects(enemy.getHitBox())) {
                dynamicSprite.decreaseHealth(5); // -5 points de vie par collision
                enemy.setSpeed(1);
            }
        }
    
    }

}
}

