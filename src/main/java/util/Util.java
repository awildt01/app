package util;

import game.Match;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kalkukoordenation.MatchTime;
import db.Database;

/**
 *  Die klasse Util beinhaltet Hilfsmethoden, die für die Umwandlung von Daten Typ und die Berechnung die Spielzeit 
 *  in Sekunden verwendet werden. 
 *  
 * 
 * @author wildt
 * @version 1.0
 * 
 */

public class Util {
	
	/**
	 *  Die Methode getDate wandelt ein String in eine Date um.
	 * 
	 */

	public static java.sql.Date getDate(String stringDate) throws Exception {
		java.sql.Date sqlDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
		Date parsed = format.parse(stringDate);
		sqlDate = new java.sql.Date(parsed.getTime());

		return sqlDate;
	}
	
	/**
	 *  Die Methode getTime, wandelt von Spielzeit von Typ String in einen Time Typ um.
	 * 
	 */

	public static java.sql.Time getTime(String stringTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
		long ms = sdf.parse(stringTime).getTime();

		return new Time(ms);
	}
	
	/**
	 *  Die Methode getMatchTimes berechnet die MatchTimes von einem Spiel 
	 *  aus der Datenbank und gibt sie zurück.
	 *   
	 * 
	 */

	public static List<MatchTime> getMatchTimes(Match match) throws Exception {

		Connection con = Database.getConnect();

		String query = "SELECT ak.id, ak.action_time, ak.halftime, sp.first_half_begin, (ak.action_time - sp.first_half_begin) " 
					+ "FROM actions ak, matches sp WHERE sp.id=ak.match_id AND ak.match_id=? AND ak.halftime=1 " 
					+ "UNION " 
					+ "SELECT ak.id, ak.action_time, ak.halftime, sp.second_half_begin, (ak.action_time - sp.second_half_begin) " 
					+ "FROM actions ak, matches sp WHERE sp.id=ak.match_id AND ak.match_id=? AND ak.halftime=2 ";

		PreparedStatement select = con.prepareStatement(query);
		select.setInt(1, match.getId());
		select.setInt(2, match.getId());

		ResultSet result = select.executeQuery();

		List<MatchTime> matchTimes = new ArrayList<MatchTime>();

		while (result.next()) {
			int id = result.getInt(1);
			Time time = result.getTime(2);
			int halbzeit = result.getInt(3);
			Time halbzeitanfang = result.getTime(4);
			Time min = result.getTime(5);

			int seconds = min.getSeconds();
			int minuten = min.getMinutes() * 60;
			int hours = min.getHours() * 60 * 60;

			int totalTime = seconds + minuten + hours;
			
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Aktionen - Id: " + id + " timestamp: " + time + " halbzeitanfang: " + halbzeitanfang + " Min: " + min + " Halbzeit: " + halbzeit);
			System.out.println("Zeit: Stunden: " + min.getHours() + " Minuten: " + min.getMinutes() + " Sekunden: " + min.getSeconds());
			System.out.println("Zeit Total= " + totalTime + "  Stunden: " + hours + " Min: " + minuten + " Sekunden: " + seconds);


			MatchTime matchTime = new MatchTime(totalTime, halbzeit, id);
			matchTimes.add(matchTime);
		}
		con.close();

		return matchTimes;
	}
}
