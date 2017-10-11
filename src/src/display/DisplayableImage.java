package display;

import physicalObjects.Position;

import java.awt.*;

/**
 * Displays the desired image on screen
 *
 * @author Jérémie Beaudoin-Dion
 */
public class DisplayableImage extends Displayable {

    private Image image;

    /**
     * Constructors
     */
    public DisplayableImage(Position position, Depth depth, Image image) {
        super(position, depth);
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

}
