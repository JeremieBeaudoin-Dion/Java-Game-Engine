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
	private String alignment;
	private Color color;
	
	/**
	 * Constructor
	 * 
	 * @param position
	 * @param message
	 * @param font
	 * @param alignment: "Right", "Left", "Center"
	 * @param color
	 */
	public Text(Position position, String message, Font font, String alignment, Color color){
		super(position, font.getSize() * message.length(), font.getSize());
		this.message = message;
		this.font = font;
		this.alignment = alignment;
		this.color = color;
	}

	public String getMessage() {
		return message;
	}

	public Font getFont() {
		return font;
	}

	public String getAlignment() {
		return alignment;
	}
	
	public Color getColor() {
		return color;
	}

}

