/**
 * This is the interface that represents operations of
 * the robot polymophically.
 * 
 * @author Tran Xuan Hoang
 */
package layer1;

public interface Operation {
	/**
	 * Make the robot's hand 'rbh' executes the operation ont
	 * the 'table'
	 * @param table the table that contains items the robot
	 *        will move
	 * @param rbh the object that represents the information
	 * about the robot's hand
	 */
	void execute(TableCell[][] table, RobotHand rbh);

	/**
	 * Get the name of the operation
	 * @return the String represents the name of the operation.
	 * For example: Move Left, Open, ...
	 */
	String getOpName();
} // end interface Operation