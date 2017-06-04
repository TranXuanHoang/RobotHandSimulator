/**
 * Component type: Control
 * Purpose: - main control inside the software system.
 * 			  coordinates both the robot’s hand and the 
 * 			  simulation of these robot’s operations.
 * 
 * @author Tran Xuan Hoang
 */
package layer5;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import layer1.Operation;
import layer1.RobotHand;
import layer1.TableCell;
import layer2.OperationFlowPlanning;

public class ControlRobotArm extends SwingWorker<Integer, DispatchInfo> {
	private TableCell[][] table;
	private JButton[][] itemButtons;
	private List<Operation> plan;
	private RobotHand rbh;
	private RBHSimulation rbhSimulation;
	private JButton resetButton;
	private JLabel infoLabel;

	private Operation op1 = null;
	private Operation op2 = null;

	/**
	 * Constructor
	 * @param table the table that contains information about
	 * imtes on it.
	 * @param itemButtons the array of buttons to simulate the
	 * 		  real table and the real items on that table.
	 * @param plan list of Operations that the robot hand has
	 * 		  to perform in order to move all items to their positions.
	 * @param rbh the object that represents information about
	 * 		  the robot's hand.
	 * @param rbhSimulationthe object that represents information for
	 * 		  simulation of the robot's hand on GUI.
	 * @param resetButton the 'Reset' button
	 * @param infoLabel the label at the right bottom corner of the GUI.
	 * 		  This label is used to show the next operation that the
	 * 		  robot will perform.
	 */
	public ControlRobotArm(TableCell[][] table,
			JButton[][] itemButtons,
			List<Operation> plan,
			RobotHand rbh,
			RBHSimulation rbhSimulation,
			JButton resetButton,
			JLabel infoLabel) {
		this.table = table;
		this.itemButtons = itemButtons;
		this.plan = plan;
		this.rbh = rbh;
		this.rbhSimulation = rbhSimulation;
		this.resetButton = resetButton;
		this.infoLabel = infoLabel;
	} // end constructor

	/**
	 * This method is override from superclass SwingWorker.
	 * It performs control the robot hand move, pick up or
	 * put down items. This method also sends the corresponding
	 * signal to method 'process' and 'done' to update the
	 * GUI while the robot is performing its operations.
	 */
	protected Integer doInBackground() throws Exception {
		for (int i = 0; i < plan.size(); i++) {

			Operation op = plan.get(i);
			RobotHand currentRBH = new RobotHand(
					rbh.curRow, rbh.curCol, 
					rbh.posStatus, rbh.actStatus,
					rbh.itemOnHand);

			infoLabel.setText("<html>Next Operation: " +
					"<font color=\"red\">" +
					op.getOpName() +"</font></html>");
			delay(500);
			setProgress(100 * (i + 1) / plan.size());

			DispatchInfo dispatchInfor = new DispatchInfo(op, currentRBH);
			publish(dispatchInfor);

			op.execute(table, rbh);

		}

		return null;
	} // end method doInBackground

	/**
	 * Update the actions and status of the robot on GUI.
	 */
	protected void process(List<DispatchInfo> publishedVals) {
		for (int i = 0; i < publishedVals.size(); i++) {
			DispatchInfo dispatchInfo = publishedVals.get(i);
			Operation op = dispatchInfo.op;
			RobotHand oldRBH = dispatchInfo.rbh;

			RBHOperationsSimulation.simulate(
					op, op1, op2, itemButtons, oldRBH, rbhSimulation);

			op2 = op1;
			op1 = op;
		}
	} // end method process

	/**
	 * Update the GUI when the robot finish performing all operations
	 */
	protected void done() {
		resetButton.setEnabled(true);
		resetButton.setBackground(new JButton().getBackground());
		infoLabel.setText("Finish");
		rbhSimulation.rbhIcon = null;

		OperationFlowPlanning.printTable(table);
		System.out.println(rbh);
	} // end method done

	/**
	 * This method delay a thread a slot of time to simulate
	 * that the robot is performing its operations
	 * @param milisecond mili seconds the GUI will be delayed.
	 */
	public static void delay(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	} // end method delay
} // end class ControlRobotArm

/**
 * This is a private class that is used to represent information
 * exchanged between method 'doInBackGround' and 'process'.
 */
class DispatchInfo {
	protected Operation op;
	protected RobotHand rbh;

	public DispatchInfo(Operation op, RobotHand rbh) {
		this.op = op;
		this.rbh = rbh;
	} // end constructor
} // end class DispatchInfo