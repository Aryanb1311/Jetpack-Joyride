
public class ScoreSetters {

	protected Double highScore;

	
	public ScoreSetters () {
		highScore = 0.0;
	}
	
	public ScoreSetters(double hScore) {
		this.highScore = hScore;
	}
	
	
	// get and sets
	public Double getHighScore() {
		return highScore;
	}

	public void setHighScore(Double newValue) { // set new top score?
		this.highScore = newValue;
	}

	public String toString() {
        return ("Highscore: " + highScore) ;
}}