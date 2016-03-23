package kalkukoordenation;

/**
 * Die Klasse MachtTime bezeichnet die Zeitstempel in Sekunden und die Halbzeit, die zu  ein Action gehört.Diese
 * Felder werden aus der Datei vistrack-actions entnomen.Es wird benutz um die Secpoints zu zuordenen.
 * 
 * 
 * @author wildt
 * @version 1.0
 * 
 */


public class MatchTime {

	private int seconds;
	private int halfTime;
	private int actionId;
	
	
	/**
	 * Inisialisiert ein Objekt von Typ MachtTime.
	 * 
	 * seconds: Zeitstempel in Sekunden.
	 * halfTime: entweder 1 oder 2.
	 * actionId: ID von einen Action 
	 * 
	 */

	public MatchTime(int seconds, int halfTime, int actionId) {
		this.seconds = seconds;
		this.halfTime = halfTime;
		this.actionId = actionId;
	}
	
	/**
	 * gibt die Zeit  zurück. 
	 * @return seconds.
	 *  
	 */	

	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * setze die Zeit.  
	 * @param seconds
	 *  
	 */	

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	/**
	 * gibt die Halbzeit zurück. 
	 * @return halfTime .
	 *  
	 */	

	public int getHalfTime() {
		return halfTime;
	}
	
	/**
	 * setze die Halbzeit  
	 * @param id.
	 *  
	 */	

	public void setHalfTime(int halfTime) {
		this.halfTime = halfTime;
	}
	/**
	 * gibt die Mannschaft-ID zurück. 
	 * @return id.
	 *  
	 */	

	public int getActionId() {
		return actionId;
	}
	/**
	 * setze die Mannschaft-ID.  
	 * @return id.
	 *  
	 */	

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

}
