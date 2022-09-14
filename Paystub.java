import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


//**********************************************************
//* Program Title: PayStub
//* Programmer: Aditya 
//* Course: ICS 3U1
//*
//* Date: 3/10/2019
//* Assignment: Programming Problems Set 1
//* Question: Question A2
//*
//* Filename: paystub.java
//* Description: calculates the gross pay and net pay with detuctuion.
//***********************************************************
public class Paystub implements ActionListener {
	JFrame frame;
	JPanel panel;
	JLabel title;
	JTextField name;
	static JTextArea name_tag;
	JTextArea text;
	JTextArea detuction;
	JTextField hours;
	JLabel message;
	JButton btn;
	
	JTextField paywage;
	static JTextField date;
	static JCheckBox checkbox;
	static String name2;

	JComboBox comboBox;
	int x;
	int y;
	String[] payment = { "Daily", "Weekly", "Montly", "Annually" };
	DecimalFormat format = new DecimalFormat("##.00");
	Font font = new Font("impact", Font.PLAIN, 20);
	Font font2 = new Font("impact", Font.PLAIN, 18);
	ImageIcon img;
	JLabel background;

	public Paystub() {
		frame = new JFrame("Pay Stub");
		panel = new JPanel(null);

		AddComponents();
		panel.setComponentZOrder(background, 11);
		setText();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.setSize(x + 10, y + 40);
		frame.setVisible(true);
	}

	public void AddComponents() {
		title = new JLabel("GUI Demonstration # 4");
		title.setHorizontalAlignment(JLabel.CENTER);
		name = new JTextField();
		name.setBounds(5, 140, 130, 40);
		name_tag = new JTextArea();
		name_tag.setBounds(75, 233, 700, 25);
		name_tag.setOpaque(false);
		name_tag.setEditable(false);
		hours = new JTextField();
		hours.setBounds(150, 140, 130, 40);
		// String after = before.trim().replaceAll(" +", " ");
		paywage = new JTextField();
		paywage.setBounds(310, 140, 130, 40);
		date = new JTextField();
		date.setBounds(470, 140, 130, 40);
		date.setEnabled(false);
		comboBox = new JComboBox(payment);
		comboBox.setBounds(750, 99, 200, 50);
		text = new JTextArea();
		text.setBounds(5, 300, 450, 400);
		text.setEditable(false);
		text.setOpaque(false);
		detuction = new JTextArea();
		detuction.setBounds(460, 300, 200, 200);
		detuction.setOpaque(false);
		img = new ImageIcon("images/paystub.png");
		background = new JLabel("Coin return", img, JLabel.CENTER);
		background.setBounds(0, 0, 700, 500);
		checkbox = new JCheckBox();
		checkbox.setBounds(470, 180, 250, 50);
		checkbox.setOpaque(false);
		date.setBackground(Color.gray);
		btn = new JButton();
		btn.addActionListener(this);
		btn.setBounds(640, 140, 50, 20);

		imgsize();
		panel.add(title, BorderLayout.NORTH);
		panel.add(name);
		panel.add(name_tag);
		panel.add(hours);
		panel.add(paywage);
		panel.add(detuction);
		panel.add(comboBox);
		panel.add(checkbox);
		panel.add(background);
		panel.add(date);
		panel.add(text);

		panel.add(btn);

	}

	public void imgsize() {
		x = img.getIconWidth();

		y = img.getIconHeight();
		System.out.println(x + "" + y);

	}

	public void setText() {

		name.setFont(font);
		hours.setFont(font);
		name_tag.setFont(font);
		paywage.setFont(font);
		text.setFont(font);
		detuction.setFont(font2);
		comboBox.setFont(font);
		comboBox.setPreferredSize(new Dimension(120, 30));
		comboBox.setEditable(true);
		checkbox.setText("Click here to input custom date");
	}

	public void calculation() {
		name2 = name.getText().trim().replaceAll(" +", " ");
		String hours1 = hours.getText();
		String rate = paywage.getText();
		String datet = date.getText();
		double gross_pay = Double.valueOf(format.format(((Double.parseDouble(rate) * Double.parseDouble(hours1)))));
		double tax = Double.valueOf(format.format((gross_pay * 0.18)));
		double cpp = Double.valueOf(format.format((gross_pay * 0.0498)));
		double ei = Double.valueOf(format.format((gross_pay * 0.0615)));
		text.setText("Gross pay\t " + "$" + rate + "\t" + hours1 + "\t$" + gross_pay + "\n\n\n\n\n\nGross pay\t\t\t$"
				+ gross_pay);
		detuction.setText("TAX: $" + (tax) + "\n\nCPP: $" + cpp + "\n\nEI: $" + ei);
		detuction.append("\n\t  $" + Double.valueOf(format.format(tax + cpp + ei)));
		detuction.append("\n\n\t $" + Double.valueOf(format.format((gross_pay - (tax + cpp + ei)))));

		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

		Date today = new Date();
		if (date.isEnabled() == false) {
			name_tag.setText(name2 + "\t\t\t\t     " + formatter.format(today));
		} else {

			name_tag.setText(name2 + "\t\t\t\t     " + datet);
		}
	}

	public void actionPerformed(ActionEvent event) {
		if (!name.getText().isEmpty()){
		calculation();
		}else{
			
		}
	}

	public static void main(String[] args) {

		new Paystub();

		checkbox.addMouseListener(new MouseAdapter() {
//Check box click listener
			@Override
			public void mouseClicked(MouseEvent e) {

				if (!checkbox.isSelected()) {
					date.setEnabled(false);
					date.setBackground(Color.gray);
				} else {
					date.setEnabled(true);
					date.setBackground(Color.white);
				}

			}
		});

	}
}
