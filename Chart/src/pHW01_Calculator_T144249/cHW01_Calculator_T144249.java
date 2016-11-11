/**
 * 
 */
package pHW01_Calculator_T144249;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import org.omg.CORBA.ARG_OUT;

/**
 * @author apple
 *
 */
public class cHW01_Calculator_T144249 extends JFrame {
	JMenuBar mnbBar;
	JMenu mnEdit, mnHelp, mnView;
	JMenuItem mniStandard, mniExit, mniScientific, mniProgrammer, mniCopy, mniPaste, mniAbout;
	JTextField txtResult = new JTextField();
	JScrollPane scrPage;
	String[][] sNameStan = { { "MC", "MR", "MS", "M+", "M-" },
			{ "<-", "CE", "C", "+/-", "x^1/2" },
			{ "7", "8", "9", "/", "%" },
			{ "4", "5", "6", "*", "1/x" },
			{ "1", "2", "3", "-", "=" },
			{ "0", ".", "+", "", "" } };
	JButton[][] btnStandards = new JButton[6][5];
	JPanel panStandards = new JPanel();
	JPanel panScientific = new JPanel();
	JPanel panProgrammer = new JPanel();
	int w = 50, h = 50, d = 5;
	int x = 0, y = 0;
	double dMemory = 0, dNumber1, dNumber2, dNumber3;
	boolean fAppend = false;
	String sHandling;

	public void initializeMenu() {
		mnbBar = new JMenuBar();
		mnView = new JMenu("View");
		mnEdit = new JMenu("Edit");
		mnHelp = new JMenu("Help");
		mniExit = new JMenuItem("Exit");
		mniStandard = new JMenuItem("Standard");
		mniScientific = new JMenuItem("Scientific");
		mniProgrammer = new JMenuItem("Programmer");
		mniCopy = new JMenuItem("Copy");
		mniPaste = new JMenuItem("Paste");
		mniAbout = new JMenuItem("About");

		// add menu
		mnbBar.add(mnView);
		mnbBar.add(mnEdit);
		mnbBar.add(mnHelp);

		// add Menu Item
		mnView.add(mniStandard);
		mnView.add(mniProgrammer);
		mnView.add(mniScientific);
		mnView.addSeparator();
		mnView.add(mniExit);
		mnEdit.add(mniCopy);
		mnEdit.add(mniPaste);
		mnHelp.add(mniAbout);
		setJMenuBar(mnbBar);

		Font font = txtResult.getFont();
		txtResult.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 14));
		txtResult.setHorizontalAlignment(JTextField.RIGHT);

		add(txtResult);
		add(panStandards);
		panStandards.setLayout(null);

		initStandards();

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == mniExit) {
					exitProgram();
				}
				if (e.getSource() == mniStandard) {
					initStandards();
				}
			}
		};

		mniExit.addActionListener(action);
		mniStandard.addActionListener(action);

		mniExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.VK_CONTROL));
		mniStandard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_MASK));
		mniScientific.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
		mniProgrammer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_MASK));
		mniCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.VK_CONTROL));
		mniPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.VK_CONTROL));
		mniAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));
	}

	public void initStandards() { //panel của Standards 
		txtResult.setBounds(10, 10, 270, 80);
		panStandards.setBounds(10, 100, 300, 330);
		Insets sM = new Insets(1, 1, 1, 1);
		this.setSize(290, 480);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnStandards[i][j] = new JButton(sNameStan[i][j]);
				panStandards.add(btnStandards[i][j]);
				btnStandards[i][j].setBounds(x, y, w, h);
				btnStandards[i][j].setMargin(sM);
				x = x + w + d;
			}
			y = y + h + d;
		}
		btnStandards[5][0].setSize(w + d + w, w);
		btnStandards[4][4].setSize(w, h + d + h);
		btnStandards[5][1].setLocation(w + d + w + d, y - h - d);
		btnStandards[5][2].setLocation(w + d + w + d + w + d, y - h - d);
		btnStandards[5][4].setVisible(false);
		btnStandards[5][3].setVisible(false);
	}

	public void initHandling() { //
		// them so vao man hinh  
		ActionListener actNumber = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent argO) {
				// TODO Auto-generated method stub
				JButton btnNumber = (JButton) argO.getSource();
				String sNumber = btnNumber.getText();
				String sCurrentNumber = txtResult.getText();
				if ( fAppend == true) { //rs lai so
					if (sCurrentNumber.equals("0")) {
						txtResult.setText(sNumber);
					} else {
						txtResult.setText(sCurrentNumber + sNumber);
					}
				}else{
					txtResult.setText(sNumber);
					fAppend = false;
				}
			}
		};
		for (int i = 2; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				btnStandards[i][j].addActionListener(actNumber);
			}
		}
		btnStandards[5][0].addActionListener(actNumber);

		// luu so vao ben trong bo nho
		ActionListener actMemory = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnMem = (JButton) e.getSource();
				String sFunction = btnMem.getText();

				if (sFunction.equals("MS")) {
					// memory store
					dMemory = Double.parseDouble(txtResult.getText());
				}
				if (sFunction.equals("MR")) {
					// memory recall
					txtResult.setText("" + dMemory);
				}
				if (sFunction.equals("MC")) {
					// clear memory
					dMemory = 0;
				}
				fAppend = false;
			}
		};
		for (int j = 0; j < 3; j++) {
			btnStandards[0][j].addActionListener(actMemory);
		}
		
		ActionListener actCal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnCal = (JButton) e.getSource();
				dNumber1 = Double.parseDouble(txtResult.getText());
				sHandling = btnCal.getText();
				fAppend = false;
			}
		};
		btnStandards[5][2].addActionListener(actCal);
		btnStandards[4][3].addActionListener(actCal);
		btnStandards[3][3].addActionListener(actCal);
		btnStandards[2][3].addActionListener(actCal);
	}

	public void exitProgram() {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Comfirm",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public cHW01_Calculator_T144249() {
		this.setTitle("T144249 – Calculator");
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		initializeMenu();
		initHandling();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cHW01_Calculator_T144249 mainWindow = new cHW01_Calculator_T144249();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}