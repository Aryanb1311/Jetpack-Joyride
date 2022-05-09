import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/// SPRITES WILL BE ADDED LATER!!!! 
//-------Connect it to IMG folder with sprites ****
public class CorruptPc extends JFrame implements ActionListener {

	obstacle corruptPc;
	Graphics graphics;
	Image image; // this will be replaced with a sprite later on called PC (in a separate folder)


	private static int FW = 400;
	private static int FH = 400;
	int x = 130;
	int y = 300;
	int w = 100; // the time the pc will take to move
	int v = 0; 

	int CorruptPcX, corruptPcY;

	panel panel = new panel();

	
	public CorruptPc() {

		ActionListener movement = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				//move up
				if (y >= 240|| v==0){
					y -= 10;
					v++; // test
					System.out.println("What : " + v ); // v will stop at 6				
					panel.repaint();
				}  // move down
				if (v >= 6 || y==240) { 
					/// once it reaches its max point, this is where the attack happens.
					if(y==240) {
						// insert sprite change here, using swap??? 
						try {
							Thread.sleep(2000); // pause the graphics for 1 seconds
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					// move back down until off screen
					y -= -20;
					System.out.println(y);
					// swap back to other sprite sprite here
						System.out.println("moving down");

				}
			}
			
		};

		corruptPc = new obstacle (CorruptPcX, corruptPcY, x, y, Color.BLUE);
		
		Timer speed = new Timer(w, movement); // 1 second 
		speed.start();
		add(panel);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class panel extends JPanel {

		protected void paintComponent(Graphics g) { // square will be replaced with image sprites // maybe do a swap();
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g);
			g.fillRect(x, y, 75, 50);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FW, FH);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CorruptPc();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
