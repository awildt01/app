package game;

import java.sql.Time;

/**
 * Die Klasse Action bezeichnet alle Daten, die zu einem Action gehört. Diese
 * Felder werden aus der Datei vistrack-actions entnommen und in der Tabelle
 * Action gespeichert.
 * 
 * @author wildt
 * @version 1.0
 * 
 */


public class Action {

	private Integer id;
	private String typ;
	private Time time;
	private Integer halfTime;
	private Integer matchId;
	
	/**
	 *	Initialisiert ein Objekt von den Typ Action
	 *
	 *	id: eindeutige ID von einen Action.
	 *	typ: Die Actions sind entweder von Typen  action-soccer-score " ein Tor wurde erzielt"  oder action-soccer-score-attempt " versucht ein Tor zu erzielen".
	 *	Time: Die Zeitstempel von eine Action.
	 *	halfTime:  erste oder zweite halzeite.
	 *  MachtID: eindeutige Spiel-Id
	 * 
	 * 
	 * 
	 */
	

	public Action(Integer id, String typ, Time time, Integer halfTime, Integer matchId) {
		this.id = id;
		this.typ = typ;
		this.time = time;
		this.halfTime = halfTime;
		this.matchId = matchId;

	}
	
	/**
	 * gibt die Action-ID zurück. 
	 * @return id.
	 *  
	 */	

	public Integer getId() {
		return id;
	}
	
	/**
	 * setze die Action-ID.
	 * @return id.
	 *  
	 */

	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * gibt die Actiontyp zurück. 
	 * @return typ.
	 *  
	 */	

	public String getTyp() {
		return typ;
	}
	
	/**
	 * setze die Actiontyp-ID. 
	 * @return typ.
	 *  
	 */

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	/**
	 * gibt die Zeitstempel zurück. 
	 * @return time.
	 *  
	 */	

	public Time getTime() {
		return time;
	}
	
	/**
	 * setze die Zeitstempel. 
	 * @return time.
	 *  
	 */

	public void setTime(Time time) {
		this.time = time;
	}
	
	/**
	 * gibt die Halbzeit zurück. 
	 * @return halfTime.
	 *  
	 */	

	public Integer getHalfTime() {
		return halfTime;
	}
	
	/**
	 * setze Halbzeit.  
	 * @return halfTime..
	 *  
	 */

	public void setHalfTime(Integer halfTime) {
		this.halfTime = halfTime;
	}
	
	/**
	 * gibt die Spiel-ID zurück. 
	 * @return matchID.
	 *  
	 */	

	public Integer getMatchId() {
		return matchId;
	}
	
	/**
	 * setze die die Spiel-ID. 
	 * @return matchID.
	 *  
	 */

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

}
