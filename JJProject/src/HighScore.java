// version 1.1
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

class Stopwatch1 {
	private final long nanoSecondsPerSecond = 1000000000;

	private long watchStart = 0;
	private long watchStop = 0;
	private boolean stopWatchRunning = false;


	public void start () {
		this.watchStart = System.nanoTime();
		this.stopWatchRunning = true;
	}


	public void stop () {
		this.watchStop = System.nanoTime();
		this.stopWatchRunning = false;
	}

}

public class HighScore {

	static int lives = 1; // starts with 1
	static int bosses = 1; // add counter to count # of enemies defeated, it starts with 1 bc if it starts with 0 multiplier will not work??
	static int powerUps = 1; // add counter, doesn't start with 0 bc if it did multiplier wont work.


	// bonuses for bosses??
	static double bonus = 1.15;
	static double bonus2 = 1.25;
	static double bonusMax = 1.5;

	//storing the high score
	static double i = 0;
	static ScoreSetters[] highScores = new ScoreSetters[1];
	static ScoreSetters[] current = new ScoreSetters[100];
	static double userScore[] = {};
	static double hScore[];
	//storing user's current score


	public static void main(String[] args) {

		Stopwatch1 highScore = new Stopwatch1();
		highScore.start();
		highScore.stop();

		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			double timePassed = 0;


			void finish() {

				double score = timePassed * bosses * powerUps;

				if (bosses == 2) {
					score = timePassed * bosses * powerUps * bonus;
				} else if (bosses == 3) {
					score = timePassed * bosses * powerUps * bonus2;
				} else if (bosses >= 4){
					score = timePassed * bosses * powerUps * bonusMax;
				} 


				// 
				System.out.println("Final score: " + score);
			     try 
			        {
			            PrintWriter x; 
			            x = new PrintWriter (new FileWriter ("data.txt")); 
	
			            {
			                x.println ("Highscore: " + score); //write data to file
			            }
			           
			            x.close ();
			            System.out.println ("data.txt file created with Score!");
			        }
			        catch (Exception e) 
			        {
			            System.out.println ("no txt file created.");
			        } 


			};

			public void run() {
				System.out.print("Score: " + timePassed + System.lineSeparator());
				timePassed++;

				if (lives == 0) {
					timer.cancel(); // stops timer which allows you to 
					//System.out.println("total score: " + timePassed);  // checking to see if the score displays, it works loll

					finish(); // commence multiplier

				}
			}
		
		}, 0, 500);

		}
	}