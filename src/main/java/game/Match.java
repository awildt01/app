package game;

import java.sql.Date;
import java.sql.Time;

/**
 * Die Klasse Match bezeichnet alle Daten, die zu einem Spiel gehört. Diese
 * Felder werden aus der Datei vistrack-actions entnommen und in der Tabelle
 * Match gespeichert.
 * 
 * @author wildt
 * @version 1.0
 * 
 */

public class Match {

	private Integer id;
	private Date matchDate;
	private String tournamentName;
	private Time firstHalfBegin;
	private Time firstHalfEnd;
	private Time secondHalfBegin;
	private Time secondHalfEnd;
	private Integer TeamId1;
	private Integer TeamId2;
	private Float soccerFieldLength;
	private Float soccerFieldWide;

	/**
	 * Initialisiert ein Objekt von Typ Match
	 * 
	 *  id: eindeutige Spiel-Id 
	 * matchDate: Spieldatum 
	 * Tournament: Spielliga
	 * firstHalfBegin: Anfang der ersten Halbzeit 
	 * firstHalfEnd: Ende der ersten Halbzeit 
	 * secondHalfBegin: Anfang der zweiten Halbzeit 
	 * secondHalfEnd: Ende der zweiten Halbzeit 
	 * TeamId1: Mannschaft_1: eindeutige Mannschaft-Id 
	 * TeamID2: Mannschaft_2: eindeutige Mannschaft-Id 
	 * soccerFieldLength: Fußballfeldlänge
	 * soccerFieldWide: Fußballfeldbreite
	 * 
	 */

	public Match(Integer id, Date matchDate, String tournamentName, Time firstHalfBegin, Time firstHalfEnd, Time secondHalfBegin, Time secondHalfEnd, Integer TeamId1, Integer TeamId2, Float soccerFieldLength, Float soccerFieldWide) {
		this.id = id;
		this.matchDate = matchDate;
		this.tournamentName = tournamentName;
		this.firstHalfBegin = firstHalfBegin;
		this.firstHalfEnd = firstHalfEnd;
		this.secondHalfBegin = secondHalfBegin;
		this.secondHalfEnd = secondHalfEnd;
		this.TeamId1 = TeamId1;
		this.TeamId2 = TeamId2;
		this.soccerFieldLength = soccerFieldLength;
		this.soccerFieldWide = soccerFieldWide;
	}

	/**
	 * gibt das Spiel-ID zurück.
	 * 
	 * @return id.
	 * 
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * setze die Spiel-ID.
	 * 
	 * @param id
	 */

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * gibt das Spieldatum zurück.
	 * 
	 * @return machtDate
	 * 
	 */

	public Date getMatchDate() {
		return matchDate;
	}

	/**
	 * setze das Spieldatum .
	 * 
	 * @param machDate
	 *            .
	 */

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	/**
	 * gibt die Liga zurück .
	 * 
	 * @return tournamentName.
	 * 
	 */

	public String getTournamentName() {
		return tournamentName;
	}

	/**
	 * setze die Liga.
	 * 
	 * @param tournamentName
	 *            .
	 * 
	 */

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	/**
	 * gibt den Spielbeginn zurück.
	 * @returnfirsthalfBegin.
	 * 
	 */

	public Time getFirstHalfBegin() {
		return firstHalfBegin;
	}

	/**
	 * setze den Spielbeginn.
	 * @param firsthalfBegin
	 *            .
	 * 
	 */

	public void setFirstHalfBegin(Time firstHalfBegin) {
		this.firstHalfBegin = firstHalfBegin;
	}

	/**
	 * 
	 * gibt das Ende der ersten Halbzeit zurück.
	 * @return firstHalfEnd
	 * 
	 */

	public Time getFirstHalfEnd() {
		return firstHalfEnd;
	}

	/**
	 * 
	 * setze das Ende der ersten Halbzeit. 
	 * @param firstHalfend
	 * 
	 * 
	 */

	public void setFirstHalfEnd(Time firstHalfEnd) {
		this.firstHalfEnd = firstHalfEnd;
	}

	/**
	 * 
	 * gibt den Anfang der zweiten Halbzeit zurück. 
	 * @return secondHalfBegin
	 * 
	 * 
	 */

	public Time getSecondHalfBegin() {
		return secondHalfBegin;
	}

	/**
	 * 
	 * setze den Anfang der zweiten Halbzeit. 
	 * @param secoundHalfBegin
	 * 
	 * 
	 */

	public void setSecondHalfBegin(Time secondHalfBegin) {
		this.secondHalfBegin = secondHalfBegin;
	}

	/**
	 * 
	 * gibt das Ende der zweiten Halbzeit zurück.
	 * 
	 * @return secondHalfEnd
	 * 
	 * 
	 */

	public Time getSecondHalfEnd() {
		return secondHalfEnd;
	}

	/**
	 * 
	 * setze das Ende der zweiten Halbzeit.
	 * 
	 * @param secoundHalfBegin
	 * 
	 * 
	 */

	public void setSecondHalfEnd(Time secondHalfEnd) {
		this.secondHalfEnd = secondHalfEnd;
	}

	/**
	 * 
	 * gibt die Mannschaft_1-ID zurück.
	 * 
	 * @return TeamId1.
	 * 
	 * 
	 */

	public Integer getTeamId1() {
		return TeamId1;
	}

	/**
	 * 
	 * setze die Mannschaft_1-ID.
	 * 
	 * @param TeamId1
	 *            .
	 * 
	 * 
	 */

	public void setTeamId1(Integer teamId1) {
		TeamId1 = teamId1;
	}

	/**
	 * 
	 * gibt die zweite Mannschaft-ID zurück.
	 * 
	 * @return TeamId2.
	 * 
	 * 
	 */

	public Integer getTeamId2() {
		return TeamId2;
	}

	/**
	 * 
	 * setze die Mannschaft_2-ID.
	 * 
	 * @param TeamId2
	 *            .
	 * 
	 * 
	 */

	public void setTeamId2(Integer teamId2) {
		TeamId2 = teamId2;
	}

	/**
	 * 
	 * gibt die Fußballfeldlänge zurück.
	 * 
	 * @return soccerFieldLength
	 * 
	 * 
	 */

	public Float getSoccerFieldLength() {
		return soccerFieldLength;
	}

	/**
	 * 
	 * setze die Fußballfeldlänge.
	 * 
	 * @param soccerFieldLength
	 *            .
	 * 
	 * 
	 */

	public void setSoccerFieldLength(Float soccerFieldLength) {
		this.soccerFieldLength = soccerFieldLength;
	}

	/**
	 * 
	 * Gibt die Fußballfeldbreite zurück.
	 * 
	 * @return soccerFieldWide.
	 * 
	 * 
	 */

	public Float getSoccerFieldWide() {
		return soccerFieldWide;
	}

	/**
	 * 
	 * setze die Fußballfeldbreite.
	 * 
	 * @param soccerFieldWide
	 *            .
	 * 
	 * 
	 */

	public void setSoccerFieldWide(Float soccerFieldWide) {
		this.soccerFieldWide = soccerFieldWide;
	}

}
