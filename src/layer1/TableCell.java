/**
 * Component type: Server
 * Purpose: represent each cell of the table that contains
 * items user specifies at the beginning and each cell of 
 * the table that items will be moves to.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

public class TableCell {
	private Items item;
	
	public TableCell() {
		this(Items.NONE);
	}
	
	public TableCell(Items item) {
		this.item = item;
	}
	
	public Items getItem() {
		return this.item;
	}
	
	public void setItem(Items item) {
		this.item = item;
	}
	
	public String toString() {
		switch (item) {
		case BLUE:
			return "Blue";
		case RED:
			return "Red";
		case GREEN:
			return "Green";
		case BLACK:
			return "Black";
		case YELLOW:
			return "Yellow";
		case PINK:
			return "Pink";
		case ORANGE:
			return "Orange";
		case NONE:
			return "none";
		}
		
		return null;
	}
} // end class TableCell