package kalkukoordenation;


/**
 *  
 *  
 *  Die Klasse Position stellt die Position "Koodinaten " von Secpoints dar. 
 * In der Klasse Position  werden  die Daten zwischen gespeichert, und werden verwendet um die Daten (Position_id, Punkt x, Punkt y, Halbzeit ) 
 * für die Tabelle Secpoints zu berechnenn.Diese Position werden mit der Klasse MatchTime benutzt, um die Secpoints zu definieren.
 * 
 * @author wildt
 * @version 1.0
 * 
 */

public class Position {

	private int id;
	private float x;
	private float y;
	private int halfTime;
	
	/**
	 * gibt den Id von einen Position zurück
	 * @return
	 */

	public int getId() {
		return id;
	}
	/**
	 *setze eine Id
	 * @return
	 */

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * gibt den Punkt x zurück 
	 * @return x
	 */

	public float getX() {
		return x;
	}
	/**
	 * setze Punkt x 
	 * @return x
	 */

	public void setX(float x) {
		this.x = x;
	}
	/**
	 *  gibt den Punkt y zurück
	 * @return y
	 */

	public float getY() {
		return y;
	}
	/**
	 * setze Punkt y
	 * @return y
	 */

	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * gibt Halbzeit zurück  
	 * @return Halbzeit
	 */

	public int getHalfTime() {
		return halfTime;
	}
	
	/**
	 * setze Halbzeit
	 * @return Halbzeitt
	 */

	public void setHalfTime(int halfTime) {
		this.halfTime = halfTime;
	}
	
	

	public void print() {
		System.out.println("PositionList - Id: " + id + " Halbzeit: " + halfTime + " X: " + x + " Y: " + y);
	}

}
