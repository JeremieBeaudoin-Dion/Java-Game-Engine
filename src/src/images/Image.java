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

	public Image(Dimension dimension, int depth, BufferedImage image) {
		super(dimension, depth);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}
	
}
