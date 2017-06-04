/**
 * Component type: Server
 * Purpose: Information about types of Items.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

public enum Items {
	// declare constants of enum type
	BLUE(0),
	RED(1),
	GREEN(2),
	BLACK(3),
	YELLOW(4),
	PINK(5),
	ORANGE(6),
	NONE(7); // no exist any item
	
	// instance field
	private final int color;
	
	// constructor
	Items(int color) {
		this.color = color;
	}
	
	public int getItem() {
		return color;
	}
	
	public static Items getItem(int colorIndex) {
		if (colorIndex == 0)
			return BLUE;
		else if (colorIndex == 1)
			return RED;
		else if (colorIndex == 2)
			return GREEN;
		else if (colorIndex == 3)
			return BLACK;
		else if (colorIndex == 4)
			return YELLOW;
		else if (colorIndex == 5)
			return PINK;
		else if (colorIndex == 6)
			return ORANGE;
		else
			return NONE;
	}
} // end enum Items