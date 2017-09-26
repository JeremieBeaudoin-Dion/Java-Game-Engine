package images;

import java.awt.*;

/**
 * A text is a String which can be show on the frame
 *
 * @author Jérémie Beaudoin-Dion
 */
public class Text extends ImageObject {
	
	private String message;
	private Font font;
	private Color color;

	public Text(Position position, String message, Font font, Color color, int depth){
		super(new Dimension(position, font.getSize() * message.length(), font.getSize()), depth);
		this.message = message;
		this.font = font;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public Font getFont() {
		return font;
	}
	
	public Color getColor() {
		return color;
	}

}

