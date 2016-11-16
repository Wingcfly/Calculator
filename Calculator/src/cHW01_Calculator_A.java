/**
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * @author Anh Nguyen T150510
 */
public class cHW01_Calculator_A extends JFrame {
	JTextField txtIn = new JTextField(), txtIn00 = new JTextField();
	JPanel panbox = new JPanel(), panPr00 = new JPanel(), panPr01 = new JPanel() ;
	JRadioButton deg = new JRadioButton("Degrees"),rad = new JRadioButton(
			"Radians"), gra = new JRadioButton("Grads"), hex = new JRadioButton("Hex"), dec = new JRadioButton("Dec"), oct = new JRadioButton("Oct"),
			bin = new JRadioButton("Bin"), qword = new JRadioButton("Qword"), dword = new JRadioButton("Dword"),
			word = new JRadioButton("Word"), bit = new JRadioButton("Byte");
	JMenuBar bar = new JMenuBar();
	JMenu view = new JMenu("View"), edit = new JMenu("Edit"), help = new JMenu(
			"Help");
	JMenuItem stand = new JMenuItem("Standard", KeyEvent.VK_D),
			scien = new JMenuItem("Scientific", KeyEvent.VK_S),
			programmer = new JMenuItem("Programmer", KeyEvent.VK_P),
			exit = new JMenuItem("Exit", KeyEvent.VK_X), copy = new JMenuItem(
					"Copy", KeyEvent.VK_C), paste = new JMenuItem("Paste",
					KeyEvent.VK_V), about = new JMenuItem("About",
					KeyEvent.VK_A);
	ImageIcon imgcopy = new ImageIcon("Icon/Copy.gif"),
			imgpaste = new ImageIcon("Icon/Paste.gif");
	ButtonGroup btnGr = new ButtonGroup(), btnSel = new ButtonGroup(), btnSe = new ButtonGroup();;
	double dMemo = 0, dCalcu = 0;
	boolean fAppend = false;
	String sCal = "";
	Insets isMargin = new Insets(1, 1, 1, 1);

	public cHW01_Calculator_A() {
		stand.setMnemonic(KeyEvent.VK_D);
		scien.setMnemonic(KeyEvent.VK_S);
		programmer.setMnemonic(KeyEvent.VK_P);
		exit.setMnemonic(KeyEvent.VK_X);
		copy.setMnemonic(KeyEvent.VK_C);
		paste.setMnemonic(KeyEvent.VK_V);
		about.setMnemonic(KeyEvent.VK_A);
		stand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.ALT_MASK));
		scien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		programmer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.ALT_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.ALT_MASK));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		copy.setIcon(imgcopy);
		paste.setIcon(imgpaste);
		// about.setIcon(imabout);
		inittalizeMenu();
		initScien();
		initStand();
		//initProg();
		initEvent();
		displayMode(1);
		this.setTitle("cHW01_Calculator_T150510");
		// this.setPreferredSize(new Dimension(420, 450));
		// pack();
		this.setSize(270, 460);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.add(panbox);
		panbox.setLayout(new BorderLayout(14, 10));
		panbox.setSize(240, 40);
		panbox.setLocation(10, 100);
		btnGr.add(deg);
		btnGr.add(rad);
		btnGr.add(gra);
		deg.setSelected(true);
		this.add(txtIn);
		txtIn.setEditable(false);
		panbox.add(deg, BorderLayout.WEST);
		panbox.add(rad, BorderLayout.CENTER);
		panbox.add(gra, BorderLayout.EAST);
		btnSel.add(hex);
		btnSel.add(dec);
		btnSel.add(oct);
		btnSel.add(bin);
		btnSe.add(qword);
		btnSe.add(dword);
		btnSe.add(word);
		btnSe.add(bit);
		Font f = txtIn.getFont();
		txtIn.setFont(new Font(f.getName(), Font.BOLD, f.getSize() + 14));
		txtIn.setHorizontalAlignment(txtIn.RIGHT);

		ActionListener act = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == stand) {
					displayMode(1);
				} else if (e.getSource() == scien) {
					displayMode(2);
				}else if (e.getSource() == programmer){
					displayMode(3);
				}
			}
		};
		stand.addActionListener(act);
		scien.addActionListener(act);
		programmer.addActionListener(act);
	}

	public void initEvent() {
		ActionListener actNumber = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton btnNumber = (JButton) arg0.getSource();
				String sNumber = btnNumber.getText();
				String sCurrent = txtIn.getText();
				if (fAppend == true) {
					if (sCurrent.equals(0)) {
						txtIn.setText(sNumber);
					} else {
						txtIn.setText(sCurrent + sNumber);
					}
				} else {
					txtIn.setText(sNumber);
					fAppend = true;
				}
			}
		};
		btnStand[2][0].addActionListener(actNumber);
		btnStand[2][1].addActionListener(actNumber);
		btnStand[2][2].addActionListener(actNumber);
		btnStand[3][0].addActionListener(actNumber);
		btnStand[3][1].addActionListener(actNumber);
		btnStand[3][2].addActionListener(actNumber);
		btnStand[4][0].addActionListener(actNumber);
		btnStand[4][1].addActionListener(actNumber);
		btnStand[4][2].addActionListener(actNumber);
		btnStand[5][0].addActionListener(actNumber);

		ActionListener actMemo = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent g) {
				// TODO Auto-generated method stub
				JButton btnMemo = (JButton) g.getSource();
				String sFunction = btnMemo.getText();

				if (sFunction.equals("MS")) {
					dMemo = Double.parseDouble(txtIn.getText());
				}
				if (sFunction.equals("MR")) {
					txtIn.setText("" + dMemo);
				}
				if (sFunction.equals("MC")) {
					dMemo = 0;
				}
				fAppend = false;
			}
		};
		btnStand[0][0].addActionListener(actMemo);
		btnStand[0][1].addActionListener(actMemo);
		btnStand[0][2].addActionListener(actMemo);

		ActionListener actCal = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnCal = (JButton) e.getSource();
				dCalcu = Double.parseDouble(txtIn.getText());
				sCal = btnCal.getText();
				fAppend = false;
			}
		};
		btnStand[5][2].addActionListener(actCal);
		btnStand[4][3].addActionListener(actCal);
		btnStand[3][3].addActionListener(actCal);
		btnStand[2][3].addActionListener(actCal);

		ActionListener acEqual = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (sCal.equals("+")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu + dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("-")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu - dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("*")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu * dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
				if (sCal.equals("/")) {
					double dCal = Double.parseDouble(txtIn.getText());
					double result = dCalcu / dCal;
					txtIn.setText("" + result);
					fAppend = false;
				}
			}
		};
		btnStand[5][4].addActionListener(acEqual);
		
		ActionListener actCl = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtIn.setText("0");
				fAppend = false ;
			}
		};
		btnStand[1][2].addActionListener(actCl);
	}

	String[][] sStard = { { "MC", "MR", "MS", "M+", "M-" },
			{ "<-", "CE", "C", "+/-", "sqrt" }, { "7", "8", "9", "/", "%" },
			{ "4", "5", "6", "*", "1/x" }, { "1", "2", "3", "-", "" },
			{ "0", ".", "+", "", "=" }, };
	String[][] sScientific = { { "", "Inv", "ln", "(", ")", },
			{ "Int", "sinh", "sin", "x^2", "n!" },
			{ "dms", "cosh", "cos", "x^y", "x^1/y" },
			{ "PI", "tanh", "tan", "x^3", "x^1/3" },
			{ "F-E", "Exp", "Mod", "log", "10^x" } };
	String[][] sProgram = { { "", "Mod", "A", }, { "(", ")", "B" }, { "RoL", "RoR", "C" }, { "Or", "Xor", "D" },
			{ "Lsh", "Rsh", "E" }, { "Not", "And", "F" } };
	JButton[][] btnProg = new JButton[6][3];
	JButton[][] btnScien = new JButton[5][5];
	JButton[][] btnStand = new JButton[6][5];
	JPanel panStand = new JPanel();
	JPanel panScien = new JPanel();
	JPanel panProg = new JPanel();
	int w = 40, h = 40, d = 10;
	int x = 0, y = 0;

	public void initStand() {
		panStand.setLayout(null);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnStand[i][j] = new JButton(sStard[i][j]);
				panStand.add(btnStand[i][j]);
				btnStand[i][j].setBounds(x, y, w, h);
				btnStand[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		btnStand[5][4].setSize(w, h + d + h);
		btnStand[5][0].setSize(w + d + w, h);
		btnStand[5][1].setLocation(w + d + w + d, y - h - d);
		btnStand[5][2].setLocation(w + d + w + d + w + d, y - h - d);
		btnStand[5][4].setLocation(x - 50, y - 100);
		btnStand[4][4].setVisible(false);
		btnStand[5][4].setVisible(true);
		this.add(panStand);
		displayMode(1);
	}

	public void initScien() {
		panScien.setLayout(null);
		y = 0;
		for (int i = 0; i < 5; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnScien[i][j] = new JButton(sScientific[i][j]);
				panScien.add(btnScien[i][j]);
				btnScien[i][j].setBounds(x, y, w, h);
				btnScien[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panScien);
		displayMode(2);
	}
	
	public void initProg(){
		panProg.setLayout(null);
		panPr00.add(hex);
		panPr00.add(dec);
		panPr00.add(oct);
		panPr00.add(bin);
		panPr00.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panPr01.add(qword);
		panPr01.add(dword);
		panPr01.add(word);
		panPr01.add(bit);
		panPr01.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 3; j++) {
				btnProg[i][j] = new JButton(sProgram[i][j]);
				panProg.add(btnProg[i][j]);
				btnProg[i][j].setBounds(x, y, w, h);
				btnProg[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panProg);
		displayMode(3);
	}

	public void displayMode(int mode) {
		if (mode == 2) {
			panProg.setVisible(false);
			panScien.setVisible(true);
			panStand.setVisible(true);
			panScien.setBounds(10, 150, x, y);
			panStand.setBounds(270, 100, x, y);
			txtIn.setBounds(10, 10, w + 460, h + 40);
			panbox.setVisible(true);
			this.setSize(530, 460);
		} else if (mode == 1) {
			panScien.setVisible(false);
			panProg.setVisible(false);
			panStand.setVisible(true);
			panStand.setBounds(10, 110, x, y);
			txtIn.setBounds(10, 10, w + 200, h + 50);
			panbox.setVisible(false);
			this.setSize(270, 460);
		} else if (mode == 3) {
			panScien.setVisible(false);
			panStand.setVisible(true);
			// box.setVisible(false);
			panPr00.setSize(80, 140);
			panPr00.setLocation(10,200);
			panPr00.setVisible(true);
			panPr01.setSize(80, 140);
			panPr01.setLocation(10, 350);
			panPr01.setVisible(true);
			txtIn.setBounds(10, 10, w + 450, h + 40);
			panProg.setVisible(true);
			panProg.setBounds(100, 150, x, y);
			panStand.setBounds(260, 100, x , y);
			this.setSize(520, 460);
		}
	}

	public void inittalizeMenu() {
		bar.add(view);
		bar.add(edit);
		bar.add(help);

		view.add(stand);
		view.addSeparator();
		view.add(scien);
		view.add(programmer);
		view.add(exit);

		edit.add(copy);
		edit.add(paste);

		help.add(about);

		setJMenuBar(bar);
		// stand.addActionListener(action);
		// scien.addActionListener(action);
		// programmer.addActionListener(action);
		// exit.addActionListener(action);
		// copy.addActionListener(action);
		// paste.addActionListener(action);
		// about.addActionListener(action);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cHW01_Calculator_A mainWindow = new cHW01_Calculator_A();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}

}
