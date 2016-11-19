/**
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
 * @author Nguyen Nguyen - T144249
 *
 */
public class cHW01_Calculator_T144249 extends JFrame {
	JMenuBar mnbBar;
	JMenu mnEdit, mnHelp, mnView;
	JMenuItem mniStandard, mniExit, mniScientific, mniProgrammer, mniCopy, mniPaste, mniAbout;
	JTextField txtResult = new JTextField("0");
	JScrollPane scrPage = new JScrollPane(txtResult);
	String[][] sNameStan = { { "MC", "MR", "MS", "M+", "M-" },
			{ "<-", "CE", "C", "+/-", "√" },
			{ "7", "8", "9", "/", "%" },
			{ "4", "5", "6", "*", "1/x" },
			{ "1", "2", "3", "-", "=" },
			{ "0", ".", "+", "", "" } };
	String[][] sScientific = { { "", "Inv", "ln", "(", ")", },
			{ "Int", "sinh", "sin", "x^2", "n!" },
			{ "dms", "cosh", "cos", "x^y", "x√y" },
			{ "π", "tanh", "tan", "x^3", "3√x" },
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
	ButtonGroup btgScientific = new ButtonGroup(),
			btgProgrammer1 = new ButtonGroup(),
			btgProgrammer2 = new ButtonGroup();
	int w = 50, h = 50, d = 5;
	int x = 0, y = 0;
	int lText;
	double dMemory = 0, dNumber1=0, dNumber2, result;
	boolean fAppend = false;
	String sHandling, sCal = "";
	Insets sM = new Insets(1, 1, 1, 1);
	String memory;

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
		add(panBoxProgrammer1);
		add(panBoxProgrammer2);
		add(optDegrees);
		add(optGrads);
		add(optRadians);

		Font font = txtResult.getFont();
		txtResult.setFont(new Font(font.getName(), Font.BOLD, font.getSize() + 14));
		txtResult.setHorizontalAlignment(JTextField.RIGHT);
		txtResult.setForeground(Color.BLUE);

		add(scrPage);
		add(panStandards);
		add(panProgrammer);
		add(panScientific);

		initStandards();
		initScientific();
		initProgramer();
		
		txtResult.setEditable(false);
		
		initDisplay(2); //bat dau chay chuong trinh se chay Standards

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
				if (e.getSource() == mniCopy) {
					memory = txtResult.getText();
				}
				if (e.getSource() == mniPaste) {
					txtResult.setText(memory);
				}
			}
		};
		mniProgrammer.addActionListener(action);
		mniStandard.addActionListener(action);
		mniScientific.addActionListener(action);
		mniCopy.addActionListener(action);
		mniPaste.addActionListener(action);
		mniAbout.addActionListener(action);
		
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

		mniExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK));
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
		btnScientific[1][1].setEnabled(false);
		btnScientific[2][1].setEnabled(false);
		btnScientific[3][1].setEnabled(false);
		btnScientific[4][1].setEnabled(false);
		btnScientific[0][3].setEnabled(false);
		btnScientific[0][4].setEnabled(false);
		btnScientific[1][0].setEnabled(false);
		btnScientific[2][0].setEnabled(false);
		btnScientific[4][0].setEnabled(false);
		
		initDisplay(2);
	}
	
	public void initProgramer(){
		//box 1 trong programmer
		panBoxProgrammer1.setBounds(10, 100, 80, 150); 
		panBoxProgrammer1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panBoxProgrammer1.add(optHex);
		panBoxProgrammer1.add(optOct);
		panBoxProgrammer1.add(optDec);
		panBoxProgrammer1.add(optBin);
		btgProgrammer1.add(optHex);
		btgProgrammer1.add(optOct);
		btgProgrammer1.add(optDec);
		btgProgrammer1.add(optBin);
		optHex.setSelected(true);
		panBoxProgrammer1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		//box 2 trong programmer
		panBoxProgrammer2.setBounds(10, 270, 80, 150);
		panBoxProgrammer2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panBoxProgrammer2.add(optQword);
		panBoxProgrammer2.add(optDword);
		panBoxProgrammer2.add(optWord);
		panBoxProgrammer2.add(optByte);
		btgProgrammer2.add(optQword);
		btgProgrammer2.add(optDword);
		btgProgrammer2.add(optWord);
		btgProgrammer2.add(optByte);
		optQword.setSelected(true);
		panBoxProgrammer2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		//khai bao va nhap cac button vao programmer
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
		btnProgrammer[0][0].setEnabled(false);
		btnProgrammer[1][0].setEnabled(false);
		btnProgrammer[1][1].setEnabled(false);
		btnProgrammer[3][0].setEnabled(false);
		btnProgrammer[3][1].setEnabled(false);
		btnProgrammer[4][0].setEnabled(false);
		btnProgrammer[4][1].setEnabled(false);
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
			panStandards.setBounds(290, 100, 300, 330);
			this.setSize(570, 480);
			scrPage.setBounds(10, 10, 550, 80);
			panBoxScientific.setVisible(true);
			panBoxProgrammer1.setVisible(false);
			panBoxProgrammer2.setVisible(false);
		} else if (mode == 1) { //standards view
			panScientific.setVisible(false);
			panProgrammer.setVisible(false);
			panStandards.setVisible(true);
			panStandards.setLayout(null);
			this.setSize(290, 480);
			scrPage.setBounds(10, 10, 270, 80);
			panStandards.setBounds(10, 100, 270, 330);
			panBoxScientific.setVisible(false);
			panBoxProgrammer1.setVisible(false);
			panBoxProgrammer2.setVisible(false);
		} else if (mode == 3) { //programmer view
			this.setSize(555, 480);
			panScientific.setVisible(false);
			panStandards.setVisible(true);
			panProgrammer.setVisible(true);
			panProgrammer.setLayout(null);
			panStandards.setLayout(null);
			panBoxScientific.setVisible(false);
			scrPage.setBounds(10, 10, 530, 80);
			panBoxProgrammer1.setVisible(true);
			panBoxProgrammer2.setVisible(true);
			panProgrammer.setBounds(100, 100, x, y);
			panStandards.setBounds(270, 100, 270, 330);
		}
	}
//=======================================================================================
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
				/**
				 * @param sCal: phep tinh
				 */
				// standards
				JButton btnCal = (JButton) e.getSource();
				dNumber1 = Double.parseDouble(txtResult.getText());
				sCal = btnCal.getText();
				fAppend = false;
				if (sCal.equals("1/x")){
					result = 1/dNumber1;
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("√")){
					result = Math.sqrt(dNumber1);
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("+/-")){
					if (dNumber1 < 0){
						dNumber1 = dNumber1*-1;
						filterResult(dNumber1);
					}else if(dNumber1 > 0){
						dNumber1 = dNumber1*-1;
						filterResult(dNumber1);
					}
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("<-")) {
					String sText = txtResult.getText();
					String subText;
					lText = sText.length();
					int count = txtResult.getText().length();
					if (lText > 1) {
						subText = sText.substring(0, lText - 1);
						txtResult.setText(subText);
					} else if (lText == 1) {
						txtResult.setText("0");
						fAppend = false;
					}
				}
				
				//scientific
				if (sCal.equals("log")){
					result = Math.log10(dNumber1);
					filterResult(result);
				}
				if (sCal.equals("ln")){
					result = Math.log(dNumber1);
					filterResult(result);
				}
				if (optDegrees.isSelected()){
					if (sCal.equals("sin")) {
						result = Math.sin(dNumber1*Math.PI/180); //theo degree
						filterResult(result);
					}
					if (sCal.equals("cos")) {
						result = Math.cos(dNumber1*Math.PI/180); //theo degree
						filterResult(result);
					}
					if (sCal.equals("tan")) {
						result = Math.tan(dNumber1*Math.PI/180); //theo degree
						filterResult(result);
					}
				}else if(optRadians.isSelected()){
					if (sCal.equals("sin")) {
						result = Math.sin(dNumber1); //radians 
						filterResult(result);
					}
					if (sCal.equals("cos")) {
						result = Math.cos(dNumber1); //radians 
						filterResult(result);
					}
					if (sCal.equals("tan")) {
						result = Math.tan(dNumber1); //radians 
						filterResult(result);
					}
				}
				
				if (sCal.equals("x^2")) {
					result = dNumber1*dNumber1;
					filterResult(result);
				}
				if (sCal.equals("x^3")) {
					result = dNumber1 * dNumber1 * dNumber1;
					filterResult(result);
					sCal = "";
				}
				if (sCal.equals("3√x")) {
					result = Math.cbrt(dNumber1);
					filterResult(result);
					sCal = "";
				}
				if (sCal.equals("10^x")) {
					result = Math.pow(10, dNumber1);
					filterResult(result);
					sCal = "";
				}
				if (sCal.equals("n!")) {
					String sText = txtResult.getText();
					result = 1;
					int count = 0;
					for (int i = 0; i < sText.length(); i++) {
						char cCheck = sText.charAt(i);
						if (cCheck == '.') {
							count++;
						}
					}
					if (count == 0) {
						if (dNumber1 > 0) {
							for (int j = 1; j <= dNumber1; j++) {
								result = result * j;
							}
							txtResult.setText("" + result);
						} else if (dNumber1 == 0) {
							txtResult.setText("1" );
						} else if (dNumber1 < 0) {
							txtResult.setText("Invalid Input");
						}
					} else {
						txtResult.setText("Invalid Input");
					}
				}
				if (sCal.equals("=")){
					Double a = Double.parseDouble(txtResult.getText());
					filterResult(a);
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
		btnStandards[1][0].addActionListener(actCal);
		btnStandards[1][3].addActionListener(actCal);
		btnScientific[0][2].addActionListener(actCal);
		btnScientific[1][2].addActionListener(actCal);
		btnScientific[1][3].addActionListener(actCal);
		btnScientific[1][4].addActionListener(actCal);
		btnScientific[2][2].addActionListener(actCal);
		btnScientific[2][3].addActionListener(actCal);
		btnScientific[2][4].addActionListener(actCal);
		btnScientific[3][2].addActionListener(actCal);
		btnScientific[3][3].addActionListener(actCal);
		btnScientific[3][4].addActionListener(actCal);
		btnScientific[4][3].addActionListener(actCal);
		btnScientific[4][2].addActionListener(actCal);
		btnScientific[4][4].addActionListener(actCal);
		
		btnScientific[3][0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtResult.setText("" + Math.PI);
				fAppend = false;
			}
		});
		
		btnStandards[5][1].addActionListener(new ActionListener() { //xu ly dau cham'
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sText = txtResult.getText();
				int count = 0;
				for (int i = 0; i < sText.length(); i++) {
					char cCheck = sText.charAt(i);
					if (cCheck == '.') {
						count++;
					}
				}
				if (count == 0) {
					sText += ".";
					txtResult.setText(sText);
				}
			}
		});

		//phep cong tru co ban
		ActionListener acEqual = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sCal.equals("+")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 + dNumber2;
					String s = String.valueOf(result);
					txtResult.setText(""+result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("-")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 - dNumber2;
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("*")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 * dNumber2;
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("/")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = dNumber1 / dNumber2;
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("x^y")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = Math.pow(dNumber1, dNumber2);
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("x√y")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					result = Math.pow(dNumber2, 1/dNumber1);
					filterResult(result);
					fAppend = false;
					sCal = "";
				}
				if (sCal.equals("Mod")) {
					dNumber2 = Double.parseDouble(txtResult.getText());
					double n = dNumber1 % dNumber2;
					filterResult(n);
					fAppend = false;
					sCal = "";
				}
			}
		};
		btnStandards[4][4].addActionListener(acEqual);

		// luu so vao ben trong bo nho
		ActionListener actMemory = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnMem = (JButton) e.getSource();
				String sFunction = btnMem.getText();
				Double number = Double.parseDouble(txtResult.getText());
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
				if (sFunction.equals("M+")){
					dMemory = dMemory + number;
					filterResult(number);
					sCal = "";
				}
				if (sCal.equals("M-")){
					dMemory = dMemory - number;
					filterResult(number);
					sCal = "";
				}
				fAppend = false;
			}
		};
		for (int j = 0; j < 5; j++) {
			btnStandards[0][j].addActionListener(actMemory);
		}
	}

	public void exitProgram() { //exit chuong trinh
		int res = JOptionPane.showConfirmDialog(null, "Are you sure?", "Yes",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public void filterResult(Double dNumber){
		String s = String.valueOf(dNumber);
		int lText = s.length();
		if (s.endsWith(".0") || s.endsWith(".00") || s.endsWith(".000") || s.endsWith(".0000")){
			String b = s.substring(0, lText-2);
			txtResult.setText(b);
		}else{
			txtResult.setText(""+result);
		}
	}
	
	public cHW01_Calculator_T144249() { //class chinh
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
		mainWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}