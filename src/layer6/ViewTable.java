/**
 * Component type: User Interface
 * Purpose: - Create the GUI for use to interact with the
 * 			  software system.
 * 			- Simulate the operations of the robot
 * 
 * @author Tran Xuan Hoang
 */
package layer6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import layer1.Items;
import layer1.Operation;
import layer1.RobotHand;
import layer1.RobotHand.Status;
import layer1.TableCell;
import layer2.OperationFlowPlanning;
import layer5.ControlRobotArm;
import layer5.RBHSimulation;

public class ViewTable extends JPanel {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int rows;
	private int cols;
	private TableCell[][] table;
	private RobotHand rbh;
	private RBHSimulation rbhSimulation;

	ControlRobotArm controlRBA;

	private JButton[][] itemButtons;

	private JList<String> colorList;
	private JCheckBox delCheckBox;
	private JButton moveButton;
	private JButton resetButton;

	private JProgressBar progressBar;
	private JLabel infoLabel;

	private final String[] colorNames = { "Blue", "Red", "Green", "Black",
			"Yellow", "Pink", "Orange" };

	/**
	 * Constructor
	 * 
	 * @param rows
	 *            number of rows of the table
	 * @param cols
	 *            number of columns of the table. Here 'cols' is the number of
	 *            columns of the table that user place items at the beginning.
	 */
	public ViewTable(int rows, int cols) {
		// super("Simulator of Robot Hand");
		super();

		this.rows = rows;
		this.cols = cols;
		this.table = new TableCell[rows][2 * cols];

		setLayout(new BorderLayout());

		colorList = new JList<String>(colorNames); // list of colorNames
		colorList.setVisibleRowCount(2); // display five rows at once

		// do not allow multiple selections
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// set selected color to the fist color in the list of colors
		colorList.setSelectedIndex(0);

		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel temp = new JPanel(new GridLayout(1, 2));
		temp.setBorder(new EmptyBorder(0, 0, 0, 20));
		JLabel colorLabel = new JLabel("Color of Items: ");
		temp.add(colorLabel);
		temp.add(new JScrollPane(colorList));
		northPanel.add(temp);

		delCheckBox = new JCheckBox("Delete Items");
		delCheckBox.setBorder(new EmptyBorder(0, 0, 0, 20));
		northPanel.add(delCheckBox);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(resetButtonListener);
		northPanel.add(resetButton);
		moveButton = new JButton("Move Items");
		moveButton.addActionListener(moveButtonListener);
		northPanel.add(moveButton);

		add(northPanel, BorderLayout.NORTH);

		// create table of items
		JPanel board1Panel = new JPanel(new GridLayout(rows, cols));
		board1Panel.setBorder(new EmptyBorder(0, 0, 0, 10));
		JPanel board2Panel = new JPanel(new GridLayout(rows, cols));
		board2Panel.setBorder(new EmptyBorder(0, 10, 0, 0));

		itemButtons = new JButton[rows][cols * 2];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				JButton button = new JButton();
				button.setBackground(Color.WHITE);

				itemButtons[i][j] = button;
				itemButtons[i][j].addActionListener(itemButtonsListener);

				board1Panel.add(itemButtons[i][j]);

				table[i][j] = new TableCell();
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = cols; j < 2 * cols; j++) {
				JButton button = new JButton();
				button.setBackground(Color.WHITE);
				itemButtons[i][j] = button;

				board2Panel.add(itemButtons[i][j]);

				table[i][j] = new TableCell();
			}
		}

		// initialize robot hand
		initRHB();

		JPanel boardPanel = new JPanel(new GridLayout(1, 2));
		boardPanel.add(board1Panel);
		boardPanel.add(board2Panel);
		add(boardPanel, BorderLayout.CENTER);

		JPanel southPanel = new JPanel(new GridLayout(1, 2, 20, 10));
		JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel progresLabel = new JLabel("Progress:");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.RED);
		progressPanel.add(progresLabel);
		progressPanel.add(progressBar);
		southPanel.add(progressPanel);

		infoLabel = new JLabel();
		southPanel.add(infoLabel);
		add(southPanel, BorderLayout.SOUTH);
	} // end constructor

	/**
	 * Initialize the robot hand and its property for the simulation later. The
	 * robot hand is set up at the top left cell of the table that it will move
	 * items to, and it's hand is UP, OPEN and does not hold any Item.
	 */
	public void initRHB() {
		rbh = new RobotHand(0, cols, Status.UP, Status.OPEN, Items.NONE);

		Icon rbhIcon = null;
		Icon nextIcon = itemButtons[0][cols].getIcon();
		rbhSimulation = new RBHSimulation(rbhIcon, nextIcon);

		itemButtons[0][cols].setBorder(rbhSimulation.rbhBD);
	} // end method initRBH

	/**
	 * This method resize the resolution of the image icon read from memory. The
	 * image will be resized to 20px*20px
	 * 
	 * @param icon
	 *            the icon read from memory
	 * @return the resized ImageIcon
	 */
	public ImageIcon resizeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	} // end method resizeIcon

	/**
	 * Event Handler for the cell that user click to specify the items robot has
	 * to move at the beginning.
	 */
	private ActionListener itemButtonsListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton bt = (JButton) arg0.getSource();

			if (!delCheckBox.isSelected()) {
				// modify 'itemButtons'
				int colorIndex = colorList.getSelectedIndex();
				String color = colorNames[colorIndex] + "Circle.png";
				ImageIcon icon = new ImageIcon(getClass().getResource(
						"Images/" + color));
				bt.setIcon(resizeIcon(icon));

				// modify 'table'
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if (bt.equals(itemButtons[i][j])) {
							table[i][j].setItem(Items.getItem(colorIndex));
						}
					}
				}
			} else {
				// modify 'itemButtons'
				bt.setIcon(null);

				// modify 'table'
				for (int i = 0; i < rows; i++)
					for (int j = 0; j < cols; j++)
						if (bt.equals(itemButtons[i][j]))
							table[i][j].setItem(Items.NONE);
			}
		}
	};

	/**
	 * Event Handler for the 'Reset' button
	 */
	private ActionListener resetButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// reset table with items
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols * 2; j++) {
					itemButtons[i][j].setIcon(null);
					table[i][j].setItem(Items.NONE);
				}
			}

			// recovery the 'Move Items' button
			moveButton.setEnabled(true);
			moveButton.setBackground(new JButton().getBackground());

			infoLabel.setText("");
			progressBar.setValue(0);
		}
	};

	/**
	 * Event Handler for the 'Move Items' button
	 */
	private ActionListener moveButtonListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			List<Operation> plan = OperationFlowPlanning.make(table, rbh);

			controlRBA = new ControlRobotArm(table, itemButtons, plan, rbh,
					rbhSimulation, resetButton, infoLabel);
			controlRBA.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent e) {
					if (e.getPropertyName().equals("progress")) {
						int newVal = (Integer) e.getNewValue();
						progressBar.setValue(newVal);
					}
				}
			});
			controlRBA.execute();

			moveButton.setEnabled(false);
			moveButton.setBackground(Color.CYAN);
			resetButton.setEnabled(false);
			resetButton.setBackground(Color.CYAN);
		}
	};

	/**
	 * Get the numbers of rows and columns from users, then create the main GUI
	 * for the whole process of running of the software.
	 */
	private static void createAndShowGUI() {
		// Get inputs from user
		int rows = 0;
		int cols = 0;

		do {
			JComponent.setDefaultLocale(Locale.ENGLISH);
			JTextField rowTextField = new JTextField(10);
			JTextField colTextField = new JTextField(10);
			JLabel msg = new JLabel("<html>Please input the number "
					+ "of rows and columns "
					+ "<br>of the table. Number of rows or columns must"
					+ "<br>be <font color=\"red\">greater than 4 and "
					+ "less than 15</font>.</html>");
			msg.setBorder(new EmptyBorder(0, 0, 10, 0));

			JPanel tempPanel = new JPanel(new GridLayout(0, 2, 0, 5));
			tempPanel.add(new JLabel("Rows:"));
			tempPanel.add(rowTextField);
			tempPanel.add(new JLabel("Columns:"));
			tempPanel.add(colTextField);
			JPanel inputPanel = new JPanel(new GridLayout(0, 1));
			inputPanel.add(msg);
			inputPanel.add(tempPanel);

			int result = JOptionPane.showConfirmDialog(null, inputPanel,
					"Input Parameters", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				try {
					rows = Integer.parseInt(rowTextField.getText());
					cols = Integer.parseInt(colTextField.getText());
				} catch (NumberFormatException ex) {
				}
			} else if (result == JOptionPane.CANCEL_OPTION
					|| result == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}
		} while (rows <= 4 || rows >= 15 || cols <= 4 || cols >= 15);

		// Create and set up the window
		JFrame frame = new JFrame("Simulator of Robot Hand");

		// Create and set up the content pane
		ViewTable pane = new ViewTable(rows, cols);
		pane.setOpaque(true);
		frame.setContentPane(pane);

		// Display the window
		frame.setSize(2 * cols * 50, rows * 50 + 110);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	} // end method createAndShowGUI

	public static void main(String args[]) {
		// Uncomment this try-catch to get the appliacation's GUI
		// compatible with the default look-and-feel of the operating
		// system that you are using.
		/*try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}*/
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	} // end main
} // end class ViewTable