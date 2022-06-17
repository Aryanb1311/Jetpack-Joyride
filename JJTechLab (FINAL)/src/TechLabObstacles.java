import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/*checklist: make the lasers rotate (not done)
 * make them bounce off the wall (DONE)
 * SIZE IS SUBJECT TO CHANGE i just needed a visual
 */

public class TechLabObstacles extends JFrame {

	obstacles discoLaser;
	Graphics graphics;
	Image image;

	private static int FW = 400;
	private static int FH = 400;
	int discoLaserX = 260;
	int discoLaserY = 160;
	int discoLaserWidth = 70;
	int discoLaserHeight = 10;
	int speedx = 5;
	int speedy = -5;
	
	//reach x value off screen, counter++ change angle (speedy)
	//create variable called speedy random
	//make speedy random into mathrandom (check chemicolliadifficulty)
	//figure out where the laser is off screen (certain x value)
	//create if statement for if > reaches x, counter++
	//counter == num, laser returns to og x y, y = speedy random

	laserPanel laserPanel = new laserPanel();

	public TechLabObstacles() {
		Random random = new Random();
		ActionListener laserMovement = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (discoLaserY > (FH-10) || discoLaserY <= 0) {
					speedy = -speedy;
				} else if (discoLaserX < -10) {
					discoLaserX = 260;
					discoLaserY = 160;
					speedy = speedy +- random.nextInt(15);
				}
				
				discoLaserX = discoLaserX - speedx;
				discoLaserY = discoLaserY - speedy;
				laserPanel.repaint();
				discoLaser.setLocation(discoLaserX, discoLaserY);
				
			}
		};
		Timer laserSpeed = new Timer(15, laserMovement);
		laserSpeed.start();
		add(laserPanel);
		discoLaser = new obstacles (discoLaserX, discoLaserY, discoLaserWidth, discoLaserHeight, Color.GREEN);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private class laserPanel extends JPanel {

		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			image = createImage(this.getWidth(), this.getHeight());
			graphics = image.getGraphics();
			g.drawImage(image, 0, 0, this);

			discoLaser.draw(g);
		}

		public Dimension getPreferredSize() {
			return new Dimension(FW, FH);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TechLabObstacles();
			}
		});

	}
}