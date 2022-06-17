import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

// Chemicollia boss battle 1.3 

public class ChemicolliaDifficulty extends Words {
	static String userTerm = "";
	static String bossTerm = "";
	static boolean bossBeat = false;

	static int counter = 0;
	static int qNum = 0;

	static JLabel label, qLabel, timerLabel;
	static JButton Enter;
	static JTextField textfield;
	static JPanel panel;


	public static JFrame variables() {
		
		Random random = new Random(); //setting up random numbers
		qNum = random.nextInt(5);
		textfield = new JTextField(16);
		qLabel = new JLabel("");
		label = new JLabel(""); // text prompting user for input
		Enter = new JButton("Enter"); // buttons for user to guess with
		timerLabel = new JLabel("Timer: ");

		panel = new JPanel(); // creates new panel for the interface
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); //panel dimensions
		panel.add(label);
		panel.add(textfield);
		panel.add(qLabel); //figure out how to format buttons later im not in the mood
		panel.add(Enter); // adding number and quit/new game/score buttons to panel
		panel.add(timerLabel);

		JFrame frame = new JFrame(); // creates new frame for the panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exits program upon closing the window
		frame.setSize(300, 300); //setting frame dimensions
		frame.getContentPane().add(panel); //adding panel to frame
		Countdown();
		return frame;
	}

	static void gameOver() {
		timerLabel.setVisible(false);
		if (bossBeat = true) {
			label.setText("Congratulations");
			qLabel.setText("");
			textfield.setText("");
			textfield.setEditable(false);
		} else {
		// changing the labels to fit game over screen
		qLabel.setText("");
		label.setText("Game Over");
		//making text box blank and uneditable
		textfield.setText("");
		textfield.setEditable(false);
		}
	}

	public static void Countdown() {

		Timer timer = new Timer(); //set up a new timer ykyk

		timer.scheduleAtFixedRate(new TimerTask() {
			int c = 25; //countdown starts at 25 sec

			public void run() {

				System.out.print("Countdown: " + c + System.lineSeparator());

				c--;
				timerLabel.setText("Timer: " + c);
				if (c < 0 || bossBeat == true) {
					timer.cancel();
					System.out.print("Time's up!");
					gameOver();
				} 
			}
		}, 0, 1000); //controls how fast the countdown goes down
	}



	static void youWin() {
		JLabel youWinLbl = new JLabel("YOU WIN! +1 LIFE");
		Dimension size = youWinLbl.getPreferredSize();
		youWinLbl.setBounds(100, 150, size.width, size.height);
		panel.add(youWinLbl);
		youWinLbl.setVisible(true);
		System.out.println("win screen activated");
	}

	public static void main(String[] args) { 
		JFrame frame = variables(); //importing variables to JFrame and making JFrame visible
		frame.setVisible(true);
		new ChemicolliaDifficulty();


		Enter.addActionListener(new ActionListener() { //if enter is pressed
			public void actionPerformed(ActionEvent e) {
				userTerm = textfield.getText().replaceAll("[^a-zA-Z0-9]", "").trim().toLowerCase();
				terms();
				//qLabel.append("you said " + userTerm); //change this to if userTerm = bossTerm

				if (counter == 0) {

					bossTerm = easy[qNum];
					label.setText(bossTerm);
					if (userTerm.equals(bossTerm)) {
						counter++;
						qLabel.setText("You're right! Next term...");
						textfield.setText("");

						Random random = new Random();
						qNum = random.nextInt(5); //sets qnum as another random number instead of going through all questions 1, 2, etc.
						bossTerm = medium[qNum];
						label.setText(bossTerm);
					} 
					else if (!userTerm.equals(bossTerm)){
						qLabel.setText("Wrong.");
						textfield.setText("");
					}
				}
				else if (counter == 1) { //how to make the level progress, if i remove the else's it progresses but incorrectly
					;
					if (userTerm.equals(bossTerm)) {
						qLabel.setText("You're right! Final term...");
						counter++;
						textfield.setText("");
						Random random = new Random();
						qNum = random.nextInt(5);
						bossTerm = hard[qNum];
						label.setText(bossTerm);
					} 
					else if (!userTerm.equals(bossTerm)){
						qLabel.setText("Wrong.");
						textfield.setText("");
					}

				}
				else if (counter == 2) {
					label.setText(bossTerm);
					if (userTerm.equals(bossTerm)) {
						bossBeat = true;
						youWin();
					}
					else if (!userTerm.equals(bossTerm)){
						qLabel.setText("Wrong.");
						textfield.setText("");
					}
				}
			}
		});

		terms(); //need linked list, need to figure out < nvm we did not need a linked list

		if (counter == 0) {
			bossTerm = easy[qNum];
			label.setText(bossTerm);
		} if (userTerm.equals(bossTerm)) {
			counter++;
			qLabel.setText("You're right! You have " + counter + " point!");
		} if (counter == 2) {
			bossTerm = hard[qNum];
			label.setText(bossTerm);
		}
	}}

