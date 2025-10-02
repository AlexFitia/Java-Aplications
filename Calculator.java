package git;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
	int boardWidth = 360;
	int boardHeight = 540;

	JFrame frame = new JFrame("Calculator");
	JLabel DisplayLabel = new JLabel();
	JPanel DisplayPanel = new JPanel();
	JPanel ButtonsPanel = new JPanel();

	String A = "0";
	String operator = null;
	String B = null;

	String[] buttonValues = { "AC", "+/-", "%", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "0",
			".", "√", "=" };
	String[] rightSymbols = { "÷", "×", "-", "+", "=" };
	String[] topSymbols = { "AC", "+/-", "%" };

	Color customDarkGrey = new Color(212, 212, 210);
	Color customLightGrey = new Color(80, 80, 80);
	Color customBlack = new Color(28, 28, 28);
	Color customOrange = new Color(255, 149, 0);

	Calculator() {
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		DisplayLabel.setBackground(customBlack);
		DisplayLabel.setForeground(customDarkGrey);
		DisplayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
		DisplayLabel.setHorizontalAlignment(JLabel.RIGHT);
		DisplayLabel.setText("0");
		DisplayLabel.setOpaque(true);

		DisplayPanel.setLayout(new BorderLayout());
		DisplayPanel.add(DisplayLabel);
		frame.add(DisplayPanel, BorderLayout.NORTH);

		ButtonsPanel.setLayout(new GridLayout(5, 4, 0, 0));
		ButtonsPanel.setBackground(customBlack);
		frame.add(ButtonsPanel);

		for (int i = 0; i < buttonValues.length; i++) {
			JButton button = new JButton();
			String buttonValue = buttonValues[i];
			button.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 30));
			button.setText(buttonValue);
			button.setFocusable(false);

			if (Arrays.asList(topSymbols).contains(buttonValue)) {
				button.setBackground(customLightGrey);
				button.setForeground(customBlack);
			} else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
				button.setBackground(customOrange);
				button.setForeground(customBlack);
			} else {
				button.setBackground(customDarkGrey);
				button.setForeground(customBlack);
			}

			button.setOpaque(true);
			button.setContentAreaFilled(true);
			button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			ButtonsPanel.add(button);

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton button = (JButton) e.getSource();
					String buttonValue = button.getText();
					if (Arrays.asList(rightSymbols).contains(buttonValue)) {
						if (buttonValue == "=") {
							if (A != null) {
								B = DisplayLabel.getText();
								double numA = Double.parseDouble(A);
								double numB = Double.parseDouble(B);

								if (operator == "+") {
									DisplayLabel.setText(RemoveZeroDecimal(numA + numB));
								} else if (operator == "-") {
									DisplayLabel.setText(RemoveZeroDecimal(numA - numB));
								} else if (operator == "×") {
									DisplayLabel.setText(RemoveZeroDecimal(numA * numB));
								} else if (operator == "÷") {
									DisplayLabel.setText(RemoveZeroDecimal(numA / numB));
								}
								ClearAll();
							}
						} else if ("÷×+-".contains(buttonValue)) {
							if (operator == null) {
								A = DisplayLabel.getText();
								DisplayLabel.setText("0");
								B = "0";
							}
							operator = buttonValue;

						}
					} else if (Arrays.asList(topSymbols).contains(buttonValue)) {
						if (buttonValue == "AC") {
							ClearAll();
							DisplayLabel.setText("0");
						} else if (buttonValue == "+/-") {
							double numDisplay = Double.parseDouble(DisplayLabel.getText());
							numDisplay = numDisplay * -1;
							DisplayLabel.setText(RemoveZeroDecimal(numDisplay));
						} else if (buttonValue == "%") {
							double numDisplay = Double.parseDouble(DisplayLabel.getText());
							numDisplay = numDisplay / 100;
							DisplayLabel.setText(RemoveZeroDecimal(numDisplay));
						}
					} else {
						if (buttonValue == ".") {
							if (!DisplayLabel.getText().contains(buttonValue)) {
								DisplayLabel.setText(DisplayLabel.getText() + buttonValue);
							}
						} else if ("0123456789".contains(buttonValue)) {
							if (DisplayLabel.getText() == "0") {
								DisplayLabel.setText(buttonValue);
							} else {
								DisplayLabel.setText(DisplayLabel.getText() + buttonValue);
							}
						} else if (buttonValue == "√") {
							double numDisplay = Double.parseDouble(DisplayLabel.getText());
							numDisplay = Math.pow(numDisplay, 0.5);
							DisplayLabel.setText(RemoveZeroDecimal(numDisplay));
						}

					}
				}
			});
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1),
							BorderFactory.createMatteBorder(2, 2, 4, 4, new Color(50, 50, 50, 100))));
					button.setBackground(button.getBackground().darker());
				}

				public void mouseExited(java.awt.event.MouseEvent evt) {
					button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

					if (Arrays.asList(topSymbols).contains(button.getText())) {
						button.setBackground(customLightGrey);
					} else if (Arrays.asList(rightSymbols).contains(button.getText())) {
						button.setBackground(customOrange);
					} else {
						button.setBackground(customDarkGrey);
					}
				}
			});

		}
	}

	void ClearAll() {
		A = "0";
		operator = null;
		B = null;
	}

	String RemoveZeroDecimal(double numDisplay) {
		if (numDisplay % 1 == 0) {
			return Integer.toString((int) numDisplay);
		} else {
			return Double.toString(numDisplay);
		}

	}
}
