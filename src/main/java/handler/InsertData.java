package handler;

import game.Action;
import game.Match;
import game.Team;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kalkukoordenation.MatchTime;
import kalkukoordenation.Position;

import org.w3c.dom.Document;

import util.Util;
import db.Database;

/**
 * 
 * Die Klasse InsertData  ist für die Speicherung von Daten in der Datenbank zuständig.
 * Die Methode execute  ist die Hauptmethode der Klasse InsertData, die die Datenspeicherung von  Mannschaften,
 * Aktionen, Spiele und SecPoints ausführt.
 * POSITION_ID_ bezieht auf die koordinaten vor 5, 10 20 Sekunden vor Torversucht.
 * private Connection con ist verantwortliche für die Dateverbindung
 * private File[] fileArray beinhaltet eine List mit den verscheinissen von der  XML Datei  
 * 
 * Liest die Spielfeldlänge aus der Pos-Datei.
 * 
 * @return List, Spielfeldlänge.
 */

public class InsertData {

	private final int POSITION_ID_1 = 1;
	private final int POSITION_ID_5 = 5;
	private final int POSITION_ID_10 = 10;
	private final int POSITION_ID_20 = 20;

	private final String POS_DIRECTORY = "C:/positionen/";

	private Connection con;
	private File[] fileArray;


	public InsertData(File[] fileArray) {
		this.fileArray = fileArray;
		this.con = Database.getConnect();
	}

	/**
	 *  
	 *  Die Methode execute ist der Hauptmethode zur Speicherung der Daten.  
	 *  die Methode ReadFiles.initXMLFile(file) lädt die XMl Datei mit Spieldaten und speichert in einem Document-Objekt"doc" zur späteren Weiterverarbeitung.
	 *  
	 */

	public void execute() throws Exception {

		int i = 1;
		for (File file : fileArray) {

			System.out.println("------------------- " + i++ + ". XML-Datei: " + file.getAbsolutePath() + " ---------------------------------");
			System.out.println();

			Document doc = ReadFiles.initXMLFile(file);

			if (doc != null) {
				Integer matchId = ReadFiles.getMatchId(doc);
				File f = new File(POS_DIRECTORY + matchId + ".pos");
				if (f.exists()) {
					FileReader fr = new FileReader(POS_DIRECTORY + matchId + ".pos");
					BufferedReader br = new BufferedReader(fr);

					System.out.println("POS-Datei: " + POS_DIRECTORY + matchId + ".pos");
					System.out.println();

					if (!existsMatch(matchId)) {
						List<Team> teams = ReadFiles.readTeams(doc);
						insertTeams(teams);

						Match match = ReadFiles.readMatch(doc, br);
						insertMatch(match);

						List<Action> actions = ReadFiles.readAction(doc);
						insertActions(actions);

						List<MatchTime> matchTimes = Util.getMatchTimes(match);
						List<Position> positions = ReadFiles.readPosFile(br);
						br.close();
						position(positions, matchTimes);
					} else {
						System.out.println("Spiel mit Id " + matchId + " ist schon in der Datenbank vorhanden");
					}
				} else {
					System.out.println("Keine POS-Datei zum Spiel mit Id " + matchId + " in Verzeichnis " + POS_DIRECTORY + matchId + ".pos" + " gefunden.");
				}
			}
			System.out.println("----------------------------------------------------------------------------");
		}
		closeConnection();
	}

	private boolean existsMatch(Integer matchId) throws Exception {
		String query = "SELECT id FROM matches WHERE id=" + matchId;
		ResultSet result = con.createStatement().executeQuery(query);
		if (result.next()) {
			return true;
		}

		return false;
	}

	/**
	 *Die Methode position ermittelt für jede Aktion des Spiels aus der XML-vistrack-actions-Datei das entsprechende Frame von der Pos-Datei. 
	 *Es werden auch die 5, 10 und 20 Sekunden vor einem Torversuch oder einem Tor berechnet und die bezüglichen Frames bestimmt.
	 * 
	 */

	private void position(List<Position> positions, List<MatchTime> matchTimes) throws Exception {

		for (MatchTime matchTime : matchTimes) {

			// Position_Id = 1
			int frame1 = matchTime.getSeconds() * 25;
			for (Position pos : positions) {
				if (pos != null && pos.getHalfTime() == matchTime.getHalfTime() && pos.getId() == frame1) {
					System.out.println("Position_id: " + POSITION_ID_1 + " Time: " + matchTime.getSeconds() + " Frame: " + frame1 + " X: " + pos.getX() + " Y: " + pos.getY() + " HalbzeitSekunden: " + matchTime.getHalfTime() + " HalbzeitPosition: " + pos.getHalfTime());
					insertSecPoints(POSITION_ID_1, pos.getX(), pos.getY(), matchTime.getActionId());
				}
			}

			// Position_Id = 5
			int frame5 = (matchTime.getSeconds() - POSITION_ID_5) * 25;
			for (Position pos : positions) {
				if (pos != null && pos.getHalfTime() == matchTime.getHalfTime() && pos.getId() == frame5) {
					System.out.println("Position_id: " + POSITION_ID_5 + " Time: " + (matchTime.getSeconds() - POSITION_ID_5) + " Frame: " + frame5 + " X: " + pos.getX() + " Y: " + pos.getY() + " HalbzeitSekunden: " + matchTime.getHalfTime() + " HalbzeitPosition: " + pos.getHalfTime());
					insertSecPoints(POSITION_ID_5, pos.getX(), pos.getY(), matchTime.getActionId());
				}
			}

			// Position_Id = 10
			int frame10 = (matchTime.getSeconds() - POSITION_ID_10) * 25;
			for (Position pos : positions) {
				if (pos != null && pos.getHalfTime() == matchTime.getHalfTime() && pos.getId() == frame10) {
					System.out.println("Position_id: " + POSITION_ID_10 + " Time: " + (matchTime.getSeconds() - POSITION_ID_10) + " Frame: " + frame10 + " X: " + pos.getX() + " Y: " + pos.getY() + " HalbzeitSekunden: " + matchTime.getHalfTime() + " HalbzeitPosition: " + pos.getHalfTime());
					insertSecPoints(POSITION_ID_10, pos.getX(), pos.getY(), matchTime.getActionId());
				}
			}

			// Position_Id = 20
			int frame20 = (matchTime.getSeconds() - POSITION_ID_20) * 25;
			for (Position pos : positions) {
				if (pos != null && pos.getHalfTime() == matchTime.getHalfTime() && pos.getId() == frame20) {
					System.out.println("Position_id: " + POSITION_ID_20 + " Time: " + (matchTime.getSeconds() - POSITION_ID_20) + " Frame: " + frame20 + " X: " + pos.getX() + " Y: " + pos.getY() + " HalbzeitSekunden: " + matchTime.getHalfTime() + " HalbzeitPosition: " + pos.getHalfTime());
					insertSecPoints(POSITION_ID_20, pos.getX(), pos.getY(), matchTime.getActionId());
				}
			}
		}
	}

	/**
	 * Die Methode InsertPoints  ist Zuständig für  die Ausführung eine Insertstament in der Tabelle  secpoints. 
	 * In diesen Insertstament werden position_id , aktion-id und posiction_secs gespeichert.
	 * 
	 */

	public void insertSecPoints(int posId, float x, float y, int actionId) throws Exception {
		System.out.println("SecPoints insert:");
		System.out.println("Position_id: " + posId + " X: " + x + " Y: " + y + " ActionId: " + actionId);

		String query = "INSERT INTO secpoints (position_id, action_id, position_secs) VALUES (" + posId + ", " + actionId + ", ST_GeomFromText('POINT ( " + x + " " + y + " )', -1) ) ";
		con.createStatement().executeUpdate(query);
	}

	/**
	 *Die Methode InsertActions ist Zuständig für die Ausführung eine Insertstament in der Tabelle actions. 
	 *In diesen Insertstament werden aktion_id, typ,timestamp,spiel_id und halbzeite.
	 * 
	 */

	private void insertActions(List<Action> aktionen) throws Exception {
		for (Action aktion : aktionen) {
			PreparedStatement insert = con.prepareStatement("INSERT INTO actions (file_action_id, typ, action_time, match_id, halftime) VALUES(?, ?, ?, ?, ?) ");
			insert.setInt(1, aktion.getId());
			insert.setString(2, aktion.getTyp());
			insert.setTime(3, aktion.getTime());
			insert.setInt(4, aktion.getMatchId());
			insert.setInt(5, aktion.getHalfTime());
			insert.execute();
			insert.close();
		}
	}

	/**
	 * Die Methode InsertMatch ist Zuständig für die Ausführung eine Insertstament in der Tabelle matchs.
	 * In diesen Insertstament werden match_id, Datum, Liga, halbzeit_anfang_1, halbzeit_ende_1, halbzeit_anfang_2,
	 *  halbzeit_ende_2, mannschaft_1, mannschaf_2 gespeichert.
	 * 
	 */

	private void insertMatch(Match spiel) throws Exception {
		PreparedStatement insert = con.prepareStatement("INSERT INTO matches (id, match_date, tournament_name, first_half_begin, first_half_end, second_half_begin, second_half_end, team_id_1, team_id_2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ");
		insert.setInt(1, spiel.getId());
		insert.setDate(2, spiel.getMatchDate());
		insert.setString(3, spiel.getTournamentName());
		insert.setTime(4, spiel.getFirstHalfBegin());
		insert.setTime(5, spiel.getFirstHalfEnd());
		insert.setTime(6, spiel.getSecondHalfBegin());
		insert.setTime(7, spiel.getSecondHalfEnd());
		insert.setInt(8, spiel.getTeamId1());
		insert.setInt(9, spiel.getTeamId2());
		insert.execute();
		insert.close();
	}

	/**
	 * Die Methode InsertTeams ist Zuständig für die Ausführung eine Insertstament in der Tabelle team.
	 * In diesen Insertstament werden id und name  von Mannschaften gespeichert.
	 * 
	 */

	private void insertTeams(List<Team> list) {
		for (Team team : list) {
			try {
				PreparedStatement insert = con.prepareStatement("INSERT INTO teams (id, name) VALUES(?,?) ");
				insert.setInt(1, team.getId());
				insert.setString(2, team.getName());
				insert.execute();
				insert.close();
			} catch (Exception e) {
				System.out.println("Team existiert bereit: " + team.getName());
			}
		}
	}

	/**
	 * schielisung der Datenbak.
	 * 
	 */

	private void closeConnection() throws Exception {
		con.close();
	}

}
