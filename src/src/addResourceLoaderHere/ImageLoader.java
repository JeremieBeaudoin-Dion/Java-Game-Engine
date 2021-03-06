package addResourceLoaderHere;

import jGameFramework.core.LoaderOfImages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads images in order to be displayed on screen
 *
 * This class is necessary for the JGame Framework to work.
 * It can load as many images as needed.
 *
 * @author Jérémie Beaudoin-Dion
 */
public class ImageLoader extends LoaderOfImages {

    private BufferedImage imageExample;

    /**
     * Basic constructor
     * @throws IOException if an image is missing
     */
    public ImageLoader() throws IOException {

        loadAllImages();

    }

    /**
     * Loads all image from file
     *
     * Do remember to use toCompatibleImage() which greatly increases the performance
     * of jGameFramework.display.
     *
     * @throws IOException : if the image is missing
     */
    //Static?
    private void loadAllImages() throws IOException {

        // Example of use
        imageExample = toCompatibleImage(ImageIO.read(new File("ImageExample.png")));

    }

    public BufferedImage getImageExample() {
        return imageExample;
    }

}
