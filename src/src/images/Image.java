package images;

import java.awt.image.BufferedImage;

/**
 * An image is a physical object which can be put on the JFrame
 * 
 * @author Jérémie Beaudoin-Dion
 *
 */
public class Image extends ImageObject {

    private BufferedImage image;
	
	/**
	 * Constructor
	 * 
	 * @param position: where the image is on screen
	 * @param width: the width of the image
	 * @param height: the height of the image
     * @param image: what is the BufferedImage to show
	 */
	public Image(Position position, int width, int height, BufferedImage image) {
		super(position, width, height);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
