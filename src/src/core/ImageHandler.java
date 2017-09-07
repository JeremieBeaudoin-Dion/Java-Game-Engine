package core;

import images.*;
import images.Image;
import images.Rectangle;

import java.awt.*;
import java.util.List;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * This class creates a JFrame instance.
 *
 * It draws the graphics on the frame and handles
 * double buffering.
 *
 * @author Jérémie Beaudoin-Dion
 *
 */
public class ImageHandler extends JFrame {

    // Enables double buffer
    private BufferedImage backBuffer;
    private Insets insets;

    /**
     * Constructor; creates the window and such
     */
    public ImageHandler (){

        // Sets the parameter for the window
        setTitle(Game.GAME_NAME);
        setPreferredSize(new Dimension(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();

        // Makes sure the graphics don't get drawn on the border of the window
        insets = getInsets();
        setSize(insets.left + Game.WINDOW_WIDTH + insets.right,
                insets.top + Game.WINDOW_HEIGHT + insets.bottom);

        // Enables double buffer
        backBuffer = new BufferedImage(Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Called every frame, this method handles graphics
     */
    public void update(List<ImageObject> imageObjects) {

        // Sets the double buffer
        Graphics2D g = (Graphics2D) getGraphics();
        Graphics2D bbg = (Graphics2D) backBuffer.getGraphics();

        // Sets the quality of the image to hight
        setRenderingQuality(bbg);

        // Sets default background
        bbg.setColor(Color.BLACK);
        bbg.fillRect(0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);

        // Draws the physical objects on the frame
        drawFrame(bbg, imageObjects);

        // Draws the whole thing in one shot -- Double buffer
        g.drawImage(backBuffer, insets.left, insets.top, null);

        // Clears the cache
        bbg.dispose();
        g.dispose();

    }

    /**
     * Sets the desired rendering quality of the image
     */
    private void setRenderingQuality(Graphics2D bbg) {
        bbg.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        bbg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        bbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        bbg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /**
     * Distributes the physical objects to the correct private method
     */
    private void drawFrame(Graphics bbg, List<ImageObject> imageObjects) {

        for(ImageObject object : imageObjects) {
            printDisplayableOnBuffer(bbg, object);
        }

    }

    /**
     * Sends the physical object to the correct method in order to be put on screen
     */
    private void printDisplayableOnBuffer(Graphics bbg, ImageObject imageObject) {
        if (imageObject instanceof Text) {
            printText(bbg, (Text) imageObject);

        } else if (imageObject instanceof Image) {
            printImage(bbg, (Image) imageObject);

        } else if (imageObject instanceof Ellipse){
            printEllipse(bbg, (Ellipse) imageObject);

        } else if (imageObject instanceof Rectangle){
            printRectangle(bbg, (Rectangle) imageObject);

        } else if (imageObject instanceof Arc){
            printArc(bbg, (Arc) imageObject);
        }
    }

    private void printImage(Graphics bbg, Image image) {
        bbg.drawImage(image.getImage(), image.getPosition().getX(), image.getPosition().getY(), null);
    }

    private void printText(Graphics bbg, Text text) {
        bbg.setColor(text.getColor());
        bbg.setFont(text.getFont());

        bbg.drawString(text.getMessage(), text.getPosition().getX(), text.getPosition().getY());
    }

    private void printEllipse(Graphics bbg, Ellipse ellipse) {
        bbg.setColor(ellipse.getColor());

        bbg.fillOval(ellipse.getPosition().getX(), ellipse.getPosition().getY(), ellipse.getWidth(), ellipse.getHeight());
    }

    private void printRectangle(Graphics bbg, Rectangle rectangle) {
        bbg.setColor(rectangle.getColor());

        bbg.fillRect(rectangle.getPosition().getX(), rectangle.getPosition().getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    private void printArc(Graphics bbg, Arc arc) {
        bbg.setColor(arc.getColor());

        bbg.drawArc(arc.getPosition().getX(), arc.getPosition().getY(), arc.getWidth(), arc.getHeight(), arc.getStartAngle(), arc.getArcAngle());
    }

    /**
     * Returns the current position of the mouse relative to the
     * screen position.
     *
     * @return the current mouse position with a Position
     */
    public Point getMousePosition() {

        PointerInfo currentMouse = MouseInfo.getPointerInfo();

        int x = (int) (currentMouse.getLocation().getX() - getLocationOnScreen().getX());
        int y = (int) (currentMouse.getLocation().getY() - getLocationOnScreen().getY());

        return new Point(x, y);
    }

    @Override
    public void paint (Graphics g) {}

    @Override
    public void repaint () {}

}

