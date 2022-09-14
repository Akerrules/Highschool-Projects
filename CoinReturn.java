
// **********************************************************
// * Program Title: Coin Return
// * Programmer: Aditya kandel 
// * Course: ICS 3U1
// *
// * Date: date last modified
// * Assignment: Programming Problems Set 1
// * Question: Part B 1
// *
// * Filename: CoinReturn.java
// * Description: Explain what your program does.
// ***********************************************************

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CoinReturn {

	static String Change;
	static int toonies;
	static int loonies;
	static int loonies_total;
	static int nickels;
	static double Change2;
	static int toonies_total;
	static int nickels_total;
	static int quarters;
	static int quarters_total;
	static int dimes_total;
	static int dimes;

	// main
	public static void main(String[] agrs) {

		toonies = 200;
		loonies = 100;
		nickels = 5;
		quarters = 25;
		dimes = 10;
		/********** Setting frame properties **************/
		JFrame frame = new JFrame("Coin Return");
		frame.setSize(400, 200);
		frame.setLocation(300, 200);
		/********** Setting frame properties **************/
		ImageIcon img = new ImageIcon("images/coins2.jpg");
		JLabel background = new JLabel("Coin return", img, JLabel.CENTER);
		JPanel panel = new JPanel(null);
		int y = img.getIconHeight();
		int x = img.getIconWidth();
		background.setBounds(0, 0, x, y + 10);

	
		JOptionPane.showMessageDialog(frame,
				"\tWelcome to Coin return \nYou can convert money into coins \nInput amount of coin in the text field to show the change  ");
		JLabel title = new JLabel("Coin Return ");
	
		title.setBounds(200, 0, 100, 50);

		JTextField TextField = new JTextField(20);
		JTextArea textarea = new JTextArea(10, 10);
		JCheckBox checkbox = new JCheckBox();
	
		/********** Setting text area properties **************/
		textarea.setEditable(false);
		textarea.setBackground(Color.black);
		textarea.setForeground(Color.white);
		textarea.setBounds(25, 50, 400, 250);
		Font font = textarea.getFont();
		float size = font.getSize() + 10.0f;
		textarea.setFont(font.deriveFont(size));
		textarea.setOpaque(false);
		/********** Setting text area properties **************/
	
		/********** Setting text field properties **************/
		TextField.setFont(font.deriveFont(size));
		TextField.setBounds(25, 350, 200, 25);
		/********** Setting text field properties **************/
	
		/********** Setting Check box properties **************/
		checkbox.setFont(font.deriveFont(size));
		checkbox.setBounds(27, 400, 400, 20);
		checkbox.setText("Click here to input money in dollars");
		checkbox.setOpaque(false);
		checkbox.setFocusable(false);
		/********** Setting Check box properties **************/
		
		/********** Setting adding component to panel . **************/
		panel.add(background);
		panel.add(title);
		panel.add(textarea);
		panel.add(checkbox);
		panel.add(TextField);
		/********** Setting adding component to panel . **************/
		
		/********** Setting Z order. **************/
		panel.setComponentZOrder(background, 3);
		panel.setComponentZOrder(textarea, 1);
		panel.setComponentZOrder(TextField, 1);
		panel.setComponentZOrder(checkbox, 1);
		/********** Setting Z order. **************/
		textarea.setText("Cents mode Activated");
		TextField.setText("Input Money value");

		TextField.addMouseListener(new MouseAdapter() {
			boolean check = true;

			@Override
			public void mouseClicked(MouseEvent e) {

				if (check == true) {
					TextField.setText("");

				}
				if (TextField.getText().isEmpty() == false) {
					check = false;

				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (TextField.getText().isEmpty() == true) {
					TextField.setText("Input Money value");
				}

			}
		});
		TextField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Change = TextField.getText();
				try {// if is number
					// Integer.parseInt(Change);
					Double.parseDouble(Change);
					TextField.setBackground(Color.WHITE);
					if (!checkbox.isSelected()) {
						Change2 = Double.parseDouble(Change);
						textarea.setText("Cents mode Activated");
					} else if (checkbox.isSelected()) {

						Change2 = Double.parseDouble(Change) * 100;
						textarea.setText("Dollars mode Activated");
					}
				} catch (NumberFormatException e) {

					TextField.setBackground(Color.red);

					textarea.setText("Please Input number only ");

				}
				//divide the input by 2 or 200 to get toonies
				toonies_total = (int) (Change2 / toonies);

				textarea.append("\nToonies:  " + toonies_total);
				//if there is change left , divide the chnage by 1 or 100 to get loonies 
				loonies_total = (int) (Change2 - (toonies * toonies_total));
				textarea.append("\nLoonies:  " + loonies_total / loonies);
				
				quarters_total = (int) (Change2 - (loonies * (loonies_total / loonies) + (toonies * (toonies_total))));
				//if 100 is greater than change and loonies ==100 then change is quarters total 
				if (Change2 < 100 && loonies == 100) {

					quarters_total = (int) Change2;
					//else same for dollars 
				} else if (Change2 < 1 && loonies == 1) {
					quarters_total = (int) Change2;
				}
				//If change is left get quarter
				if (Change2 - (((loonies_total / loonies) + (toonies_total / loonies)) * 100) > 0) {

					textarea.append("\nQuarters: " + "" + quarters_total / quarters);
				} else {
					//else display 0
					textarea.append("\nQuarters: " + 0);
				}
				//if change if left get dimes 
				dimes_total = (int) (Change2 - ((loonies_total / loonies) * loonies
						+ (quarters_total / quarters) * quarters + (toonies_total) * toonies));

				if (dimes_total > 0) {
					textarea.append("\nDimes:    " + dimes_total / dimes);
				} else {
					//else display 0
					textarea.append("\nDimes:    " + 0);
				}
				//if change is still left get nickelle
				nickels_total = (int) (Change2
						- ((loonies_total / loonies) * loonies + (quarters_total / quarters) * quarters
								+ (toonies_total) * toonies + (dimes_total / dimes) * dimes));

				if (nickels_total > 0) {
					textarea.append("\nNickels:   " + /* Math.round */(nickels_total / nickels));

				}
				//else display 0 
				else {
					textarea.append("\nNickels:   " + 0);
				}

			}

		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setVisible(true);
		frame.setSize(x, y + 20);
		frame.setResizable(false);

	}

}
