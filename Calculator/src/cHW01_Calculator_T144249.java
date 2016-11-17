/**
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	JTextField txtResult = new JTextField("0");
	JScrollPane scrPage = new JScrollPane(txtResult);
	String[][] sNameStan = { { "MC", "MR", "MS", "M+", "M-" },
			{ "<-", "CE", "C", "+/-", "x^1/2" },
			{ "7", "8", "9", "/", "%" },
			{ "4", "5", "6", "*", "1/x" },
			{ "1", "2", "3", "-", "=" },
			{ "0", ".", "+", "", "" } };
	String[][] sScientific = { { "", "Inv", "ln", "(", ")", },
			{ "Int", "sinh", "sin", "x^2", "n!" },
			{ "dms", "cosh", "cos", "x^y", "x^1/y" },
			{ "PI", "tanh", "tan", "x^3", "x^1/3" },
			{ "F-E", "Exp", "Mod", "log", "10^x" } };
	String[][] sProgram = { { "", "Mod", "A", },
			{ "(", ")", "B" },
			{ "RoL", "RoR", "C" },
			{ "Or", "Xor", "D" },
			{ "Lsh", "Rsh", "E" },
			{ "Not", "And", "F" } };
	JButton[][] btnStandards = new JButton[6][5],
			btnScientific = new JButton[5][5],
			btnProgrammer = new JButton[6][3];
	JPanel panStandards = new JPanel(),
			panScientific = new JPanel(),
			panProgrammer = new JPanel(),
			panBoxScientific = new JPanel(),
			panBoxProgrammer1 = new JPanel(),
			panBoxProgrammer2 = new JPanel();
	JRadioButton optDegrees = new JRadioButton("Degrees"),
			optRadians = new JRadioButton("Radians"),
			optGrads = new JRadioButton("Grads");
	JRadioButton optHex = new JRadioButton("Hex"),
			optDec = new JRadioButton("Dec"),
			optOct = new JRadioButton("Oct"),
			optBin = new JRadioButton("Bin"),
			optQword = new JRadioButton("Qword"),
			optDword = new JRadioButton("Dword"),
			optWord = new JRadioButton("Word"),
			optByte = new JRadioButton("Byte");
	ButtonGroup btgScientific = new ButtonGroup();
	int w = 50, h = 50, d = 5;
	int x = 0, y = 0;
	double dMemory = 0, dNumber1=0, dNumber2, result;
	boolean fAppend = false;
	String sHandling, sCal = "";
	Insets sM = new Insets(1, 1, 1, 1);

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
		mnView.add(mniScientific);
		mnView.add(mniProgrammer);
		mnView.addSeparator();
		mnView.add(mniExit);
		mnEdit.add(mniCopy);
		mnEdit.add(mniPaste);
		mnHelp.add(mniAbout);
		setJMenuBar(mnbBar);
		
		add(panBoxScientific);
		add(optDegrees);
		add(optGrads);
		add(optRadians);

		Font font = txtResult.getFont();
		txtResult.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 14));
		txtResult.setHorizontalAlignment(JTextField.RIGHT);

		add(scrPage);
		add(panStandards);
		add(panProgrammer);
		add(panScientific);

		initStandards();
		initScientific();
		initProgramer();
		
		initDisplay(1); //bat dau chay chuong trinh se chay Standards

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == mniExit) {
					exitProgram();
				}
				if (e.getSource() == mniStandard) {
					initDisplay(1);
				}
				if (e.getSource() == mniScientific) {
					initDisplay(2);
				}
				if (e.getSource() == mniProgrammer) {
					initDisplay(3);
				}
			}
		};
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				exitProgram();
			}
		});
		mniExit.addActionListener(action);
		mniStandard.addActionListener(action);
		mniProgrammer.addActionListener(action);
		mniScientific.addActionListener(action);

		mniExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.VK_CONTROL));
		mniStandard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.ALT_MASK));
		mniScientific.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
		mniProgrammer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.ALT_MASK));
		mniCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.VK_CONTROL));
		mniPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.VK_CONTROL));
		mniAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));
	}

	public void initStandards() { //panel cua Standards 
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
		btnStandards[2][4].setEnabled(false);
		this.add(panStandards);
		initDisplay(1);
	}
	
	public void initScientific() { //panel cua Scientific
		optGrads.setEnabled(false);
		btgScientific.add(optDegrees);
		btgScientific.add(optRadians);
		optDegrees.setSelected(true);
		panBoxScientific.setLayout(new BorderLayout(14, 10));
		panBoxScientific.setBounds(10, 100, 270, 50);
		panBoxScientific.add(optDegrees, BorderLayout.WEST);
		panBoxScientific.add(optRadians, BorderLayout.CENTER);
		panBoxScientific.add(optGrads, BorderLayout.EAST);
		panBoxScientific.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); //vien` panbox
		y = 0;
		for (int i = 0; i < 5; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnScientific[i][j] = new JButton(sScientific[i][j]);
				panScientific.add(btnScientific[i][j]);
				btnScientific[i][j].setBounds(x, y, w, h);
				btnScientific[i][j].setMargin(sM);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panScientific);
		btnScientific[0][0].setEnabled(false);
		btnScientific[0][1].setEnabled(false);
		btnScientific[0][3].setEnabled(false);
		btnScientific[0][4].setEnabled(false);
		initDisplay(2);
	}
	
	public void initProgramer(){
		add(panBoxProgrammer1);
		add(panBoxProgrammer2);
		panBoxProgrammer1.add(optHex);
		panBoxProgrammer1.add(optDec);
		panBoxProgrammer1.add(optOct);
		panBoxProgrammer1.add(optBin);
		panBoxProgrammer1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panBoxProgrammer1.setLayout(new BorderLayout(14, 10));
		panBoxProgrammer1.setBounds(10, 100, 50, 270);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 3; j++) {
				btnProgrammer[i][j] = new JButton(sProgram[i][j]);
				panProgrammer.add(btnProgrammer[i][j]);
				btnProgrammer[i][j].setBounds(x, y, w, h);
				btnProgrammer[i][j].setMargin(sM);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panProgrammer);
		initDisplay(3);
	}
	
	public void initDisplay(int mode) {
		if (mode == 2) { //scientific view
			panProgrammer.setVisible(false);
			panScientific.setVisible(true);
			panStandards.setVisible(true);
			panScientific.setLayout(null);
			panStandards.setLayout(null);
			panScientific.setBounds(10, 155, 270, 400);
			panStandards.setBounds(285, 100, 300, 330);
			this.setSize(570, 480);
			scrPage.setBounds(10, 10, 545, 80);
			panBoxScientific.setVisible(true);
		} else if (mode == 1) { //standards view
			panScientific.setVisible(false);
			panProgrammer.setVisible(false);
			panStandards.setVisible(true);
			panStandards.setLayout(null);
			panBoxProgrammer1.setVisible(false);
			this.setSize(290, 480);
			scrPage.setBounds(10, 10, 270, 80);
			panStandards.setBounds(10, 100, 270, 330);
			panBoxScientific.setVisible(false);
		} else if (mode == 3) { //programmer view
			panScientific.setVisible(false);
			panStandards.setVisible(true);
			panProgrammer.setVisible(true);
			panProgrammer.setLayout(null);
			panStandards.setLayout(null);
			panBoxScientific.setVisible(false);
			scrPage.setBounds(10, 10, w + 450, h + 40);
			panBoxProgrammer1.setBounds(10, 100, x, y);
			//panProgrammer.setBounds(10, 100, x, y);
			panStandards.setBounds(260, 100, x , y);
			this.setSize(520, 480);
		}
	}

	public void initHandling() { //xu ly cho toan bo chuong trinh
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
					fAppend = true;
				}
			}
		};
		for (int i = 2; i < 5; i++) {
			for (int j = 0; j < 3; j++) {
				btnStandards[i][j].addActionListener(actNumber);
			}
		}
		btnStandards[5][0].addActionListener(actNumber);
		
		ActionListener actClear = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtResult.setText("0");
			}
		};
		btnStandards[1][2].addActionListener(actClear);
		btnStandards[1][1].addActionListener(actClear);
		
		ActionListener actCal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnCal = (JButton) e.getSource();
				dNumber1 = Double.parseDouble(txtResult.getText());
				sCal = btnCal.getText();
				fAppend = false;
				if (sCal.equals("1/x")){
					result = 1/dNumber1;
					txtResult.setText(String.format("%.4s", result));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("x^1/2")){
					result = Math.pow(dNumber1, 0.5);
					txtResult.setText(String.format("%.4s", result));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("%")){
					
				}
				if (sCal.equals("M+")){
					result = dMemory + dNumber1;
					txtResult.setText(String.format("%.4s", result));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("M-")){
					result = dMemory - dNumber1;
					txtResult.setText(String.format("%.4s", result));
					fAppend = false;
					sCal = "";
				}
			}
		};
		btnStandards[5][2].addActionListener(actCal);
		btnStandards[4][3].addActionListener(actCal);
		btnStandards[3][3].addActionListener(actCal);
		btnStandards[2][3].addActionListener(actCal);
		btnStandards[3][4].addActionListener(actCal);
		btnStandards[1][4].addActionListener(actCal);
		btnStandards[0][3].addActionListener(actCal);
		btnStandards[0][4].addActionListener(actCal);

		ActionListener acEqual = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sCal.equals("+")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 + dNumber2;
					String s = String.valueOf(result);
					txtResult.setText(String.format("%.4s", s));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("-")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 - dNumber2;
					String s = String.valueOf(result);
					txtResult.setText(String.format("%.4s", s));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("*")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 * dNumber2;
					String s = String.valueOf(result);
					txtResult.setText(String.format("%.4s", s));
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("/")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 / dNumber2;
					String s = String.valueOf(result);
					txtResult.setText(String.format("%.4s", s));
					fAppend = false;
					sCal = "";
				}
			}
		};
		btnStandards[2][3].addActionListener(acEqual);
		btnStandards[3][3].addActionListener(acEqual);
		btnStandards[4][4].addActionListener(acEqual);
		btnStandards[5][3].addActionListener(acEqual);
		

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
		this.setLocationRelativeTo(null);

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