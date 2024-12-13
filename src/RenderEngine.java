import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class RenderEngine extends JPanel implements Engine{
    private ArrayList<Displayable> renderList;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void addToRenderList(Displayable displayable){
        if (!renderList.contains(displayable)){
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable){
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    @Override
    public void paint(Graphics g) {
    super.paint(g);

    // Display objects
    for (Displayable renderObject : renderList) {
        renderObject.draw(g);
    }

    // Display health
    DynamicSprite hero = findHero(); // Hero is unique
    if (hero != null) {
        int health = hero.getHealth();
        g.setColor(Color.RED);
        g.fillRect(10, 10, health * 2, 20); // Health size (link proportionnaly to the player health)
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 200, 20);
    }
    }

    // Object of the hero
    private DynamicSprite findHero() {
    for (Displayable obj : renderList) {
        if (obj instanceof DynamicSprite) {
            return (DynamicSprite) obj;
        }
    }
    return null;
    }
    @Override
    public void update(){
        this.repaint();
    }
}

