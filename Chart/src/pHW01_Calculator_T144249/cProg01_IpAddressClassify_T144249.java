/**
 * 
 */
package pHW01_Calculator_T144249;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author US
 *
 */
public class cProg01_IpAddressClassify_T144249 extends JFrame {
	JTextField[] txtInput[];
	JButton btnAdd;
	JLabel lblInputType, lblInput[];
	JRadioButton optSelect1, optSelect2, optSelect3;
	ButtonGroup btgSelect;
	//String[] number = new String[12];
	//cProg03_DoubleBarChart_paint_T144249 pan01 = new cProg03_DoubleBarChart_paint_T144249();
	int w = 0;

	public void cProg03_DoubleBarChart_Program_T144249() {
		ButtonGroup btgSelect = new ButtonGroup();
		JRadioButton optSelect1 = new JRadioButton("Type 1"), optSelect2 = new JRadioButton("Type 2"),
				optSelect3 = new JRadioButton("Type 3");
		JLabel lblInputType = new JLabel("Select type input: ");
		JTextField[] txtInput = new JTextField[12];
		JButton btnAdd = new JButton("Add");

		for (int i = 0; i < 12; i++) {
			add(txtInput[i]);
		}
		add(btnAdd);
		add(lblInputType);

		add(optSelect1);
		add(optSelect2);
		add(optSelect3);
		btgSelect.add(optSelect1);
		btgSelect.add(optSelect2);
		btgSelect.add(optSelect3);
		optSelect1.setSelected(true);

		lblInputType.setBounds(10, 10, 120, 25);
		optSelect1.setBounds(140, 10, 90, 25);
		optSelect2.setBounds(250, 10, 90, 25);
		optSelect3.setBounds(360, 10, 90, 25);

		for (int j = 0; j < 12; j++) {
			txtInput[j].setBounds(10 + w, 40, 30, 25);
			w = w + 20;
		}

		btnAdd.setBounds(200, 70, 80, 25);
	}

	public cProg01_IpAddressClassify_T144249() {
		this.setTitle("T144249 - Simple Bar Chart");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(550, 500));
		pack();

		cProg03_DoubleBarChart_Program_T144249();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cProg01_IpAddressClassify_T144249 mainWindow = new cProg01_IpAddressClassify_T144249();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setVisible(true);
	}
}
